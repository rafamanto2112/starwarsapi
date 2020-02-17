package br.com.vital.starwars.exception;

import com.mongodb.MongoException;

public class FindPlanetException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FindPlanetException(String code, String message, MongoException e) {
		super(message);
	}


}
