package fr.iut.csid.empower.elearning.core.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:test_spring-config-hsql.xml")
public class RegistrationServiceTest {

	// @Mock
	// private StudentDAO studentDAOMock;
	//
	// @Mock
	// private CourseDAO courseDAOMock;
	//
	// @Resource
	// @InjectMocks
	// private RegistrationService registrationService;
	//
	// @Before
	// public void setUp() {
	// // Init service, reset pour tous les tests
	// registrationService = new RegistrationService();
	// // Init mock
	// MockitoAnnotations.initMocks(this);
	// }
	//
	// @Test
	// public void registerOkTest() {
	//
	// // Init data
	//
	// Student student = new Student("Vincent", "Lacaze");
	// Course course = new Course("cours1");
	//
	// // Expectation
	// when(studentDAOMock.save(student)).thenReturn(student);
	//
	// // Process
	// registrationService.register(student, course);
	//
	// }
	//
	// @Test
	// public void registerFailTest() {
	//
	// // Init data
	//
	// Student student = new Student("Vincent", "Lacaze");
	// Course course = new Course("coursToRegister");
	// for (int i = 0; i < 3; i++) {
	// Course course2 = new Course("cours" + i);
	// student.getCourses().add(course2);
	// }
	//
	// // // Expectation
	// // when(studentDAOMock.save(student)).thenReturn(student);
	//
	// // Process
	// registrationService.register(student, course);
	//
	// }
}