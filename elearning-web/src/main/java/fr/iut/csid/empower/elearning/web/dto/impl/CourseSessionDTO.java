package fr.iut.csid.empower.elearning.web.dto.impl;

import java.io.Serializable;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.web.dto.OwnedDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class CourseSessionDTO implements Serializable, OwnedDTO<Long> {

	private String label;
	private String summary;
	// private DateTime startDate;
	// private DateTime endDate;
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

	// public DateTime getStartDate() {
	// return startDate;
	// }
	//
	// public void setStartDate(DateTime startDate) {
	// this.startDate = startDate;
	// }
	//
	// public DateTime getEndDate() {
	// return endDate;
	// }
	//
	// public void setEndDate(DateTime endDate) {
	// this.endDate = endDate;
	// }

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerCourseId) {
		this.ownerId = ownerCourseId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
