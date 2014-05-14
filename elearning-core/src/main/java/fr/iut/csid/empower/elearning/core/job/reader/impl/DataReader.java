package fr.iut.csid.empower.elearning.core.job.reader.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.planning.PlanningEvent;
import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.job.reader.Reader;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.planning.PlanningEventDAO;
import fr.iut.csid.empower.elearning.core.service.dao.room.RoomDAO;
import fr.iut.csid.empower.elearning.core.service.dao.student.StudentDAO;

/**
 * Job d'accès aux données
 * 
 * @author pblanchard
 * 
 */
@Named
public class DataReader implements Reader {

	private static Logger logger = LoggerFactory.getLogger(DataReader.class);

	@Inject
	private StudentDAO studentDAO;
	@Inject
	private CourseDAO courseDAO;
	@Inject
	private RoomDAO roomDAO;
	@Inject
	private PlanningEventDAO planningDAO;

	/**
	 * Job factice d'accées aux données (consultation)...
	 */
	@Override
	public void process() {
		// Tracer le contenu de la base concernant les trois principales entités
		showDbContent();
		// Spécificités tp2
		showSpecificities();
		// Executions des requetes nommées, illustration de l'accès aux données
		showNamedQueriesWork();

	}

	private void showDbContent() {
		// Trace tout ce qui est en base... pour les entités déclarées.
		// TODO A génériciser ! classloader et reflexivité....
		StringBuilder container = new StringBuilder();

		for (Student student : studentDAO.findAll()) {
			container.append("[" + student.getFirstName() + " "
					+ student.getLastName() + "]");
		}
		logger.info("[" + studentDAO.count() + "] student(s) found : ["
				+ container.toString() + "]");
		// Efficience ?
		container.delete(0, container.length());

		for (Course course : courseDAO.findAll()) {
			container.append("[" + course.getLabel() + "]");
		}
		logger.info("[" + courseDAO.count() + "] course(s) found : ["
				+ container.toString() + "]");
		container.delete(0, container.length());

		for (Room room : roomDAO.findAll()) {
			container.append("[" + room.getLabel() + "]");
		}
		logger.info("[" + roomDAO.count() + "] classroom(s) found : ["
				+ container.toString() + "]");
		for (PlanningEvent event : planningDAO.findAll()) {
			container.append("[" + event.getCourse() + "]");
		}
		logger.info("[" + planningDAO.count() + "] planning event(s) found : ["
				+ container.toString() + "]");
	}

	/**
	 * Répond aux joyeusetés du tp2
	 */
	private void showSpecificities() {
		StringBuilder container = new StringBuilder();

		// Tous les cours ayant moins de trois étudiants
		// TODO paramétrable
		Long maxSubscription = 3L;
		List<Course> courses = courseDAO
				.findByStudentsLessThan(maxSubscription);
		for (Course course : courses) {
			container.append("[" + course.getLabel() + "]");
		}
		logger.info("[" + courses.size() + "] course(s) with less than ["
				+ maxSubscription + "] subscription(s) found : ["
				+ container.toString() + "]");
		container.delete(0, container.length());

		// Toutes les salles libres
		// TODO paramétrable
		List<Room> rooms = roomDAO.findByPlanningsIsNull();
		for (Room room : rooms) {
			container.append("[" + room.getLabel() + "]");
		}
		logger.info("[" + rooms.size() + "] free classroom(s) found : ["
				+ container.toString() + "]");
		container.delete(0, container.length());

	}

	/**
	 * Executions des requetes nommées, illustration de l'accès aux données
	 */
	private void showNamedQueriesWork() {

		StringBuilder container = new StringBuilder();

		String roomLabel = "Room 1";
		Room room = roomDAO.findByLabel(roomLabel);
		// Si une salle est enregistré avec cet intitulé
		if (room != null) {
			for (PlanningEvent planning : room.getCourses()) {
				container.append("[" + planning.getCourse().getLabel() + " : "
						+ planning.getDate().toString() + " - "
						+ planning.getTargetHalfDay() + ".]");
			}
			// Affichage des cours se déroulant dans la salle
			logger.info("[" + room.getCourses().size()
					+ "] course(s) event(s) located in the classroom ["
					+ room.getLabel() + "] : [" + container.toString() + "]");
		} else {
			logger.info("No room registred with label [" + roomLabel + "]");
		}

		container.delete(0, container.length());
		String courseLabel = "English";
		Course course = courseDAO.findByLabel(courseLabel);
		// Si une salle est enregistré avec cet intitulé
		if (course != null) {
			for (Student student : course.getStudents()) {
				container.append("[id : " + student.getId() + ", name : "
						+ student.getFirstName() + " " + student.getLastName()
						+ ".]");
			}
			// Affichage des cours se déroulant dans la salle
			logger.info("[" + course.getStudents().size()
					+ "] student(s) registed for course [" + course.getLabel()
					+ "] : [" + container.toString() + "]");
		} else {
			logger.info("No course registred with label [" + courseLabel + "]");
		}

	}

}
