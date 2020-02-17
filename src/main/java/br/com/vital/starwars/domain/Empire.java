package br.com.vital.starwars.domain;

import java.util.List;
import java.util.Objects;

import br.com.vital.starwars.exception.PlanetNotFoundException;
import br.com.vital.starwars.repository.PlanetRepository;

public class Empire {
	
	private PlanetRepository planetRepository;

	public Empire(PlanetRepository planetRepository) {
		this.planetRepository = planetRepository;
	}

	public void save(Planet planet) {
		if(hasntPlanetId(planet)) {
			planetRepository.insert(planet);
		} else {
			Planet planetResult = planetRepository.findById(planet.getId());
			
			if(!Objects.isNull(planetResult)) {
				planetRepository.save(planet);
			} else {
				throw new PlanetNotFoundException("FIND_0040", "planet not found");
			}
		}
	}
	
	private boolean hasntPlanetId(Planet planet) {
		return Objects.isNull(planet) || Objects.isNull(planet.getId()) || planet.getId().trim().isEmpty();
	}

	public List<Planet> listAllSaved() {
		return planetRepository.listAllSaved();
	}

	public void remove(Planet planet) {
		planetRepository.remove(planet);
	}

	public List<Planet> findByName(String name) {
		return planetRepository.findByName(name);
	}

	public List<Planet> listAll() {
		return planetRepository.listAll();
	}

	public Planet findId(String id) {
		return planetRepository.findById(id);
	}

}
