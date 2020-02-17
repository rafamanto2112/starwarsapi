package br.com.vital.starwars.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PlanetNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String code;

	public PlanetNotFoundException(String code, String message) {
		super(message);
		this.code = code;
	}


}
