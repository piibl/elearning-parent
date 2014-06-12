package fr.iut.csid.empower.elearning.core.domain.course.session.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;

@Entity
@Table(name = "SESSION_MARK")
public class Mark {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SessionMarkSeq")
	@SequenceGenerator(name = "SessionMarkSeq", sequenceName = "SESSION_MARK_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "SESSION_MARK_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Resource rendue par l'élève
	 */
	// TODO split ressource en deux : contrôles / supports de cours
	@OneToOne
	@JoinColumn(name = "EVALUATION_RESPONSE_ID")
	private EvaluationResponse evaluationResponse;

	/**
	 * Sujet de l'évaluation
	 */
	// TODO split ressource en deux : contrôles / supports de cours
	@OneToOne
	@JoinColumn(name = "EVALUATION_SUBJECT_ID")
	private EvaluationSubject evaluationSubject;

	/**
	 * Etudiant à qui la note est attribuée
	 */
	@ManyToOne
	@JoinColumn(name = "STUDENT_ID")
	private Student student;

	/**
	 * Session à laquelle appartient la note
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_SESSION_ID")
	private CourseSession session;

	/**
	 * Enseignant qui a attribué la note, en v1 enseignant propriétaire du cours
	 */
	@ManyToOne
	@JoinColumn(name = "TEACHER_ID")
	private Teacher teacher;

	/**
	 * Note
	 */
	@Column(name = "MARK_VALUE")
	private Double markValue;

	/**
	 * Appréciation de l'enseignant
	 */
	@Column(name = "COMMENT")
	private String comment;

	public Mark() {

	}

	/**
	 * Constructeur
	 * 
	 * @param evaluationResponse
	 * @param evaluationSubject
	 * @param student
	 * @param session
	 * @param teacher
	 * @param markValue
	 * @param comment
	 */
	public Mark(EvaluationResponse evaluationResponse, EvaluationSubject evaluationSubject, Student student, CourseSession session, Teacher teacher,
			Double markValue, String comment) {
		this.evaluationResponse = evaluationResponse;
		this.evaluationSubject = evaluationSubject;
		this.student = student;
		this.session = session;
		this.teacher = teacher;
		this.markValue = markValue;
		this.comment = comment;
	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public EvaluationResponse getEvaluationResponse() {
		return evaluationResponse;
	}

	public EvaluationSubject getEvaluationSubject() {
		return evaluationSubject;
	}

	public Student getStudent() {
		return student;
	}

	public CourseSession getSession() {
		return session;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public Double getMarkValue() {
		return markValue;
	}

	public String getComment() {
		return comment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEvaluationResponse(EvaluationResponse evaluationResponse) {
		this.evaluationResponse = evaluationResponse;
	}

	public void setEvaluationSubject(EvaluationSubject evaluationSubject) {
		this.evaluationSubject = evaluationSubject;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setSession(CourseSession session) {
		this.session = session;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setMarkValue(Double markValue) {
		this.markValue = markValue;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
