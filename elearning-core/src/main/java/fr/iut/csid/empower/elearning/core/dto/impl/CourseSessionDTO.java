package fr.iut.csid.empower.elearning.core.dto.impl;

import java.io.Serializable;

import fr.iut.csid.empower.elearning.core.dto.OwnedDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class CourseSessionDTO implements Serializable, OwnedDTO<Long> {

	private String label;

	/**
	 * Identifiant du cours au format chaine de caractère
	 */
	private Long ownerId;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerCourseId) {
		this.ownerId = ownerCourseId;
	}

}
