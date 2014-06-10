package fr.iut.csid.empower.elearning.core.dto.impl;

import java.io.Serializable;

import fr.iut.csid.empower.elearning.core.dto.IDTO;

/**
 * Objet tampon servant à la conversion Json -> Objet
 */
public class UserDTO implements Serializable, IDTO {

	private String firstName;

	/**
	 * Nom de l'utilisateur
	 */

	private String lastName;

	/**
	 * Login de l'utilisateur
	 */

	private String login;

	/**
	 * TODO Cryptage, stockage du hash en bdd
	 */

	private String password;

	private String email;

	// Mutateurs

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
