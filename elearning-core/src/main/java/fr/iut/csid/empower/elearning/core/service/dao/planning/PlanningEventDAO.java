package fr.iut.csid.empower.elearning.core.service.dao.planning;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;

/**
 * PlanningEvent Event DAO
 * 
 * @author Pierre_pers
 */
public interface PlanningEventDAO extends JpaRepository<PlanningEvent, Long> {

}
