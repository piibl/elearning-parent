package fr.iut.csid.empower.elearning.web.reference;

/**
 * Centralisation des "bouts" d'url pour accéder aux ressources
 */
public enum DisplayViewPath {

	DISPLAY_COURSES("display/courses :: display-courses"), DISPLAY_COURSE("display/courses :: display-details"), DISPLAY_ADMINS(
			"display/courses :: display-courses"), DISPLAY_ADMIN("display/courses :: display-details"), DISPLAY_STUDENTS(
			"display/courses :: display-courses"), DISPLAY_STUDENT("display/courses :: display-details")

	;

	private String path;

	private DisplayViewPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
