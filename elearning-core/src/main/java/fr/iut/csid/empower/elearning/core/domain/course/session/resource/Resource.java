package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mongodb.gridfs.GridFSDBFile;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.reference.ResourceType;

/**
 * Ressource, objet contenant les pointeurs vers les fichiers stockés physiquement en base Mongodb
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
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
	protected CourseSession ownerSession;

	/**
	 * Nom de la ressource physique<br/>
	 * Sert à retrouver la ressource dans la base mongodb;
	 */
	@Column(name = "NAME")
	protected String name;

	@Column(name = "TYPE")
	protected ResourceType type;

	/**
	 * Description de la ressource
	 */
	@Lob
	@Column(name = "SUMMARY")
	protected String summary;

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
	protected Resource(CourseSession ownerSession, String resourceName, String summary, ResourceType type) {
		this.ownerSession = ownerSession;
		this.name = resourceName;
		this.summary = summary;
		this.type = type;
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

	public String getSummary() {
		return summary;
	}

	public GridFSDBFile getResource() {
		return resource;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setResource(GridFSDBFile resource) {
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public ResourceType getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

}
