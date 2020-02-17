package br.com.vital.starwars.exception;

import com.mongodb.MongoWriteException;

public class InsertPlanetException extends RuntimeException {

	public InsertPlanetException(String code, String message, MongoWriteException e) {
		super(message);
	}

	private static final long serialVersionUID = 8718927195148154130L;

}
