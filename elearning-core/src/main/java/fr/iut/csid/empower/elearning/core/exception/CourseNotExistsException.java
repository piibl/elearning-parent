package fr.iut.csid.empower.elearning.core.exception;


public class CourseNotExistsException extends RuntimeException {

	public CourseNotExistsException() {
		super();
	}

	public CourseNotExistsException(String message) {
		super(message);
	}

}
