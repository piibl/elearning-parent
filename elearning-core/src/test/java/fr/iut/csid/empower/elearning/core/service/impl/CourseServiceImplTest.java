/**
 * 
 */
package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.CoreTest;
import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_HSQL })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class CourseServiceImplTest {

	// Objet sous test
	@Inject
	private CourseService courseService;

	// Utilitaires pour insert/assert
	@Inject
	private CourseDAO courseDAO;
	@Inject
	private CourseTeachingDAO courseTeachingDAO;
	@Inject
	private TeacherDAO teacherDAO;

	@Test
	public void testCreateCourseOk() {
		// Init
		// Sauvegarde d'un enseignant
		Teacher teacher = teacherDAO.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		// objet DTO
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setLabel("Un cours");
		courseDTO.setOwnerId("1");

		// Process
		Course course = courseService.createFromDTO(courseDTO);

		// Assert
		Assert.assertNotNull(course);
		Assert.assertTrue(courseTeachingDAO.count() == 1);
		// Quick'n'dirty, on aime !!!!
		Assert.assertEquals(teacher.getId(), courseTeachingDAO.findByTeacher(teacher).get(0).getTeacher().getId());
		Assert.assertEquals(course.getId(), courseTeachingDAO.findByCourse(course).get(0).getCourse().getId());
	}

	@Test(expected = UserNotExistsException.class)
	public void testCreateCourseKoUserNotexists() {
		// Init
		// objet DTO
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setLabel("Un cours");
		courseDTO.setOwnerId("1");

		// Process
		courseService.createFromDTO(courseDTO);
	}

	@Test(expected = NumberFormatException.class)
	public void testCreateCourseKoNumberFormatException() {
		// Init
		// objet DTO
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setLabel("Un cours");
		courseDTO.setOwnerId("gh<ksgk<");

		// Process
		courseService.createFromDTO(courseDTO);
	}

	@Test
	public void testDeleteCourse() {
		// Init
		Course course = courseDAO.save(new Course("Un cours"));
		Teacher teacher = teacherDAO.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		CourseTeaching courseTeaching = courseTeachingDAO.save(new CourseTeaching(teacher, course));

		// Process
		courseService.delete(course);

		// Assert
		Assert.assertFalse(courseTeachingDAO.exists(courseTeaching.getId()));
		Assert.assertFalse(courseDAO.exists(course.getId()));
	}

	@Test
	public void testCoursesByTeacher() {
		// Init
		Course course = courseDAO.save(new Course("Un cours"));
		Teacher teacher = teacherDAO.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		courseTeachingDAO.save(new CourseTeaching(teacher, course));

		// Process
		List<Course> courses = courseService.findByTeacher(teacher.getId());

		// Assert
		// TODO test un peu plus sérieux ?
		Assert.assertTrue(courses.size() == 1);
	}

	// TODO tests lévée d'exception
}
