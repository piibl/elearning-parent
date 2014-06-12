package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("T")
public class Teacher extends User {

	public Teacher(){
		
	}
	
	public Teacher(String firstName, String lastName, String login,
			String password, String email) {
		super(firstName, lastName, login, password, email);
	}

	
}
