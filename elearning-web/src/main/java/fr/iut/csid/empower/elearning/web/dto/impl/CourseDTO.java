package fr.iut.csid.empower.elearning.web.dto.impl;

import java.io.Serializable;

import fr.iut.csid.empower.elearning.web.dto.IDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class CourseDTO implements Serializable, IDTO {

	private String label;

	/**
	 * Identifiant du créateur du cours au format chaine de caractère
	 */
	private String ownerId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

}
