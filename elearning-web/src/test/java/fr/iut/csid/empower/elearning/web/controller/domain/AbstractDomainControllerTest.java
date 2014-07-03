/**
 * 
 */
package fr.iut.csid.empower.elearning.web.controller.domain;

import static org.hamcrest.Matchers.hasToString;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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
import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.controller.domain.user.AdministratorController;
import fr.iut.csid.empower.elearning.web.dto.impl.UserDTO;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.AdministratorResourceAssembler;
import fr.iut.csid.empower.elearning.web.service.AdministratorService;

/**
 * Basée sur l'implémenation AdministratorController
 */
@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration
@ContextConfiguration(locations = { WebTest.SPRING_CONFIG_HSQL })
public class AbstractDomainControllerTest {

	@Mock
	private AdministratorService administratorServiceMock;
	@Mock
	private AdministratorResourceAssembler administratorResourceAssemblerMock;
	@Mock
	private ControllerLinkBuilderFactory linkBuilderFactoryMock;

	@InjectMocks
	private AdministratorController controllerUnderTest;

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
	public void testGetAll() {
		// Init
		List<Administrator> administrators = new ArrayList<Administrator>();
		administrators.add(new Administrator());
		administrators.add(new Administrator());
		// Mocks
		when(administratorServiceMock.findAll()).thenReturn(administrators);
		when(administratorResourceAssemblerMock.toResource(administrators)).thenReturn(administratorResourceAssemblerMock.toResource(administrators));

	}

	@Test
	public void testRegisterStudent() throws Exception {
		// Init DTO
		// UserDTO userDTO = new UserDTO();
		// userDTO.setFirstName("Bryan");
		// userDTO.setLastName("Bryan");
		// userDTO.setLogin("bryan");
		// userDTO.setEmail("b@b");
		// userDTO.setPassword("hh");
		//
		// // Mocks
		// when(studentServiceMock.createFromDTO(any(UserDTO.class))).thenReturn(new Student("", "", "bryan", "", ""));
		// // Perform
		// mockMvc.perform(post("/registration").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(userDTO)))
		// .andExpect(status().isOk()).andExpect(model().attribute("loginNewUser", hasToString("bryan")))
		// .andExpect(view().name("fragment/alerts :: new-user-welcome"));
	}
}
