package fr.iut.csid.empower.elearning.core.domain.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	public Course() {

	}

	public Course(String label) {
		super();
		this.label = label;
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

}
