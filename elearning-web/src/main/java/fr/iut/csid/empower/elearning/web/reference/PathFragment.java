package fr.iut.csid.empower.elearning.web.reference;

/**
 * Centralisation des "bouts" d'url pour accéder aux ressources
 */
public enum PathFragment {

	// Racine des templates thymeleaf
	REDIRECT("redirect:/"),
	// paths métier globaux (CRUD)
	NEW("new"), EDIT("edit"), DELETE("delete"),
	// Paths métiers spécifiques
	COURSES("courses"), SESSIONS("sessions"), SUBSCRIBE("subscribe"),
	// Paths communs
	HOME("home"), DENIED("denied"),
	// Tableaux de bord
	ADMIN_DASHBOARD("dashboards/admin"), STUDENT_DASHBOARD("dashboards/student"), TEACHER_DASHBOARD("dashboards/teacher"), NOTSUBSCRIBED(
			"not-subscribed"), SUBSCRIBED("subscribed"), NOTIFICATIONS("notifications"), NEWS("news"), RESOURCES("resources"),

	;

	private String path;

	private PathFragment(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

}
