package fr.iut.csid.empower.elearning.core.service.dao.course;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.CoreTest;
import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.reference.CourseSubscriptionType;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_HSQL })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class CourseRepositoryTest {

	@Inject
	private CourseRepository courseRepository;
	@Inject
	private StudentRepository studentRepository;
	@Inject
	private CourseSubscriptionRepository courseSubscriptionRepository;

	@Test
	public void testFindUnsubscribedCourses() {
		// INIT
		Course course1 = courseRepository.save(new Course("Cours1", ""));
		courseRepository.save(new Course("Cours2", ""));
		Student student = studentRepository.save(new Student("", "", "bryan", "", ""));
		courseSubscriptionRepository.save(new CourseSubscription(student, course1, CourseSubscriptionType.CONSULTATIVE));
		// Perform
		Assert.assertEquals(1, courseRepository.findUnsubscribedCourses(student).size());
		Assert.assertEquals("Cours2", courseRepository.findUnsubscribedCourses(student).get(0).getLabel());
	}

}
