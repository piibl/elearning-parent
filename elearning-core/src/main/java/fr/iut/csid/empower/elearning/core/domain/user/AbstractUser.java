package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

/**
 * Classe abstraite des entités utilisateur
 */
@MappedSuperclass
public abstract class AbstractUser implements User {

	// TODO utilité ??
	/**
	 * Prénom de l'utilisateur
	 */
	@Column(name = "FIRST_NAME")
	protected String firstName;

	/**
	 * Nom de l'utilisateur
	 */
	@Column(name = "LAST_NAME")
	protected String lastName;

	/**
	 * Login de l'utilisateur
	 */
	@Column(name = "LOGIN", nullable = false, unique = true)
	protected String login;

	/**
	 * TODO Cryptage, stockage du hash en bdd
	 */
	@Column(name = "PWD")
	protected String password;

	@Column(name = "EMAIL")
	protected String email;

	/**
	 * Date de subscription
	 */
	@Column(name = "SUBSCRIPTION_DATE", columnDefinition = "TIMESTAMP")
	@Convert(converter = DateTimeConverter.class)
	protected DateTime subscriptionDate;

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
	public AbstractUser(String firstName, String lastName, String login, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.subscriptionDate = new DateTime();
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

	public DateTime getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(DateTime subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

}
