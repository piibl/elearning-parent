package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.iut.csid.empower.elearning.core.reference.UserRole;

@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Administrator extends EndUser {

	public Administrator() {

	}

	public Administrator(String firstName, String lastName, String login, String password, String email) {
		super(firstName, lastName, login, password, email, UserRole.ADMINISTRATOR);
	}

}
