package fr.iut.csid.empower.elearning.core.service.dao.course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * DAO CourseSubscription
 * 
 * @author Pierre_pers
 */
public interface CourseSubscriptionDAO extends JpaRepository<CourseSubscription, Long> {

	public List<CourseSubscription> findByStudent(Student student);
}
