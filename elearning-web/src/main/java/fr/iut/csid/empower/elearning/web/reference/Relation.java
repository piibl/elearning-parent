package fr.iut.csid.empower.elearning.web.reference;

import org.springframework.hateoas.Link;

/**
 * TODO javadoc <br/>
 * Fil d'arianne entre points logiques : sert à décrire les liens physiques, voir templates d'affichage (display/*)
 */
public enum Relation {

	// Relations communes
	SEARCH("search"), NEW("new"), SELF(Link.REL_SELF), DELETE("delete"), EDIT("edit"),
	// Relations globales par type d'entité
	// Utilisateurs
	STUDENTS("students"), ADMINISTRATORS("administrators"), TEACHERS("teachers"),
	// Cours
	COURSES("courses"), SESSIONS("sessions"), SUBSCRIBE("subscribe"), SUBSCRIBED("subscribed"), NOTSUBSCRIBED("notSubscribed"), RESOURCES("resources");

	private String name;

	private Relation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
