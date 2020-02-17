package br.com.vital.starwars.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vital.starwars.domain.Empire;
import br.com.vital.starwars.domain.Planet;
import br.com.vital.starwars.dto.request.PlanetRequest;
import br.com.vital.starwars.dto.response.ListAllPlanetsResponse;
import br.com.vital.starwars.dto.response.PlanetResponse;
import br.com.vital.starwars.repository.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetService {
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@PostConstruct
	public void init() {
		planetRepository.listAll();
	}

	@Override
	public void save(PlanetRequest request) {
		Empire empire = new Empire(planetRepository);
		empire.save(request.toDomain());
	}

	@Override
	public List<PlanetResponse> listAllSaved() {
		Empire empire = new Empire(planetRepository);
		
		List<Planet> listPlanet = empire.listAllSaved();
		
		if(!Objects.isNull(listPlanet)) {
			return listPlanet.stream().map(p -> PlanetResponse.fromDomain(p)).collect(Collectors.toList());
		}
		
		return null;
	}

	@Override
	public void delete(PlanetRequest request) {
		Empire empire = new Empire(planetRepository);
		empire.remove(request.toDomain());
	}

	@Override
	public List<PlanetResponse> findByName(String name) {
		Empire empire = new Empire(planetRepository);
		
		List<Planet> listPlanet = empire.findByName(name);
		
		if(!Objects.isNull(listPlanet)) {
			return listPlanet.stream().map(p -> PlanetResponse.fromDomain(p)).collect(Collectors.toList());
		}
		
		return null;
	}

	@Override
	public List<ListAllPlanetsResponse> listAll() {
		Empire empire = new Empire(planetRepository);
		List<Planet> listPlanet = empire.listAll();
		
		if(!Objects.isNull(listPlanet)) {
			return listPlanet.stream().map(p -> ListAllPlanetsResponse.fromDomain(p)).collect(Collectors.toList());
		}
		
		return null;
	}

	@Override
	public PlanetResponse findByID(String id) {
		Empire empire = new Empire(planetRepository);
		
		Planet planet = empire.findId(id);
		
		if(!Objects.isNull(planet)) {
			return PlanetResponse.fromDomain(planet);
		}
		
		return null;
	}

}
