package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER")
public class Teacher extends AbstractUser {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TeacherSeq")
	@SequenceGenerator(name = "TeacherSeq", sequenceName = "TEACHER_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "TEACHER_ID", nullable = false)
	@Id
	private Long id;

	public Teacher() {

	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
