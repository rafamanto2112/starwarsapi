package br.com.vital.starwars.dto.request;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.vital.starwars.domain.Planet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Document("planets")
public class PlanetDto {

	@Id
	private String id;
	private String name;
	private String climate;
	private String terrain;
	private Integer totalMovieShowing;

	@Transient
	public static final String SEQUENCE_NAME = "planet_sequence";

	public static PlanetDto toDTO(Planet planet) {
		PlanetDto dto = new PlanetDto();
		dto.setId(planet.getId());
		dto.setName(planet.getName());
		dto.setClimate(planet.getClimate());
		dto.setTerrain(planet.getTerrain());
		dto.setTotalMovieShowing(planet.getTotalMovieShowing());

		return dto;
	}
	
	public static Planet toDomain(PlanetDto dto) {
		Planet planet = null;
		if (dto != null) {
			planet = new Planet();
			planet.setId(dto.getId());
			planet.setName(dto.getName());
			planet.setTerrain(dto.getTerrain());
			planet.setClimate(dto.getClimate());
			planet.setTotalMovieShowing(dto.getTotalMovieShowing());
		}
		
		return planet;
	}

}
