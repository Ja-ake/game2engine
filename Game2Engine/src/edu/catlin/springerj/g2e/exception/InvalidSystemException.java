package edu.catlin.springerj.g2e.exception;

public class InvalidSystemException extends RuntimeException {
	public InvalidSystemException() {
		super();
	}

	public InvalidSystemException(String err) {
		super(err);
	}
}
