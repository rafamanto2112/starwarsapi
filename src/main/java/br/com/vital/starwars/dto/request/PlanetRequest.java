package br.com.vital.starwars.dto.request;

import br.com.vital.starwars.domain.Planet;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlanetRequest {

	private String id;
	private String name;
	private String climate;
	private String terrain;

	public Planet toDomain() {
		Planet planet = new Planet();
		planet.setId(id);
		planet.setName(name);
		planet.setTerrain(terrain);
		planet.setClimate(climate);

		return planet;
	}

}
