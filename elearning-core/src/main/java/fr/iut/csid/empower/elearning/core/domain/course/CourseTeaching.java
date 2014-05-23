package fr.iut.csid.empower.elearning.core.domain.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;

/**
 * Association cours - enseignant réprésentant l'enseignement d'un cours par un professeur
 * 
 */
@Entity
@Table(name = "COURSE_TEACHING")
public class CourseTeaching {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CourseTeachingSeq")
	@SequenceGenerator(name = "CourseTeachingSeq", sequenceName = "COURSE_TEACHING_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "COURSE_TEACHING_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Enseignant
	 */
	@ManyToOne
	@JoinColumn(name = "TEACHER_ID")
	private Teacher teacher;

	/**
	 * cours
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	private Course course;

	// /**
	// * Date de subscription
	// */
	// @Column(name = "SUBSCRIPTION_DATE", columnDefinition = "TIMESTAMP")
	// @Converter(name = "dateTimeConverter", converterClass = DateTimeConverter.class)
	// @Convert("dateTimeConverter")
	// private DateTime subscriptionDate;

	/**
	 * Constructeur par défaut
	 */
	public CourseTeaching() {

	}

	/**
	 * Constructeur
	 * 
	 * @param teacher
	 *            : étudiant
	 * @param course
	 *            : cours
	 */
	public CourseTeaching(Teacher teacher, Course course) {
		this.teacher = teacher;
		this.course = course;
	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

}
