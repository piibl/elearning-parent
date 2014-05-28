package fr.iut.csid.empower.elearning.core.exception;


public class UserLoginAlreadyExistsException extends RuntimeException {

	public UserLoginAlreadyExistsException() {
		super();
	}

	public UserLoginAlreadyExistsException(String message) {
		super(message);
	}

}
