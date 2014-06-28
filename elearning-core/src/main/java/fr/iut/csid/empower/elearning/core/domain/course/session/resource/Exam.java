package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.reference.ResourceType;
import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

/**
 * Contrôle d'une session
 */
@Entity
@DiscriminatorValue("E")
public class Exam extends Resource {

	/**
	 * Date de début de disponibilité pour les élèves du contrôle
	 */
	@Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime startDate;
	/**
	 * Date de fin de disponibilité pour les élèves du contrôle
	 */
	@Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime endDate;

	/**
	 * TODO utile ? La date de fin suffit
	 */
	@Column(name = "ARCHIVED")
	private boolean archived;

	public Exam() {

	}

	/**
	 * Constructeur
	 * 
	 * @param ownerSession
	 * @param name
	 * @param summary
	 */
	public Exam(CourseSession ownerSession, String resourceName, String summary, DateTime startDate, DateTime endDate) {
		super(ownerSession, resourceName, summary, ResourceType.EXAM);
		this.startDate = startDate;
		this.endDate = endDate;
		this.archived = false;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

}
