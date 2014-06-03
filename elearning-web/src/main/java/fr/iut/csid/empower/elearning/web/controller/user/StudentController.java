package fr.iut.csid.empower.elearning.web.controller.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.assembler.StudentResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractEntityController<Student, Long> {

	private String mainView = "students";
	private String entitiesAttributeName = "students";
	private String singleEntityAttributeName = "student";

	@Inject
	private StudentService studentService;

	@Inject
	private StudentResourceAssembler studentResourceAssembler;

	// @Inject
	// private ControllerLinkBuilderFactory linkBuilderFactory;

	// private Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Override
	protected CrudService<Student, Long> getCrudService() {
		return studentService;
	}

	@Override
	protected BatchResourceAssembler<Student, Resource<Student>> getResourceAssembler() {
		return studentResourceAssembler;
	}

	@Override
	protected String getBaseViewPage() {
		return mainView;
	}

	@Override
	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	@Override
	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}
}
