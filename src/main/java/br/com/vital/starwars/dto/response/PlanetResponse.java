package br.com.vital.starwars.dto.response;

import br.com.vital.starwars.domain.Planet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlanetResponse {
	
	private String id;
	private String name;
	private String climate;
	private String terrain;
	private Integer totalMovieShowing;
	
	public static PlanetResponse fromDomain(Planet planet) {
		PlanetResponse response = new PlanetResponse();
		response.setId(planet.getId());
		response.setName(planet.getName());
		response.setTerrain(planet.getTerrain());
		response.setClimate(planet.getClimate());
		response.setTotalMovieShowing(planet.getTotalMovieShowing());
		
		return response;
	}

}
