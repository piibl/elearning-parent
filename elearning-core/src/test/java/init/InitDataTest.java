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
import fr.iut.csid.empower.elearning.core.service.dao.user.AdministratorRepository;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { CoreTest.SPRING_CONFIG_INIT_DATA })
@Transactional(value = CoreTest.TRANSACTION_MANAGER)
public class InitDataTest {

	@Inject
	private AdministratorRepository administratorRepository;
	@Inject
	private TeacherRepository teacherRepository;

	@Test
	@Rollback(false)
	public void initTeacher() {
		// Insertion en base d'un administrateur
		teacherRepository.save(new Teacher("Diego", "De La Vega", "diego", "tornado", "diego.delavega@tornado.huuu"));
	}

	@Test
	@Rollback(false)
	public void initAdmin() {
		// Insertion en base d'un administrateur
		administratorRepository.save(new Administrator("admin", "admin", "admin", "admin", "admin.admin@admin.admin"));

	}

}
