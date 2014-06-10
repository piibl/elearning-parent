package fr.iut.csid.empower.elearning.core.domain.course.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SESSION_RESOURCE")
public class SessionResource {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SessionResourceEventSeq")
	@SequenceGenerator(name = "SessionResourceEventSeq", sequenceName = "SESSION_RESOURCE_EVENT_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "SESSION_RESOURCE_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Session propriétaire de la ressource
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_SESSION_ID")
	private CourseSession ownerSession;

	/**
	 * Type de la ressource TODO enum
	 */
	@Column(name = "RESOURCE_TYPE")
	private String resourceType;

	/**
	 * Path de la ressource
	 */
	@Column(name = "RESOURCE_PATH")
	private String resourcePath;

	public SessionResource() {

	}

	/**
	 * @param ownerSession
	 * @param resourceType
	 * @param resourcePath
	 */
	public SessionResource(CourseSession ownerSession, String resourceType, String resourcePath) {
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
