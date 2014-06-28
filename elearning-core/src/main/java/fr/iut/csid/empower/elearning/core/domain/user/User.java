package fr.iut.csid.empower.elearning.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.joda.time.DateTime;

import fr.iut.csid.empower.elearning.core.util.converter.time.DateTimeConverter;

/**
 * Utilisateurs
 */
@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING, length = 20)
public class User {

	// TODO utilité ?
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserSeq")
	@SequenceGenerator(name = "UserSeq", sequenceName = "USER_SEQ", allocationSize = 1, initialValue = 1)
	@Column(name = "USER_ID", nullable = false)
	@Id
	private Long id;
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

	public User() {

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
	public User(String firstName, String lastName, String login, String password, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.email = email;
		this.subscriptionDate = new DateTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
