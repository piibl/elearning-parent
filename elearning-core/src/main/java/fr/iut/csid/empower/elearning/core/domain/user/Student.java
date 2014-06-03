package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student extends AbstractUser {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "StudentSeq")
	@SequenceGenerator(name = "StudentSeq", sequenceName = "STUDENT_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "STUDENT_ID", nullable = false)
	@Id
	private Long id;

	public Student() {

	}

	public Student(String firstName, String lastName, String login, String password, String email) {
		super(firstName, lastName, login, password, email);
	}

	// MUTATEURS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
