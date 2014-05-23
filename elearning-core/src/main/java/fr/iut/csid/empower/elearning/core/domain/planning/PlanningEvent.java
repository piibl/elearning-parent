package fr.iut.csid.empower.elearning.core.domain.planning;

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

import fr.iut.csid.empower.elearning.core.domain.campus.ClassRoom;
import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

/**
 * Association Cours-Salle
 * 
 */
@Entity
@Table(name = "PLANNING_EVENT")
public class PlanningEvent {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PlanningEventSeq")
	@SequenceGenerator(name = "PlanningEventSeq", sequenceName = "PLANNING_EVENT_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "EVENT_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Cours sujet
	 */
	@ManyToOne
	@JoinColumn(name = "COURSE_ID")
	private Course course;

	/**
	 * Salle associée
	 */
	@ManyToOne
	@JoinColumn(name = "ROOM_ID")
	private ClassRoom classRoom;

	/**
	 * Date de départ
	 */
	@Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime startDate;

	/**
	 * Date de fin
	 */
	@Column(name = "END_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	private DateTime endDate;

	public PlanningEvent() {

	}

	// MUTATEURS
	public Long getId() {
		return id;
	}

	public Course getCourse() {
		return course;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
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

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

}
