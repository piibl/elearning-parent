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
import javax.persistence.Transient;

import com.mongodb.gridfs.GridFSDBFile;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

/**
 * Ressource, objet contenant les pointeurs vers les fichiers stockés physiquement en base Mongodb
 * 
 * @author A547891
 */
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
	 * Nom de la ressource physique<br/>
	 * Sert à retrouver la ressource dans la base mongodb;
	 */
	@Column(name = "NAME")
	private String resourceName;

	/**
	 * Resource physique <br/>
	 * Aucun référencement persistant, c'est au service {@link ResourceService} de manager et de charger les resources physiques
	 */
	@Transient
	private GridFSDBFile resource;

	public Resource() {

	}

	/**
	 * @param ownerSession
	 * @param type
	 * @param path
	 */
	public Resource(CourseSession ownerSession, String resourceType, String resourceName) {
		this.ownerSession = ownerSession;
		this.type = resourceType;
		this.resourceName = resourceName;
	}

	// Mutateurs
	public Long getId() {
		return id;
	}

	public CourseSession getOwnerSession() {
		return ownerSession;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerSession(CourseSession ownerSession) {
		this.ownerSession = ownerSession;
	}

	public String getType() {
		return type;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

}
