package br.com.vital.starwars.service;

import java.util.List;

import br.com.vital.starwars.dto.request.PlanetRequest;
import br.com.vital.starwars.dto.response.ListAllPlanetsResponse;
import br.com.vital.starwars.dto.response.PlanetResponse;

public interface PlanetService {
	
	void save(PlanetRequest request);

	List<PlanetResponse> listAllSaved();

	void delete(PlanetRequest request);

	List<PlanetResponse> findByName(String name);
	
	PlanetResponse findByID(String name);

	List<ListAllPlanetsResponse> listAll();

}
