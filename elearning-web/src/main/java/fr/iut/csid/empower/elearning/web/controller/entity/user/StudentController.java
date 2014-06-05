package fr.iut.csid.empower.elearning.web.controller.entity.user;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.web.assembler.StudentResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.controller.entity.user.validator.StudentValidator;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/students")
public class StudentController extends AbstractEntityController<Student, Long> {

	private String mainView = "students";
	private String detailsView = "";
	private String entitiesAttributeName = "students";
	private String singleEntityAttributeName = "student";
	private String addForm = "fragment/add-forms :: add-student-form";

	@Inject
	private StudentService studentService;

	@Inject
	private StudentResourceAssembler studentResourceAssembler;

	/**
	 * Validateur objet student
	 */
	@Inject
	private StudentValidator studentValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(studentValidator);
	}

	@ModelAttribute("studentStructure")
	public Student getStudentStructure() {
		return new Student();
	}

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
	protected String getBaseView() {
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

	@Override
	protected String getAddFormPath() {
		return addForm;
	}

	@Override
	protected String getDetailsView() {
		return detailsView;
	}
}
