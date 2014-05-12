package fr.iut.csid.empower.elearning.core.domain.student;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.course.Course;

/**
 * Etudiant
 * 
 * @author pblanchard
 */
@Entity
@Table(name = "STUDENT")
@NamedQueries(value = { @NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.student.Student.findAll", query = "SELECT s FROM Student s"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.student.Student.countAll", query = "SELECT count(s) FROM Student s") })
public class Student {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentSeq")
	@SequenceGenerator(name = "StudentSeq", sequenceName = "STUDENT_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "STUDENT_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Prénom de l'étudiant
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * Nom de famille de l'étudiant
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Cours auquels l'étudiant est inscrit
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "COURSE_REGISTRATION", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
	private Set<Course> courses;

	public Student() {

	}

	public Student(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.courses = new HashSet<Course>();
	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Set<Course> getCourses() {
		return this.courses;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

}
