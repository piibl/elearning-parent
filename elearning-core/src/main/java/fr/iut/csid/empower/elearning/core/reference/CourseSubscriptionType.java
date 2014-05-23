package fr.iut.csid.empower.elearning.core.reference;

/**
 * Type d'une souscription à un cours
 * @author pblanchard
 *
 */
public enum CourseSubscriptionType {
	
	CONSULTATIVE(1, "Consultative"), PARTICIPATIVE(2, "Participative");
	
	/**
	 * Identifiant du type
	 */
	private int id;
	/**
	 * Intitulé du type
	 */
	private String label;
	
	/**
	 * Constructeur
	 * @param id : identifiant
	 * @param label : intitulé du statut
	 */
	private CourseSubscriptionType(int id, String label) {
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
