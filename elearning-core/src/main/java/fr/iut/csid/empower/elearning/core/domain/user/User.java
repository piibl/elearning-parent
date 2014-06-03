package fr.iut.csid.empower.elearning.core.domain.user;

import org.joda.time.DateTime;

/**
 * TODO Javadoc
 */
public interface User {

	public String getLastName();

	public void setFirstName(String firstName);

	public void setLastName(String lastName);

	public String getLogin();

	public String getPassword();

	public String getEmail();

	public void setLogin(String login);

	public void setPassword(String password);

	public void setEmail(String email);

	public DateTime getSubscriptionDate();

	public void setSubscriptionDate(DateTime subscriptionDate);
}
