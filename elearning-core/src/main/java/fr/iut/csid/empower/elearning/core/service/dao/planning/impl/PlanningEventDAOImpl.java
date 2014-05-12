package fr.iut.csid.empower.elearning.core.service.dao.planning.impl;

import java.util.Date;

import javax.inject.Named;
import javax.persistence.NoResultException;

import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;
import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.service.dao.AbstractGenericDAO;
import fr.iut.csid.empower.elearning.core.service.dao.planning.PlanningEventDAO;

@Named
public class PlanningEventDAOImpl extends AbstractGenericDAO<PlanningEvent>
		implements PlanningEventDAO {

	public PlanningEventDAOImpl() {
		super(PlanningEvent.class);
	}

	@Override
	public PlanningEvent findByRoomDateAndHalfDay(Room room, Date date,
			String targetHalfDay) {
		try {
			PlanningEvent result = (PlanningEvent) em
					.createNamedQuery(getPrefix() + "findByRoomDateAndHalfDay")
					.setParameter("room", room).setParameter("date", date)
					.setParameter("halfDay", targetHalfDay).getSingleResult();
			return result;
		} catch (NoResultException e) {
			// Pas de résultat
			return null;
		}
	}
}
