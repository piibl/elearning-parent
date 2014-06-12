package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

@Entity
@Table(name = "EVALUATION_RESPONSE")
public class EvaluationResponse {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EvaluationResponseSeq")
	@SequenceGenerator(name = "EvaluationResponseSeq", sequenceName = "EVALUATION_RESPONSE_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "EVALUATION_RESPONSE_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Session propriétaire de la ressource
	 */
	@Transient
	private CourseSession ownerSession;

	/**
	 * Type de la ressource TODO enum
	 */
	@Transient
	private String resourceType;

	/**
	 * Path de la ressource
	 */
	@Transient
	private String resourcePath;

	public EvaluationResponse() {

	}

	/**
	 * @param ownerSession
	 * @param resourceType
	 * @param resourcePath
	 */
	public EvaluationResponse(CourseSession ownerSession, String resourceType, String resourcePath) {
		this.ownerSession = ownerSession;
		this.resourceType = resourceType;
		this.resourcePath = resourcePath;
	}

	// Mutateurs
	public Long getId() {
		return id;
	}

	public CourseSession getOwnerSession() {
		return ownerSession;
	}

	public String getResourceType() {
		return resourceType;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerSession(CourseSession ownerSession) {
		this.ownerSession = ownerSession;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

}
