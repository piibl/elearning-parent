package fr.iut.csid.empower.elearning.core.service.impl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:META-INF/spring/core-context.xml")
public class PlanningServiceTest {
//	
//	@Mock
//	private PlanningEventDAO planningDAOMock;
//	
//	@Mock
//	private RoomDAO roomDAOMock;
//	
//	@Resource
//	@InjectMocks
//	private PlanningService planningService;
//	
//	@Before
//	public void setUp() {
//		// Init service, reset pour tous les tests
//		planningService = new PlanningService();
//		// Init mock
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	@Test
//	public void bookRoomTest() {
//		// Init data
//		Course course = new Course("cours1");
//		ClassRoom room = new ClassRoom("room1");
//		Date date = new Date();
//		PlanningEvent planningEvent = new PlanningEvent(course, room, date, "PM");
//		
//		// Expectation
//		when(planningDAOMock.save(planningEvent)).thenReturn(planningEvent);
//	
//		// Process
//		planningService.bookRoom(course, room, date, "PM");
//	}
//	
//	@Test
//	public void cancelBookingTest() {
//		// Init data
//		Course course = new Course("cours1");
//		ClassRoom room = new ClassRoom("room1");
//		Date date = new Date();
//		PlanningEvent planningEvent = new PlanningEvent(course, room, date, "PM");
//		
//		// Expectation
//		when(planningDAOMock.save(planningEvent)).thenReturn(planningEvent);
//	
//		// Process
//		planningService.cancelBooking(planningEvent);
//	}
}
