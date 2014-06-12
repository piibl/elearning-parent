package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

@Entity
@Table(name = "SESSION_RESOURCE")
public class Resource {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SessionResourceSeq")
	@SequenceGenerator(name = "SessionResourceSeq", sequenceName = "SESSION_RESOURCE_SEQ", allocationSize = 1, initialValue = 1)
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
	@Column(name = "TYPE")
	private String type;

	/**
	 * Path de la ressource
	 */
	@Column(name = "PATH")
	private String path;

	public Resource() {

	}

	/**
	 * @param ownerSession
	 * @param type
	 * @param path
	 */
	public Resource(CourseSession ownerSession, String resourceType, String resourcePath) {
		this.ownerSession = ownerSession;
		this.type = resourceType;
		this.path = resourcePath;
	}

	// Mutateurs
	public Long getId() {
		return id;
	}

	public CourseSession getOwnerSession() {
		return ownerSession;
	}

	public String getResourceType() {
		return type;
	}

	public String getResourcePath() {
		return path;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerSession(CourseSession ownerSession) {
		this.ownerSession = ownerSession;
	}

	public void setResourceType(String resourceType) {
		this.type = resourceType;
	}

	public void setResourcePath(String resourcePath) {
		this.path = resourcePath;
	}

}
