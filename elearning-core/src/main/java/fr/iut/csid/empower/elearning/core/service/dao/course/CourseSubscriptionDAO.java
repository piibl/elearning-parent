package fr.iut.csid.empower.elearning.core.service.dao.course;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;

/**
 * DAO CourseSubscription
 * 
 * @author Pierre_pers
 */
public interface CourseSubscriptionDAO extends JpaRepository<CourseSubscription, Long> {

}
