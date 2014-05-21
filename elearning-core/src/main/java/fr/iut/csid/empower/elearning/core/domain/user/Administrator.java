package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Administrateur
 * 
 * @author pblanchard
 */
@Entity
@Table(name = "ADMINISTRATOR")
public class Administrator extends AbstractUser {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AdministratorSeq")
	@SequenceGenerator(name = "AdministratorSeq", sequenceName = "ADMINISTRATOR_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "ADMINISTRATOR_ID", nullable = false)
	@Id
	private Long id;

	public Administrator() {

	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
