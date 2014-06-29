package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;

public interface UserService {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public EndUser findByLogin(String login);

}
