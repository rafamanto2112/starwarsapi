package br.com.vital.starwars.builder;

import br.com.vital.starwars.domain.Planet;

public class PlanetBuilder {
	
	private Planet planet;
	
	public static PlanetBuilder create() {
		PlanetBuilder planetBuilder = new PlanetBuilder();
		planetBuilder.planet = new Planet();
		planetBuilder.planet.setName("Alderaan");
		planetBuilder.planet.setClimate("temperate");
		planetBuilder.planet.setTerrain("grasslands, mountains");
		
		return planetBuilder;
	}
	
	public Planet build() {
		return planet;
	}

	public PlanetBuilder withID(String id) {
		this.planet.setId(id);
		return this;
	}

	public PlanetBuilder withName(String name) {
		this.planet.setName(name);
		return this;
	}

}
