/**
 * 
 */
package fr.iut.csid.empower.elearning.web.controller.domain;

//import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import config.WebTest;
import config.util.TestUtil;
import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
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
	public void testGetAll() throws Exception {
		// Init
		List<Administrator> administrators = new ArrayList<Administrator>();
		List<Resource<Administrator>> resources = new ArrayList<Resource<Administrator>>();
		// Mocks
		when(administratorServiceMock.findAll()).thenReturn(administrators);
		when(administratorResourceAssemblerMock.toResource(administrators)).thenReturn(resources);
		// Perform
		mockMvc.perform(get("/administrators")).andExpect(status().isOk()).andExpect(model().attribute("administrators", hasSize(0)))
				.andExpect(view().name("display/administrators :: display-administrators"));

	}

	@Test
	public void testGetAddForm() throws Exception {
		// Perform
		mockMvc.perform(get("/administrators/new")).andExpect(status().isOk()).andExpect(view().name("forms/add-forms :: add-administrator-form"));

	}

	@Test
	public void testCreateEntity() throws Exception {
		// Init
		List<Administrator> administrators = new ArrayList<Administrator>();
		List<Resource<Administrator>> resources = new ArrayList<Resource<Administrator>>();
		// Init DTO
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Bryan");
		userDTO.setLastName("Bryan");
		userDTO.setLogin("bryan");
		userDTO.setEmail("b@b");
		userDTO.setPassword("hh");
		// Mocks
		when(administratorServiceMock.createFromDTO(any(UserDTO.class))).thenReturn(new Administrator("", "", "bryan", "", ""));
		when(administratorServiceMock.findAll()).thenReturn(administrators);
		when(administratorResourceAssemblerMock.toResource(administrators)).thenReturn(resources);
		// Perform
		mockMvc.perform(post("/administrators").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(userDTO)))
				.andExpect(status().isOk()).andExpect(model().attribute("administrators", hasSize(0)))
				.andExpect(view().name("display/administrators :: display-administrators"));
	}

	@Test
	public void testGetEntity() throws Exception {
		// Init
		List<Link> links = new ArrayList<Link>();
		Administrator admin = new Administrator();
		// Mocks
		when(administratorServiceMock.find(any(Long.class))).thenReturn(admin);
		when(administratorResourceAssemblerMock.toResource(admin)).thenReturn(new Resource<Administrator>(admin, links));
		// Perform
		mockMvc.perform(get("/administrators/1")).andExpect(status().isOk()).andExpect(model().attribute("administrator", notNullValue()))
				.andExpect(view().name("display/administrators :: display-details"));
	}

	@Test
	public void testGetEditForm() throws Exception {
		List<Link> links = new ArrayList<Link>();
		// Init
		Administrator admin = new Administrator();
		// Mocks
		when(administratorServiceMock.find(any(Long.class))).thenReturn(admin);
		when(administratorResourceAssemblerMock.toResource(admin)).thenReturn(new Resource<Administrator>(admin, links));
		// Perform
		mockMvc.perform(get("/administrators/1/edit")).andExpect(status().isOk()).andExpect(model().attribute("administrator", notNullValue()))
				.andExpect(view().name("forms/edit-forms :: edit-administrator-form"));
	}

	@Test
	public void testEditEntity() throws IOException, Exception {

		// Init
		List<Administrator> administrators = new ArrayList<Administrator>();
		List<Resource<Administrator>> resources = new ArrayList<Resource<Administrator>>();
		// Init DTO
		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("Bryan");
		userDTO.setLastName("Bryan");
		userDTO.setLogin("bryan");
		userDTO.setEmail("b@b");
		userDTO.setPassword("hh");
		// Mocks
		when(administratorServiceMock.saveFromDTO(any(UserDTO.class), any(Long.class))).thenReturn(new Administrator("", "", "bryan", "", ""));
		when(administratorServiceMock.findAll()).thenReturn(administrators);
		when(administratorResourceAssemblerMock.toResource(administrators)).thenReturn(resources);
		// Perform
		mockMvc.perform(
				post("/administrators/1/edit").contentType(TestUtil.APPLICATION_JSON_UTF8).content(TestUtil.convertObjectToJsonBytes(userDTO)))
				.andExpect(status().isOk()).andExpect(model().attribute("administrators", hasSize(0)))
				.andExpect(view().name("display/administrators :: display-administrators"));

	}

	@Test
	public void testDeleteEntity() throws IOException, Exception {

		// Init
		List<Administrator> administrators = new ArrayList<Administrator>();
		List<Resource<Administrator>> resources = new ArrayList<Resource<Administrator>>();
		// Mocks
		// Init
		Administrator admin = new Administrator();
		// Mocks
		when(administratorServiceMock.find(any(Long.class))).thenReturn(admin);
		when(administratorServiceMock.findAll()).thenReturn(administrators);
		when(administratorResourceAssemblerMock.toResource(administrators)).thenReturn(resources);
		// Perform
		mockMvc.perform(get("/administrators/1/delete")).andExpect(status().isOk()).andExpect(model().attribute("administrators", hasSize(0)))
				.andExpect(view().name("display/administrators :: display-administrators"));

	}

}
