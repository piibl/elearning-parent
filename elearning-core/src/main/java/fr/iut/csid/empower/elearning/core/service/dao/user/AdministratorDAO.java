package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;

/**
 * DAO administrateur
 */
public interface AdministratorDAO extends JpaRepository<Administrator, Long> {

	/**
	 * Retourne l'administrateur correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public Administrator findByLogin(String login);

}
