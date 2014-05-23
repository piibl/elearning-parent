package fr.iut.csid.empower.elearning.core.reference;

/**
 * Statuts possibles pour une souscription à un cours
 * @author pblanchard
 *
 */
public enum CourseSubscriptionStatus {
	
	IN_PROGRESS(1, "In progress"), VALIDATED(2, "Validated"), CANCELLED(3, "Cancelled");
	
	/**
	 * Identifiant du status
	 */
	private int id;
	/**
	 * Intitulé du status
	 */
	private String label;
	
	/**
	 * Constructeur
	 * @param id : identifiant
	 * @param label : intitulé du statut
	 */
	private CourseSubscriptionStatus(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	

}
