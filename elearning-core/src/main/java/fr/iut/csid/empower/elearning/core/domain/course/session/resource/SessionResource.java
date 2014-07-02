package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import java.io.InputStream;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

/**
 * Ressource, objet contenant les pointeurs vers les fichiers stockés physiquement en base Mongodb
 */
@Entity
@Table(name = "SESSION_RESOURCE")
public class SessionResource {

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
	 * Nom de la ressource physique<br/>
	 * Sert à retrouver la ressource dans la base mongodb;
	 */
	@Column(name = "STORED_FILENAME")
	private String storedFilename;

	/**
	 * Nom du fichier d'origine, affiché dans les pages de rendu html
	 */
	@Column(name = "ORIGINAL_FILENAME")
	private String originalFilename;

	/**
	 * Type du fichier
	 */
	@Column(name = "FILETYPE")
	private String fileType;
	/**
	 * Taille du fichier (en kb)
	 */
	@Column(name = "FILESIZE")
	private Long fileSize;

	/**
	 * Flux contenant le fichie physique <br/>
	 * !! à manipuler avec précaution, il ne doit être alimenté qu'à la demande par un service !!!
	 */
	@JsonIgnore
	@Transient
	private InputStream contentStream;

	public SessionResource() {

	}

	/**
	 * @param ownerSession
	 * @param storedFilename
	 * @param originalFilename
	 * @param fileType
	 * @param fileSize
	 */
	public SessionResource(CourseSession ownerSession, String storedFilename, String originalFilename, String fileType, Long fileSize) {
		this.ownerSession = ownerSession;
		this.storedFilename = storedFilename;
		this.originalFilename = originalFilename;
		this.fileType = fileType;
		this.fileSize = fileSize;
	}

	public Long getId() {
		return id;
	}

	public CourseSession getOwnerSession() {
		return ownerSession;
	}

	public String getStoredFilename() {
		return storedFilename;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public String getFileType() {
		return fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOwnerSession(CourseSession ownerSession) {
		this.ownerSession = ownerSession;
	}

	public void setStoredFilename(String storedFilename) {
		this.storedFilename = storedFilename;
	}

	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public InputStream getContentStream() {
		return contentStream;
	}

	public void setContentStream(InputStream contentStream) {
		this.contentStream = contentStream;
	}

}
