package fr.iut.csid.empower.elearning.core.domain.course;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionStatus;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;
import fr.iut.csid.empower.elearning.core.util.converter.reference.CourseSubscriptionStatusConverter;
import fr.iut.csid.empower.elearning.core.util.converter.reference.CourseSubscriptionTypeConverter;
import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

/**
 * Association cours - étudiant réprésentant une inscription d'un étudiant à un cours
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
	 * Statut de la souscription
	 */
	@Column(name = "STATUS")
	@Convert(converter = CourseSubscriptionStatusConverter.class)
	private CourseSubscriptionStatus status;

	/**
	 * Type de la souscription
	 */
	@Column(name = "TYPE")
	@Convert(converter = CourseSubscriptionTypeConverter.class)
	private CourseSubscriptionType type;

	/**
	 * Date de subscription
	 */
	@Column(name = "SUBSCRIPTION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
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
	public CourseSubscription(Student student, Course course, CourseSubscriptionType subscriptionType) {
		this.student = student;
		this.course = course;
		this.status = CourseSubscriptionStatus.IN_PROGRESS;
		this.type = subscriptionType;
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
