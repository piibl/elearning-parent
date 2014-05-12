package fr.iut.csid.empower.elearning.core.job.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.room.Room;
import fr.iut.csid.empower.elearning.core.domain.student.Student;
import fr.iut.csid.empower.elearning.core.job.Job;
import fr.iut.csid.empower.elearning.core.service.PlanningService;
import fr.iut.csid.empower.elearning.core.service.RegistrationService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.room.RoomDAO;
import fr.iut.csid.empower.elearning.core.service.dao.student.StudentDAO;

@Named
public class InitializationJob implements Job {

	// private static Logger logger = LoggerFactory.getLogger(InitRoutine.class);

	@Inject
	private StudentDAO studentDAO;
	@Inject
	private CourseDAO courseDAO;
	@Inject
	private RoomDAO roomDAO;

	// Service d'inscription à un cours
	@Inject
	private RegistrationService registrationService;
	// Service de plannification
	@Inject
	private PlanningService planningService;

	@Override
	public void process() {
		createCourses();
		createStudents();
		createRooms();
		doPlannings();
	}

	/**
	 * Création des étudiants
	 */
	private void createStudents() {

		List<Course> courses = courseDAO.findAll();

		/**
		 * 15 étudiants, 2 inscrits au premier cours, 2 au second, 6 aux deux
		 * premiers, 2 à tous, etc... et trois ne sont inscrit à aucun cours.
		 */
		for (int i = 0; i < 15; i++) {
			Student student = new Student("Student" + (i + 1), "Dummy"
					+ (i + 1));
			if (i < 2) {
				registrationService.register(student, courses.get(0));
			} else if (i < 4) {
				registrationService.register(student, courses.get(1));
			} else if (i < 10) {
				registrationService.register(student, courses.get(0),
						courses.get(1));
			} else if (i < 12) {
				registrationService.register(student, courses.get(0),
						courses.get(1), courses.get(2));
			}
			studentDAO.immediateCreate(student);
		}
	}

	/**
	 * Création des cours
	 */
	private void createCourses() {
		String[] coursesLabel = { "English", "Maths", "Computer Science" };
		for (int i = 0; i < coursesLabel.length; i++) {
			courseDAO.immediateCreate(new Course(coursesLabel[i]));
		}
	}

	/**
	 * Création des salles
	 */
	private void createRooms() {
		String[] roomsLabel = { "Room 1", "Room 2", "Room 3" };
		for (int i = 0; i < roomsLabel.length; i++) {
			roomDAO.immediateCreate(new Room(roomsLabel[i]));
		}
	}

	/**
	 * Création des associations cours salles
	 */
	private void doPlannings() {
		// 3 salles, 3 cours
		// 1 semaine, 1 salle bouclée, 1 salle utilisée que le matin, 1 salle
		// non utilisée
		List<Course> courses = courseDAO.findAll();
		List<Room> rooms = roomDAO.findAll();

		// Formateur de date
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

		for (int i = 1; i < 7; i++) {
			String stringDate = i + "-06-2014";
			try {
				Date date = dateFormat.parse(stringDate);
				// Salle exclusivement réservé à l'anglais.
				planningService.bookRoom(courses.get(0), rooms.get(0), date,
						"AM");
				planningService.bookRoom(courses.get(0), rooms.get(0), date,
						"PM");
				// Salle partagée entre maths et info
				planningService.bookRoom(courses.get(1), rooms.get(1), date,
						"AM");
				planningService.bookRoom(courses.get(2), rooms.get(1), date,
						"PM");
			} catch (ParseException e) {
				// TODO Erreur à traiter
				e.printStackTrace();
			}

		}

	}
}
