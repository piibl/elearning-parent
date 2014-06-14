package fr.iut.csid.empower.elearning.core.domain.course.session;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CourseSessionSeq")
	@SequenceGenerator(name = "CourseSessionSeq", sequenceName = "COURSE_SESSION_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "COURSE_SESSION_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Intitulé de la session
	 */
	@Column(name = "LABEL")
	private String label;

	/**
	 * Cours auquel appartient la session
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	private Course ownerCourse;

	/**
	 * Rang de la session par rapport aux autres sessions du même cours
	 */
	@Column(name = "RANK")
	private int sessionRank;

	/**
	 * Description de la session
	 */
	@Lob
	@Column(name = "SUMMARY")
	private String summary;

	/**
	 * Date de début de la session
	 */
	@Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime startDate;

	/**
	 * Date de fin de la session
	 */
	@Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime endDate;

	public CourseSession() {

	}

	/**
	 * Constructeur
	 * 
	 * @param label
	 *            : intitulé de la session
	 * @param ownerCourse
	 *            : cours propriétaire de la session
	 * @param sessionRank
	 *            : rang de la session
	 * @param startDate
	 *            : date de début de la session
	 * @param endDate
	 *            : date de fin de la session
	 */
	public CourseSession(String label, Course ownerCourse, int sessionRank, DateTime startDate, DateTime endDate, String summary) {
		this.label = label;
		this.ownerCourse = ownerCourse;
		this.sessionRank = sessionRank;
		this.startDate = startDate;
		this.endDate = endDate;
		this.summary = summary;
	}

	// Mutateurs

	public Long getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public Course getOwnerCourse() {
		return ownerCourse;
	}

	public int getSessionRank() {
		return sessionRank;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOwnerCourse(Course ownerCourse) {
		this.ownerCourse = ownerCourse;
	}

	public void setSessionRank(int sessionRank) {
		this.sessionRank = sessionRank;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
