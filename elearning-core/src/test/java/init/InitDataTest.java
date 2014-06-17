package init;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import config.CoreTest;
import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.dao.user.AdministratorDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_INIT_DATA })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class InitDataTest {

	@Inject
	private AdministratorDAO administratorDAO;
	@Inject
	private TeacherDAO teacherDAO;

	@Test
	@Rollback(false)
	public void initTeacher() {
		// Insertion en base d'un administrateur
		teacherDAO.save(new Teacher("Diego", "De La Vega", "diego", "tornado", "diego.delavega@tornado.huuu"));
	}

	@Test
	@Rollback(false)
	public void initAdmin() {
		// Insertion en base d'un administrateur
		administratorDAO.save(new Administrator("admin", "admin", "admin", "admin", "admin.admin@admin.admin"));

	}

}
