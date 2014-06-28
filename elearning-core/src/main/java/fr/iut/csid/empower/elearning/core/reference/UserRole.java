package fr.iut.csid.empower.elearning.core.reference;

/**
 * Rôle d'un utilisateur
 */
public enum UserRole {

	ADMINISTRATOR("ADMINISTRATOR"), TEACHER("TEACHER"), STUDENT("STUDENT");

	/**
	 * Identifiant du role
	 */
	private String role;

	/**
	 * @param role
	 */
	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
