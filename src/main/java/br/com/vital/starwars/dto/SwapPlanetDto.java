package br.com.vital.starwars.dto;

import br.com.vital.starwars.domain.Planet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SwapPlanetDto {
	private String name;
	private String climate;
	private String terrain;
	private String[] films;
	
	public static Planet toDomain(SwapPlanetDto swapPlanet) {
		Planet planet = new Planet();
		planet.setName(swapPlanet.getName());
		planet.setTerrain(swapPlanet.getTerrain());
		planet.setClimate(swapPlanet.getClimate());
		planet.setTotalMovieShowing(swapPlanet.getFilms() != null ? swapPlanet.getFilms().length : null);
		
		return planet;
	}

}
