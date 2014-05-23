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

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.util.time.DateTimeConverter;

/**
 * Association cours - étudiant réprésentant une inscription d'un étudiant à un cours
 * 
 */
@Entity
@Table(name = "COURSE_SUBSCRIPTION")
public class CourseSubscription {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CourseSubscriptionSeq")
	@SequenceGenerator(name = "CourseSubscriptionSeq", sequenceName = "COURSE_SUBSCRIPTION_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "COURSE_SUBSCRIPTION_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Etudiant
	 */
	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	/**
	 * cours
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	private Course course;

	/**
	 * Date de subscription
	 */
	@Column(name = "SUBSCRIPTION_DATE", columnDefinition = "TIMESTAMP")
	@Converter(name = "dateTimeConverter", converterClass = DateTimeConverter.class)
	@Convert("dateTimeConverter")
	private DateTime subscriptionDate;

	/**
	 * Constructeur par défaut
	 */
	public CourseSubscription() {

	}

	/**
	 * Constructeur
	 * 
	 * @param student
	 *            : étudiant
	 * @param course
	 *            : cours
	 */
	public CourseSubscription(Student student, Course course) {
		this.student = student;
		this.course = course;
		this.subscriptionDate = new DateTime();
	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public Course getCourse() {
		return course;
	}

	public DateTime getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setSubscriptionDate(DateTime date) {
		this.subscriptionDate = date;
	}

}
