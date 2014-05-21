package fr.iut.csid.empower.elearning.core.domain.campus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Campus = lieu avec plusieurs salles
 * 
 * @author pblanchard
 */
@Entity
@Table(name = "CAMPUS")
public class Campus {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CampusSeq")
	@SequenceGenerator(name = "CampusSeq", sequenceName = "CAMPUS_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "CAMPUS_ID", nullable = false)
	@Id
	private Long id;

	/**
	 * Nom du campus
	 */
	@Column(name = "NAME")
	private String campusName;

	/**
	 * TODO adresses + infos administratives
	 */
	public Campus() {

	}

	public Campus(String campusName) {
		super();
		this.campusName = campusName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

}
