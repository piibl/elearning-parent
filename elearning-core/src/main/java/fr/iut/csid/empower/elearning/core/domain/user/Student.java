package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class Student extends User {

	public Student(){
		
	}
	
	public Student(String firstName, String lastName, String login,
			String password, String email) {
		super(firstName, lastName, login, password, email);
	}

	
}
