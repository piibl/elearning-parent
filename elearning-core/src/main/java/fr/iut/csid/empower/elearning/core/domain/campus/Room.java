package fr.iut.csid.empower.elearning.core.domain.campus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * salle de cours
*
 */
@Entity
@Table(name = "ROOM")
public class Room {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RoomSeq")
	@SequenceGenerator(name = "RoomSeq", sequenceName = "ROOM_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ROOM_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Nom de la salle
	 */
	@Column(name = "LABEL")
	private String label;

	/**
	 * TODO capacité, accessibilité, équipements, etc.......
	 */

	/**
	 * Campus de rattachement
	 */
	@ManyToOne
	@JoinColumn(name = "CAMPUS_ID")
	private Campus campus;

	public Room() {

	}

	public Room(String label, Campus campus) {
		super();
		this.label = label;
		this.campus = campus;
	}

	// Mutateurs
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
