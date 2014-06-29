package fr.iut.csid.empower.elearning.core.service.dao.campus.planning;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.campus.planning.PlanningEvent;

/**
 * PlanningEvent Event Repository
 * 
 * @author Pierre_pers
 */
public interface PlanningEventRepository extends JpaRepository<PlanningEvent, Long> {

}
