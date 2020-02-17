package br.com.vital.starwars.exception;

public class SavePlanetException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SavePlanetException(String code, String message, Throwable e) {
		super(message);
	}

}
