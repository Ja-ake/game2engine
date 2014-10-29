package edu.catlin.springerj.g2e.exception;

public class InvalidComponentException extends RuntimeException {
	public InvalidComponentException() { super(); }
	public InvalidComponentException(String err) { super(err); }
}
