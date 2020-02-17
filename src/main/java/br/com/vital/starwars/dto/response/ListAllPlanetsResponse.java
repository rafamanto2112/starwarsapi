package br.com.vital.starwars.dto.response;

import br.com.vital.starwars.domain.Planet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ListAllPlanetsResponse {

	private String name;
	private String climate;
	private String terrain;
	private Integer totalMovieShowing;
	
	public static ListAllPlanetsResponse fromDomain(Planet planet) {
		ListAllPlanetsResponse response = new ListAllPlanetsResponse();
		response.setName(planet.getName());
		response.setTerrain(planet.getTerrain());
		response.setClimate(planet.getClimate());
		response.setTotalMovieShowing(planet.getTotalMovieShowing());
		
		return response;
	}
}
