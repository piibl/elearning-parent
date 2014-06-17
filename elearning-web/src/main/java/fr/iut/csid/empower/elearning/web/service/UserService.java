package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.user.User;

public interface UserService {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public User findByLogin(String login);

}
