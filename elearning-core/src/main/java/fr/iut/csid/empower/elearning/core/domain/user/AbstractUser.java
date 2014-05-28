package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Classe abstraite des entités utilisateur
 */
@MappedSuperclass
public abstract class AbstractUser {

	// TODO utilité ??
	/**
	 * Prénom de l'utilisateur
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * Nom de l'utilisateur
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * Login de l'utilisateur
	 */
	@Column(name = "LOGIN")
	private String login;

	/**
	 * TODO Cryptage, stockage du hash en bdd
	 */
	@Column(name = "PWD")
	private String password;

	@Column(name = "EMAIL")
	private String email;

	public AbstractUser() {

	}

	// MUTATEURS

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Constructeur
	 * 
	 * @param login
	 *            : login de l'utilisateur
	 * @param password
	 *            : mot de passe de l'utilisateur
	 * @param email
	 *            : email de l'utilisateur
	 */
	public AbstractUser(String login, String password, String email) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
