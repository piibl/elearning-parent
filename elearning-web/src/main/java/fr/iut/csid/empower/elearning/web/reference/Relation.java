package fr.iut.csid.empower.elearning.web.reference;

import org.springframework.hateoas.Link;

/**
 * This is our internal API for the view. Links are identified by relations. Of course not every entity has a link for every relation listed here, it
 * always depends on the circumstances.
 * 
 * @author tobias.flohre
 */
public enum Relation {

	// Relations communes
	SEARCH("search"), NEW("new"), SELF(Link.REL_SELF), DELETE("delete"), EDIT("edit"),
	// Relations globales par type d'entité
	STUDENTS("students"), ADMINISTRATORS("administrators"), TEACHERS("teachers");

	private String name;

	private Relation(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
