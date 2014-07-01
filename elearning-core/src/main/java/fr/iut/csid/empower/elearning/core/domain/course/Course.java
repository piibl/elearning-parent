package fr.iut.csid.empower.elearning.core.domain.course;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;

@Entity
@Table(name = "COURSE")
public class Course {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CourseSeq")
	@SequenceGenerator(name = "CourseSeq", sequenceName = "COURSE_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "COURSE_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Intitulé du cours
	 */
	@Column(name = "LABEL")
	private String label;

	/**
	 * Résumé du cours
	 */
	@Lob
	@Column(name = "SUMMARY")
	private String summary;

	/*
	 * Données volatiles, à alimenter par les services si besoin
	 */

	/**
	 * Affiliation du cours à un enseignant
	 */
	@Transient
	private List<CourseTeaching> coursesTeaching;
	/**
	 * Souscriptions aux cours
	 */
	@Transient
	private List<CourseSubscription> subscriptions;
	/**
	 * Chapitres du cours
	 */
	@Transient
	private List<CourseSession> sessions;

	public Course() {

	}

	public Course(String label, String summary) {
		super();
		this.label = label;
		this.summary = summary;
	}

	// MUTATEURS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<CourseTeaching> getCoursesTeaching() {
		return coursesTeaching;
	}

	public List<CourseSubscription> getSubscriptions() {
		return subscriptions;
	}

	public List<CourseSession> getSessions() {
		return sessions;
	}

	public void setCoursesTeaching(List<CourseTeaching> coursesTeaching) {
		this.coursesTeaching = coursesTeaching;
	}

	public void setSubscriptions(List<CourseSubscription> courseSubscriptions) {
		this.subscriptions = courseSubscriptions;
	}

	public void setSessions(List<CourseSession> sessions) {
		this.sessions = sessions;
	}

}
