package fr.iut.csid.empower.elearning.core.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.student.Student;

@RunWith(BlockJUnit4ClassRunner.class)
public class RegistrationServiceTest {
	
	private RegistrationService registrationService;

	
	@Test
	public final void registerTest(){		
//		initialisation objet
		registrationService = new RegistrationService();
		Student student = new Student("Vincent","Lacaze");
		
//		test si l'étudiant n'a aucun cours enregistré
		assertEquals(0, student.getCourses().size());
		
//		ajout d'un cours et test si l'enregistrement est effectué
		Course course = new Course("CoursTest1");
		registrationService.register(student, course);
		assertEquals(1,student.getCourses().size());
		
//		ajout du nombre maximum de cours pour l'étudiant
		course = new Course("CoursTest2");
		registrationService.register(student, course);
		course = new Course("CoursTest3");
		registrationService.register(student, course);

//		ajout d'un cours supplémentaire et test si non enregistré
		Course course3 = new Course("Francais");
		registrationService.register(student, course3);
		assertEquals(3,student.getCourses().size());
	}
}