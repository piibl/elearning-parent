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
import fr.iut.csid.empower.elearning.core.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;

/**
 * 
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Student, Long> implements StudentService {

	@Inject
	private CourseDAO courseDAO;
	
	@Inject
	private StudentDAO studentDAO;
	
	@Inject
	private CourseSubscriptionDAO courseSubscriptionDAO;

	@Override
	protected JpaRepository<Student, Long> getDAO() {
		return studentDAO;
	}
	
	@Override
	public Student findByLogin(String login) {
		return studentDAO.findByLogin(login);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Student createFromDTO(UserDTO entityDTO) {
		Student student = new Student(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return studentDAO.save(student);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Student saveFromDTO(UserDTO entityDTO, Long id) {
		Student student = studentDAO.findOne(id);
		if (student != null) {
			// Pas de questions, on reporte tous les changements
			student.setFirstName(entityDTO.getFirstName());
			student.setLastName(entityDTO.getLastName());
			student.setLogin(entityDTO.getLogin());
			student.setEmail(entityDTO.getEmail());
			// TODO ??? sécurité ???
			student.setPassword(entityDTO.getPassword());
			return studentDAO.save(student);
		} else
			throw new UserNotExistsException();

	}
	
	
	public List<Course> findSubscribedCourses(Long studentId){
		List<Course> courses = new ArrayList<Course>();
		// Récupération de l'étudiant
		Student student = studentDAO.findOne(studentId);
		if (student != null) {
			// recherche de toutes les affilition de l'étudiant
			for (CourseSubscription courseSubscription : courseSubscriptionDAO.findByStudent(student)) {
				if (courseSubscriptionDAO.exists(courseSubscription.getCourse().getId())) {
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

	@Override
	public List<Course> findUnsubscribedCourses(Long studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
