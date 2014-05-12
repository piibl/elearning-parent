package fr.iut.csid.empower.elearning.core.domain.room;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;

/**
 * salle de cours
 * 
 * @author pblanchard
 */
@Entity
@Table(name = "ROOM")
@NamedQueries(value = {
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.room.Room.findAll", query = "SELECT r FROM Room r"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.room.Room.countAll", query = "SELECT count(r) FROM Room r"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.room.Room.findByLabel", query = "SELECT r FROM Room r WHERE r.label = :roomLabel"),
		@NamedQuery(name = "fr.iut.csid.empower.elearning.core.domain.room.Room.findFreeRooms", query = "SELECT r FROM Room r WHERE (SELECT COUNT(p) FROM r.plannings p) = 0") })
public class Room {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoomSeq")
	@SequenceGenerator(name = "RoomSeq", sequenceName = "ROOM_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ROOM_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Evenements de planning<br/>
	 * Les cours rattachés à la salle sont peu nombreux, on charge l'intégralité des cours associés lorque que l'on récupère l'entité. <br/>
	 * TODO à modifier si associations lourdes.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "room")
	private Set<PlanningEvent> plannings;

	/**
	 * Nom de la salle
	 */
	@Column(name = "LABEL")
	private String label;

	public Room() {

	}

	public Room(String roomsLabel) {
		super();
		this.label = roomsLabel;
		this.plannings = new HashSet<PlanningEvent>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<PlanningEvent> getCourses() {
		return plannings;
	}

	public void setCourses(Set<PlanningEvent> courses) {
		this.plannings = courses;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
