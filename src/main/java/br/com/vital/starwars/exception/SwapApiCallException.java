package br.com.vital.starwars.exception;

public class SwapApiCallException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SwapApiCallException(String code, String message) {
		super(message);
	}


}
