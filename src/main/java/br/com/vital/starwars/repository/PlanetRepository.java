package br.com.vital.starwars.repository;

import java.util.List;

import br.com.vital.starwars.domain.Planet;

public interface PlanetRepository {
	
	void insert(Planet planet);
	void save(Planet planet);
	Planet findById(String id);
	List<Planet> listAllSaved();
	void remove(Planet planet);
	List<Planet> findByName(String name);
	public Integer getTotalMovieShowing(String name);
	public List<Planet> listAll();

}
