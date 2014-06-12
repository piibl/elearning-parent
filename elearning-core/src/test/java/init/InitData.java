package init;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config-initdata.xml")
public class InitData {

<<<<<<< HEAD
	/**
	 * Strict minimum => un administrateur !!
	 */

	@Inject
	private AdministratorService administratorService;

=======

	@Inject
	private AdministratorService administratorService;
	
>>>>>>> 92b49c17604a7ac29c87416243fba7bd7539d889
	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Rollback(false)
	public void initAdministrators() {
		Administrator administrator = new Administrator("Admin", "Admin", "admin", "admin", "admin.admin@admin.com");
		administratorService.save(administrator);
	}
}
