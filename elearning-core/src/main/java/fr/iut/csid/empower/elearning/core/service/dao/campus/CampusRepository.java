package fr.iut.csid.empower.elearning.core.service.dao.campus;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.campus.Campus;

/**
 * Campus Repository
 * 
 * @author Pierre_pers
 */
public interface CampusRepository extends JpaRepository<Campus, Long> {

}
