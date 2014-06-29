package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;

/**
 * Repository administrateur
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

	/**
	 * Retourne l'enseignant correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public Teacher findByLogin(String login);

}
