package fr.iut.csid.empower.elearning.core.service.dao.room;

import java.util.List;

import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.service.dao.GenericDAO;

/**
 * Salle DAO
 * 
 * @author Pierre_pers
 * 
 */
public interface RoomDAO extends GenericDAO<Room> {

	/**
	 * Retourne toutes les salles non rattachées à un ou plusieurs cours
	 * 
	 * @return
	 */
	public List<Room> findFreeRooms();

	/**
	 * Retourne la salle correspondant à l'intitulé passé en paramètre
	 * 
	 * @param roomLabel
	 *            intitulé de la salle recherchée
	 * @return
	 */
	public Room findByLabel(String roomLabel);

}
