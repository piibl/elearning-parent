/**
 * 
 */
package fr.iut.csid.empower.elearning.web.controller.dashboard;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import config.AbstractSecurityTest;
import config.WebTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { WebTest.SPRING_CONFIG_HSQL })
public class StudentDashboardControllerTest extends AbstractSecurityTest {

	@Inject
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Configuration spring, repro de la config d'origine
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

	}
}
