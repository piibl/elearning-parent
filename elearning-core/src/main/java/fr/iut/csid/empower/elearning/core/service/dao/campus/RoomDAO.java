package fr.iut.csid.empower.elearning.core.service.dao.campus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.iut.csid.empower.elearning.core.domain.campus.Room;

/**
 * Salle DAO
 * 
 * @author Pierre_pers
 * 
 */
public interface RoomDAO extends JpaRepository<Room, Long> {

	/**
	 * Retourne toutes les salles non rattachées à un ou plusieurs cours
	 * 
	 * @return
	 */
	@Query("SELECT r FROM Room r WHERE (SELECT COUNT(p) FROM r.plannings p) = 0")
	public List<Room> findByPlanningsIsNull();

	/**
	 * Retourne la salle correspondant à l'intitulé passé en paramètre
	 * 
	 * @param roomLabel
	 *            intitulé de la salle recherchée
	 * @return
	 */
	public Room findByLabel(String roomLabel);

}
