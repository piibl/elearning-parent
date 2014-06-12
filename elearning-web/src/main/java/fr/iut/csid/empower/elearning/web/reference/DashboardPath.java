package fr.iut.csid.empower.elearning.web.reference;

/**
 * Chemins des tableaux de bord
 */
public enum DashboardPath {

	/**
	 * Tableau de bord admin
	 */
	ADMIN_DASHBOARD("dashboards/admindashboard"),
	/**
	 * Tableau de bord étudiant
	 */
	STUDENT_DASHBOARD("dashboards/studentdashboard"),
	/**
	 * Tableau de bord enseignant
	 */
	TEACHER_DASHBOARD("dashboards/teacherdashboard"), ;

	private String path;

	private DashboardPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
