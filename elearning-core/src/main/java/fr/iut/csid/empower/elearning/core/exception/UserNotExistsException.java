package fr.iut.csid.empower.elearning.core.exception;


public class UserNotExistsException extends RuntimeException {

	public UserNotExistsException() {
		super();
	}

	public UserNotExistsException(String message) {
		super(message);
	}

}
