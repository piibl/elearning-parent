package fr.iut.csid.empower.elearning.web.reference;

/**
 * Chemins des tableaux de bord
 */
public enum DashboardPath {

	/**
	 * Tableau de bord admin
	 */
	ADMIN_DASHBOARD("dashboards/admin"),
	/**
	 * Tableau de bord étudiant
	 */
	STUDENT_DASHBOARD("dashboards/student"),
	/**
	 * Tableau de bord enseignant
	 */
	TEACHER_DASHBOARD("dashboards/teacher"), ;

	private String path;

	private DashboardPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
