package fr.iut.csid.empower.elearning.core.domain.course;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;
import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * Cours
 * 
 * @author pblanchard
 */
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
	 * Etudiants inscrits au cours
	 */
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students;

	/**
	 * Evenements de cours enregistrés
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
	private Set<PlanningEvent> plannings;

	public Course() {

	}

	public Course(String label) {
		super();
		this.label = label;
		this.students = new HashSet<Student>();
		this.plannings = new HashSet<PlanningEvent>();
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<PlanningEvent> getRooms() {
		return plannings;
	}

	public void setRooms(Set<PlanningEvent> plannings) {
		this.plannings = plannings;
	}

}
