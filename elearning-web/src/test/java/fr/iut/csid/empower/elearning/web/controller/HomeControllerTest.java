/**
 * 
 */
package fr.iut.csid.empower.elearning.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.AbstractSecurityTest;
import config.WebTest;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { WebTest.SPRING_CONFIG_HSQL })
public class HomeControllerTest extends AbstractSecurityTest {

	@Inject
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Configuration spring, repro de la config d'origine
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}

	@Test
	public void testGetHomePage() throws Exception {
		// Utilisateur non logué
		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name(PathFragment.HOME.getPath()));
		// Utilisateur loggué, redirigé vers les dashboards
		login("admin", "admin");
		mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
	}
}