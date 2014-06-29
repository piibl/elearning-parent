package fr.iut.csid.empower.elearning.core.service.dao;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.CoreTest;
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
	public void test() {
		// INIT
		
		
	}

}
