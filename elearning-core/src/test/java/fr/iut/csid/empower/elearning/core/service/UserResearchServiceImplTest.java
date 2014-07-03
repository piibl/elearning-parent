/**
 * 
 */
package fr.iut.csid.empower.elearning.core.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import config.CoreTest;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.core.reference.UserRole;
import fr.iut.csid.empower.elearning.core.service.dao.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_HSQL })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class UserResearchServiceImplTest {

	@Inject
	private UserRepository userRepository;
	@Inject
	private UserResearchService researchService;

	@Test
	public void testFindByLogin() {
		// Sauvegarde d'un uilisateur
		userRepository.save(new EndUser("", "", "bryan", "", "", UserRole.ADMINISTRATOR));
		// Perform
		Assert.notNull(researchService.findByLogin("bryan"));
		Assert.isNull(researchService.findByLogin("bob"));
	}
}
