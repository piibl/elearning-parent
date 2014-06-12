package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.User;

/**
 * DAO administrateur
 */
public interface UserDAO extends JpaRepository<User, Long> {

	/**
	 * Retourne l'enseignant correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public User findByLogin(String login);

}
