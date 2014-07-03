/**
 * 
 */
package fr.iut.csid.empower.elearning.web.controller;

import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import config.WebTest;
import config.util.TestUtil;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration(locations = { WebTest.SPRING_CONFIG_HSQL })
public class RegistrationControllerTest {

	@Mock
	private StudentService studentServiceMock;

	@InjectMocks
	RegistrationController controllerUnderTest;

	private MockMvc mockMvc;

	@Before
	public void setup() {

		// Init mockito + injection
		MockitoAnnotations.initMocks(this);

		// Standalone, on ne veut que ce controller
		this.mockMvc = MockMvcBuilders.standaloneSetup(controllerUnderTest).build();

	}

	@Test
	public void testGetRegistrationPage() throws Exception {
		// Trivial...
		mockMvc.perform(get("/registration")).andExpect(status().isOk()).andExpect(view().name("fragment/registration :: registration-form"));
	}

	@Test
	public void testRegisterStudent() throws Exception {
		// Init DTO
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Bryan");
		userDTO.setLastName("Bryan");
		userDTO.setLogin("bryan");
		userDTO.setEmail("b@b");
		userDTO.setPassword("hh");

		// Mocks
		when(studentServiceMock.createFromDTO(any(UserDTO.class))).thenReturn(new Student("", "", "bryan", "", ""));
		// Perform
		mockMvc.perform(post("/registration").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(userDTO)))
				.andExpect(status().isOk()).andExpect(model().attribute("loginNewUser", hasToString("bryan")))
				.andExpect(view().name("fragment/alerts :: new-user-welcome"));
	}
}
