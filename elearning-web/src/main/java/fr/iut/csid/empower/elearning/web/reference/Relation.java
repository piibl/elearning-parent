package fr.iut.csid.empower.elearning.web.reference;

import org.springframework.hateoas.Link;

/**
 * TODO javadoc fil d'arianne entre points logiques
 */
public enum Relation {

	// Relations communes
	SEARCH("search"), NEW("new"), SELF(Link.REL_SELF), DELETE("delete"), EDIT("edit"),
	// Relations globales par type d'entité
	// Utilisateurs
	STUDENTS("students"), ADMINISTRATORS("administrators"), TEACHERS("teachers"),
	// Cours
	COURSES("courses"), SESSIONS("sessions");

	private String name;

	private Relation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
