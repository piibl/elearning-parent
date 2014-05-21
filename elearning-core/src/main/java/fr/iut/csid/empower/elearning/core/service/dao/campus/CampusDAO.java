package fr.iut.csid.empower.elearning.core.service.dao.campus;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.campus.Campus;

/**
 * Campus DAO
 * 
 * @author Pierre_pers
 */
public interface CampusDAO extends JpaRepository<Campus, Long> {

}
