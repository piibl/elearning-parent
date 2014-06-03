package fr.iut.csid.empower.elearning.web.reference;

/**
 * Centralisation des "bouts" d'url pour accéder aux ressources
 */
public enum PathFragment {

	// Racine des templates thymeleaf
	BASE("pages/"), REDIRECT("redirect:/"),
	// paths métier
	NEW("new"), EDIT("edit"), DELETE("delete");

	private String name;

	private PathFragment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
