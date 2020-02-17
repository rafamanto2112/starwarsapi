package br.com.vital.starwars.domain;

import java.io.Serializable;

public class Planet implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String climate;
	private String terrain;
	private Integer totalMovieShowing;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Integer getTotalMovieShowing() {
		return totalMovieShowing;
	}

	public void setTotalMovieShowing(Integer totalMovieShowing) {
		this.totalMovieShowing = totalMovieShowing;
	}

}
