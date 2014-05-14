package fr.iut.csid.empower.elearning.core.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;
import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.service.dao.planning.PlanningEventDAO;
import fr.iut.csid.empower.elearning.core.service.dao.room.RoomDAO;

/**
 * Service de gestion de planning.
 * 
 * @author Pierre_pers
 * 
 */
@Named
public class PlanningService {

	/**
	 * Logger.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(PlanningService.class);

	/**
	 * Dao pour évènement de planning.
	 */
	@Inject
	private PlanningEventDAO planningDAO;
	/**
	 * Dao pour évènement de planning.
	 */
	@Inject
	private RoomDAO roomDAO;

	/**
	 * Réserve une salle pour un cours, une date et une demi-journée donnés.
	 * 
	 * @param course
	 *            cours
	 * @param room
	 *            salle
	 * @param date
	 *            date
	 * @param targetHalfDay
	 *            demi-journée
	 */
	@Transactional
	public void bookRoom(Course course, Room room, Date date,
			String targetHalfDay) {

		// Formateur de date
		// TODO à externaliser dans une classe d'utilitaire
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		PlanningEvent planning = planningDAO.findByRoomAndDateAndTargetHalfDay(
				room, date, targetHalfDay);
		// Si le créneau est libre
		if (planning == null) {
			planning = new PlanningEvent(course, room, date, targetHalfDay);
			planningDAO.save(planning);
			room.getCourses().add(planning);
			roomDAO.save(room);

			logger.info("Room [" + room.getLabel() + "] is now booked at ["
					+ dateFormat.format(date) + " - " + targetHalfDay
					+ "] for [" + course.getLabel() + "] course.");
		} else {
			logger.info("Room [" + room.getLabel() + "] is already booked at ["
					+ dateFormat.format(date) + " - " + targetHalfDay + "]");

		}

	}

	/**
	 * Annule un évènement de planning.
	 * 
	 * @param planning
	 *            évènement de planning
	 */
	public void cancelBooking(PlanningEvent planning) {
		/**
		 * TODO Code métier : suppression de l'évènement si il existe,
		 * suppression du cours pour les étudiants
		 */
		planningDAO.delete(planning);

	}
}
