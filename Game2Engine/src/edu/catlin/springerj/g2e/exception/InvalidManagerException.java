package edu.catlin.springerj.g2e.exception;

public class InvalidManagerException extends RuntimeException {
	public InvalidManagerException() {
		super();
	}

	public InvalidManagerException(String err) {
		super(err);
	}
}
