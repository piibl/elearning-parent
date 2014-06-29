package fr.iut.csid.empower.elearning.core.service;

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;

public interface UserResearchService {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public EndUser findByLogin(String login);

}
