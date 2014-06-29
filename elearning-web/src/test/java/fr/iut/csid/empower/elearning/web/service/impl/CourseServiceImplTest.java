/**
 * 
 */
package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.WebTest;
import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.CourseTeaching;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.exception.UserNotExistsException;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseRepository;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseTeachingRepository;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherRepository;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.web.service.CourseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { WebTest.SPRING_CONFIG_HSQL })
@Transactional(value = WebTest.TRANSACTION_MANAGER)
public class CourseServiceImplTest {

	// Objet sous test
	@Inject
	private CourseService courseService;

	// Utilitaires pour insert/assert
	@Inject
	private CourseRepository courseRepository;
	@Inject
	private CourseTeachingRepository courseTeachingRepository;
	@Inject
	private TeacherRepository teacherRepository;

	@Test
	public void testCreateCourseOk() {
		// Init
		// Sauvegarde d'un enseignant
		Teacher teacher = teacherRepository.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		// objet DTO
		CourseDTO courseDTO = new CourseDTO();
		courseDTO.setLabel("Un cours");
		courseDTO.setOwnerId("1");

		// Process
		Course course = courseService.createFromDTO(courseDTO);

		// Assert
		Assert.assertNotNull(course);
		Assert.assertTrue(courseTeachingRepository.count() == 1);
		// Quick'n'dirty, on aime !!!!
		Assert.assertEquals(teacher.getId(), courseTeachingRepository.findByTeacher(teacher).get(0).getTeacher().getId());
		Assert.assertEquals(course.getId(), courseTeachingRepository.findByCourse(course).get(0).getCourse().getId());
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
		Course course = courseRepository.save(new Course("Un cours"));
		Teacher teacher = teacherRepository.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		CourseTeaching courseTeaching = courseTeachingRepository.save(new CourseTeaching(teacher, course));

		// Process
		courseService.delete(course);

		// Assert
		Assert.assertFalse(courseTeachingRepository.exists(courseTeaching.getId()));
		Assert.assertFalse(courseRepository.exists(course.getId()));
	}

	@Test
	public void testCoursesByTeacher() {
		// Init
		Course course = courseRepository.save(new Course("Un cours"));
		Teacher teacher = teacherRepository.save(new Teacher("Bryan", "Bryan", "bryan", "bryan", "bryan"));
		courseTeachingRepository.save(new CourseTeaching(teacher, course));

		// Process
		List<Course> courses = courseService.findByTeacher(teacher.getId());

		// Assert
		// TODO test un peu plus sérieux ?
		Assert.assertTrue(courses.size() == 1);
	}

	// TODO tests lévée d'exception
}
