package fr.iut.csid.empower.elearning.core.reference;

/**
 * Type d'une souscription à un cours
 * 
 * @author pblanchard
 */
public enum ResourceType {

	EXAM("E", "examen"), TEXT_MATERIAL("TM", "support de cours"), PICTURE_MATERIAL("PM", "support de cours"), VIDEO_MATERIAL("VM", "support de cours");

	/**
	 * Identifiant du type
	 */
	private String id;
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
	private ResourceType(String id, String label) {
		this.id = id;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

}
