package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;

/**
 * DAO administrateur
 */
public interface AdministratorDAO extends JpaRepository<Administrator, Long> {

}
