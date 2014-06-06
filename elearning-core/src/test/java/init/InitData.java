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
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.AdministratorService;
import fr.iut.csid.empower.elearning.core.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:config-initdata.xml")
public class InitData {

	@Inject
	private StudentService studentService;

	@Inject
	private AdministratorService administratorService;
	
	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Rollback(false)
	public void initStudents() {
		for (int i = 0; i < 20; i++) {
			Student student = new Student("Alfred" + i, "La" + i + "Saumure", "fredo[" + i + "]", "5665440", "alfred." + i + "@alfred.com");
			studentService.save(student);
		}
	}
	
	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Rollback(false)
	public void initAdministrators() {
		Administrator administrator = new Administrator("Vincent", "Lacaze", "admin", "admin", "vincent.lacaze@mail.com");
		administratorService.save(administrator);
	}
}
