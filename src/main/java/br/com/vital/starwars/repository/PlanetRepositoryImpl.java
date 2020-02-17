package br.com.vital.starwars.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;

import br.com.vital.starwars.config.RedisConfig;
import br.com.vital.starwars.domain.Planet;
import br.com.vital.starwars.dto.SwapPlanetDto;
import br.com.vital.starwars.dto.SwapRestApiDto;
import br.com.vital.starwars.dto.request.PlanetDto;
import br.com.vital.starwars.exception.FindPlanetException;
import br.com.vital.starwars.exception.InsertPlanetException;
import br.com.vital.starwars.exception.SavePlanetException;
import br.com.vital.starwars.exception.SwapApiCallException;

@Repository
public class PlanetRepositoryImpl implements PlanetRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private RedisConfig redisConfig;
	
	private static final String SWAP_API_URL = "https://swapi.co/api/planets";
	
	private static final String REDIS_KEY = "PLANET_LIST";
	
	@Override
	public void insert(Planet planet) {
		try {
			planet.setTotalMovieShowing(getTotalMovieShowing(planet.getName()));
			mongoTemplate.insert(PlanetDto.toDTO(planet));
		} catch(MongoWriteException e) {
			throw new InsertPlanetException("SAVE_0001", "failed to save the planet", e);
		}
	}

	@Override
	public void save(Planet planet) {
		try {
			mongoTemplate.save(PlanetDto.toDTO(planet));
		} catch (MongoWriteException e) {
			throw new SavePlanetException("SAVE_0002", "failed to save the planet", e);
		}
	}

	@Override
	public Planet findById(String id) {
		try {
			return PlanetDto.toDomain(mongoTemplate.findById(id, PlanetDto.class));
		} catch (MongoException e) {
			throw new FindPlanetException("FIND_0030", "failed to find the planet", e);
		}
	}

	@Override
	public List<Planet> listAllSaved() {
		List<PlanetDto> listResult = mongoTemplate.findAll(PlanetDto.class);
		
		if(!Objects.isNull(listResult)) {
			return listResult.stream().map(p -> PlanetDto.toDomain(p)).collect(Collectors.toList());
		}
		
		return null;
	}

	@Override
	public void remove(Planet planet) {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("id").is(planet.getId()));
			
			mongoTemplate.findAndRemove(query, PlanetDto.class);
		} catch (MongoException e) {
			throw new FindPlanetException("FIND_0030", "failed to remove the planet", e);
		}
	}

	@Override
	public List<Planet> findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		
		List<PlanetDto> listResult = mongoTemplate.find(query, PlanetDto.class);
		
		if(!Objects.isNull(listResult)) {
			return listResult.stream().map(p -> PlanetDto.toDomain(p)).collect(Collectors.toList());
		}
		
		return null;
	}
	
	public Integer getTotalMovieShowing(String planetName) {
		List<Planet> listPlanet = listAll();
		
		if(listPlanet != null) {
			Optional<Planet> optional = listPlanet.stream().filter(e -> e.getName().equals(planetName)).findFirst();
			if(optional.isPresent()) {
				return optional.get().getTotalMovieShowing();
			}
		}
		
		return null;
	}
	
	public List<Planet> listAll() {
		List<Planet> planets = getFromRedis();
		
		if(planets != null) {
			return planets;
		}
		
		try {
			planets = new ArrayList<>();
			SwapRestApiDto swapRestApiDto = new SwapRestApiDto();
			swapRestApiDto.setNext(SWAP_API_URL);
			
			do {
				swapRestApiDto = callSwapService2(swapRestApiDto.getNext());
				
				if(swapRestApiDto != null && swapRestApiDto.getResults() != null) {
					planets.addAll(swapRestApiDto.getResults().stream().map(p -> SwapPlanetDto.toDomain(p)).collect(Collectors.toList()));
				}
			} while(swapRestApiDto != null && swapRestApiDto.getNext() != null);
			
			saveOnRedis(planets);
			
			return planets;
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new SwapApiCallException("0004", "SwapApi service call failed");
		}
	}
	

	private void saveOnRedis(List<Planet> planets) {
		redisConfig.save(REDIS_KEY, planets);
	}
	
	private List<Planet> getFromRedis() {
		Planet[] planets = redisConfig.get(REDIS_KEY, Planet[].class);
		
		if(planets != null && planets.length > 0) {
			return Arrays.asList(planets);
			
		}
		
		return null;
	}

	private SwapRestApiDto callSwapService2(String nextUrl) {
		final HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "teste");

		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		ResponseEntity<SwapRestApiDto> planets = restTemplate.exchange(nextUrl, HttpMethod.GET, entity, SwapRestApiDto.class);
		
		boolean hasResult = planets != null && planets.getBody() != null && planets.getBody().getResults() != null;

		if (hasResult) {
			return planets.getBody();
		} else {
			return null;
		}
	}
	

}
