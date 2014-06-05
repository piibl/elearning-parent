package fr.iut.csid.empower.elearning.core.service;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.dto.UserDTO;

public interface TeacherService extends CrudService<Teacher, Long, UserDTO> {

	/**
	 * Retourne l'instance correspondante à ce login
	 * 
	 * @param login
	 *            : login de l'utilisateur recherché
	 * @return
	 */
	public Teacher findByLogin(String login);

}
