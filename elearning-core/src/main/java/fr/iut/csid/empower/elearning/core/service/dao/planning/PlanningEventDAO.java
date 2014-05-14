package fr.iut.csid.empower.elearning.core.service.dao.planning;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;
import fr.iut.csid.empower.elearning.core.domain.room.Room;

/**
 * PlanningEvent Event DAO
 * 
 * @author Pierre_pers
 * 
 */
public interface PlanningEventDAO extends JpaRepository<PlanningEvent, Long> {

	/**
	 * Retourne l'évènement se déroulant aux horaires et dans la salle indiqués
	 * si celui-ci existe
	 * 
	 * @param room
	 * @param date
	 * @param targetHalfDay
	 * @return
	 */
	public PlanningEvent findByRoomAndDateAndTargetHalfDay(Room room,
			Date date, String targetHalfDay);

}
