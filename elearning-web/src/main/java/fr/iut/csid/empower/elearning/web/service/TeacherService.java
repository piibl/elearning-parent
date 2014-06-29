package fr.iut.csid.empower.elearning.web.service;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;

public interface TeacherService extends DTOSupport<Teacher, Long, UserDTO> {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public Teacher findByLogin(String login);

}
