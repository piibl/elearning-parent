package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;

/**
 * Repository administrateur
 */
public interface UserRepository extends JpaRepository<EndUser, Long> {

	/**
	 * Retourne l'enseignant correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public EndUser findByLogin(String login);

}
