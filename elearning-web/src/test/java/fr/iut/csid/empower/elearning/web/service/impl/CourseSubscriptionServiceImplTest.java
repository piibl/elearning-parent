/**
 * 
 */
package fr.iut.csid.empower.elearning.web.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseSubscription;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.exception.CourseNotExistsException;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseSubscriptionRepository;
import fr.iut.csid.empower.elearning.web.service.CourseSubscriptionService;

@RunWith(MockitoJUnitRunner.class)
public class CourseSubscriptionServiceImplTest {

	@Mock
	private CourseRepository courseRepositoryMock;
	@Mock
	private CourseSubscriptionRepository courseSubscriptionRepositoryMock;

	@InjectMocks
	private CourseSubscriptionService serviceUnderTest = new CourseSubscriptionServiceImpl();

	@Before
	public void setup() {
		// Init mockito + injection
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUnsubscribeOk() {
		CourseSubscription subscription = new CourseSubscription();
		subscription.setCourse(new Course("dummy", ""));
		when(courseRepositoryMock.findOne(1L)).thenReturn(new Course());
		when(courseSubscriptionRepositoryMock.findByStudentAndCourse(any(Student.class), any(Course.class))).thenReturn(subscription);
		// Perform
		Assert.assertEquals(serviceUnderTest.unsubscribe(new Student(), 1L), "dummy");

	}

	@Test(expected = CourseNotExistsException.class)
	public void testUnsubscribeKO() {

		when(courseRepositoryMock.findOne(1L)).thenReturn(null);

		// Perform
		serviceUnderTest.unsubscribe(new Student(), 1L);

	}
}
