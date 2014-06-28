package fr.iut.csid.empower.elearning.core.reference;

/**
 * Type d'une souscription à un cours
 * 
 * @author pblanchard
 */
public enum ResourceType {

	EVALUATION(1, "contrôle"), COURSE_SUPPORT(2, "support de cours");

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
	 * 
	 * @param id
	 *            : identifiant
	 * @param label
	 *            : intitulé du statut
	 */
	private ResourceType(int id, String label) {
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
