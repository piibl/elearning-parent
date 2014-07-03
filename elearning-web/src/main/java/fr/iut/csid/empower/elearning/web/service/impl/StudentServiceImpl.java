package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.AbstractCrudService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.session.CourseSessionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.service.StudentService;

/**
 * 
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Student, Long> implements StudentService {

	@Inject
	private CourseRepository courseRepository;

	@Inject
	private StudentRepository studentRepository;

	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;

	@Inject
	private CourseTeachingRepository courseTeachingRepository;

	@Inject
	private CourseSessionRepository courseSessionRepository;

	@Override
	protected JpaRepository<Student, Long> getRepository() {
		return studentRepository;
	}

	@Override
	public Student findByLogin(String login) {
		return studentRepository.findByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Student createFromDTO(UserDTO entityDTO) {
		Student student = new Student(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return studentRepository.save(student);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Student saveFromDTO(UserDTO entityDTO, Long id) {
		Student student = studentRepository.findOne(id);
		if (student != null) {
			// Pas de questions, on reporte tous les changements
			student.setFirstName(entityDTO.getFirstName());
			student.setLastName(entityDTO.getLastName());
			student.setLogin(entityDTO.getLogin());
			student.setEmail(entityDTO.getEmail());
			// TODO ??? sécurité ???
			student.setPassword(entityDTO.getPassword());
			return studentRepository.save(student);
		} else
			throw new UserNotExistsException();

	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> findSubscribedCourses(Long studentId) {
		List<Course> courses = new ArrayList<Course>();
		// Récupération de l'étudiant
		Student student = studentRepository.findOne(studentId);
		if (student != null) {
			// recherche de toutes les souscriptions de l'étudiant
			for (CourseSubscription courseSubscription : courseSubscriptionRepository.findByStudent(student)) {
				if (courseRepository.exists(courseSubscription.getCourse().getId())) {
					Course course = courseRepository.findOne(courseSubscription.getCourse().getId());
					course.setSubscriptions(courseSubscriptionRepository.findByCourse(course));
					course.setCoursesTeaching(courseTeachingRepository.findByCourse(course));
					course.setSessions(courseSessionRepository.findByOwnerCourseOrderBySessionRankAsc(course));
					courses.add(course);
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

	@Override
	@Transactional(readOnly = true)
	public List<Course> findUnsubscribedCourses(Long studentId) {

		// Récupération de l'étudiant
		Student student = studentRepository.findOne(studentId);
		if (student != null) {
			List<Course> courses = courseRepository.findUnsubscribedCourses(student);
			for (Course course : courses) {
				course.setSubscriptions(courseSubscriptionRepository.findByCourse(course));
				course.setCoursesTeaching(courseTeachingRepository.findByCourse(course));
				course.setSessions(courseSessionRepository.findByOwnerCourseOrderBySessionRankAsc(course));
			}
			return courses;
		} else {
			// Pas d'étudiant associé à cet id
			throw new UserNotExistsException();
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Course findSubscribedCourse(Long courseId) {
		Course course = courseRepository.findOne(courseId);
		course.setSubscriptions(courseSubscriptionRepository.findByCourse(course));
		course.setCoursesTeaching(courseTeachingRepository.findByCourse(course));
		course.setSessions(courseSessionRepository.findByOwnerCourseOrderBySessionRankAsc(course));
		return course;

	}

}
