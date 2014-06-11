package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

/**
 * 
 */
@Named
public class CourseServiceImpl extends AbstractCrudService<Course, Long> implements CourseService {

	@Inject
	private CourseDAO courseDAO;
	@Inject
	private CourseTeachingDAO courseTeachingDAO;
	@Inject
	private CourseSubscriptionDAO courseSubscriptionDAO;
	@Inject
	private TeacherDAO teacherDAO;
	@Inject
	private StudentDAO studentDAO;

	@Override
	protected JpaRepository<Course, Long> getDAO() {
		return courseDAO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { UserNotExistsException.class, NumberFormatException.class })
	public Course createFromDTO(CourseDTO courseDTO) {
		// Création d'un cours et save pour obtenir un id
		Course course = new Course(courseDTO.getLabel());
		course = courseDAO.save(course);
		// Conversion de l'id du créateur en type Long
		Long ownerId = Long.valueOf(courseDTO.getOwnerId());
		// Récupération de l'enseignant créateur
		Teacher teacher = teacherDAO.findOne(ownerId);
		if (teacher != null) {
			CourseTeaching courseTeaching = new CourseTeaching(teacher, course);
			courseTeachingDAO.save(courseTeaching);
			return course;
		} else {
			// Pas d'enseignant associé à cet id
			throw new UserNotExistsException();
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Course saveFromDTO(CourseDTO entityDTO, Long id) {
		Course course = courseDAO.findOne(id);
		if (course != null) {
			// Pas de questions, on reporte tous les changements
			course.setLabel(entityDTO.getLabel());
			return courseDAO.save(course);
		} else
			throw new CourseNotExistsException();

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Course course) {
		// Suppression de toutes les affiliations à ce cours
		for (CourseTeaching courseTeaching : courseTeachingDAO.findByCourse(course)) {
			courseTeachingDAO.delete(courseTeaching.getId());
		}
		// Suppression du cours
		courseDAO.delete(course);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByTeacher(Long teacherId) {
		List<Course> courses = new ArrayList<Course>();
		// Récupération de l'enseignant
		Teacher teacher = teacherDAO.findOne(teacherId);
		if (teacher != null) {
			// recherche de toutes les affilition de l'enseignant
			for (CourseTeaching courseTeaching : courseTeachingDAO.findByTeacher(teacher)) {
				if (courseDAO.exists(courseTeaching.getCourse().getId())) {
					courses.add(courseDAO.getOne(courseTeaching.getCourse().getId()));
				} else {
					// TODO ignorer les orphelins ?
					throw new CourseNotExistsException();
				}
			}
			return courses;
		} else {
			// Pas d'enseignant associé à cet id
			throw new UserNotExistsException();
		}

	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findByStudent(Long studentId) {
		List<Course> courses = new ArrayList<Course>();
		// Récupération de l'étudiant
		Student student = studentDAO.findOne(studentId);
		if (student != null) {
			// recherche de toutes les souscriptions de l'étudiant
			for (CourseSubscription courseSubscription : courseSubscriptionDAO.findByStudent(student)) {
				if (courseDAO.exists(courseSubscription.getCourse().getId())) {
					courses.add(courseDAO.getOne(courseSubscription.getCourse().getId()));
				} else {
					// TODO ignorer les orphelins ?
					throw new CourseNotExistsException();
				}
			}
			return courses;
		} else {
			// Pas d'étudiant associé à cet id
			throw new UserNotExistsException();
		}
	}
}
