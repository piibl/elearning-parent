package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.iut.csid.empower.elearning.core.reference.UserRole;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends EndUser {

	public Student() {

	}

	public Student(String firstName, String lastName, String login, String password, String email) {
		super(firstName, lastName, login, password, email, UserRole.STUDENT);
	}

}
