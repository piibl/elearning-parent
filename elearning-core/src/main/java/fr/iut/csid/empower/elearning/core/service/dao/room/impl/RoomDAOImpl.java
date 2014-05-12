package fr.iut.csid.empower.elearning.core.service.dao.room.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.service.dao.AbstractGenericDAO;
import fr.iut.csid.empower.elearning.core.service.dao.room.RoomDAO;

@Named
public class RoomDAOImpl extends AbstractGenericDAO<Room> implements RoomDAO {

	public RoomDAOImpl() {
		super(Room.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Room> findFreeRooms() {
		List<Room> resultList = em.createNamedQuery(
				getPrefix() + "findFreeRooms").getResultList();
		return resultList;

	}

	@Override
	public Room findByLabel(String roomLabel) {
		try {
			Room result = (Room) em
					.createNamedQuery(getPrefix() + "findByLabel")
					.setParameter("roomLabel", roomLabel).getSingleResult();
			return result;
		} catch (NoResultException e) {
			// Pas de résultat pour ce label
			return null;
		}

	}
}
