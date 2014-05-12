package fr.iut.csid.empower.elearning.core.domain.planning;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.room.Room;

/**
 * Association Cours-Salle
 * 
 * @author pblanchard
 */
@Entity
@Table(name = "PLANNING_EVENT")
@NamedQueries(value = {
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent.findAll", query = "SELECT p FROM PlanningEvent p"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent.countAll", query = "SELECT count(p) FROM PlanningEvent p"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent.findByRoomDateAndHalfDay", query = "SELECT p FROM PlanningEvent p WHERE p.room = :room AND p.date = :date AND p.targetHalfDay = :halfDay ") })
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
	@PrimaryKeyJoinColumn(name = "COURSE_ID", referencedColumnName = "COURSE_ID")
	private Course course;

	/**
	 * Salle associée
	 */
	@ManyToOne
	@PrimaryKeyJoinColumn(name = "ROOM_ID", referencedColumnName = "ROOM_ID")
	private Room room;

	/**
	 * Date du cours <br/>
	 * TODO à splitter en "StartTimestamp" et "EndTimestamp"
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;

	/**
	 * Demi-journée cible : matin ou après midi. (AM / PM) <br/>
	 * TODO valeurs possibles à externaliser en enum.
	 */
	@Column(name = "HALF_DAY")
	private String targetHalfDay;

	/**
	 * @param course
	 * @param room
	 * @param date
	 * @param targetHalfDay
	 */
	public PlanningEvent(Course course, Room room, Date date, String targetHalfDay) {
		super();
		this.course = course;
		this.room = room;
		this.date = date;
		this.targetHalfDay = targetHalfDay;
	}

	public PlanningEvent() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public Room getRoom() {
		return room;
	}

	public Date getDate() {
		return date;
	}

	public String getTargetHalfDay() {
		return targetHalfDay;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTargetHalfDay(String targetHalfDay) {
		this.targetHalfDay = targetHalfDay;
	}

}
