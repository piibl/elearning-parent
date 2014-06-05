package fr.iut.csid.empower.elearning.web.controller.entity.course;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.dto.CourseDTO;
import fr.iut.csid.empower.elearning.core.service.CourseService;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.controller.entity.AbstractEntityController;
import fr.iut.csid.empower.elearning.web.hateoas.BatchResourceAssembler;

@Controller
@RequestMapping("/courses")
public class CourseController extends AbstractEntityController<Course, Long, CourseDTO> {

	private String mainView = "display/courses :: display-courses";
	private String detailsView = "display/courses :: display-details";
	private String entitiesAttributeName = "courses";
	private String singleEntityAttributeName = "course";
	private String addForm = "fragment/add-forms :: add-course-form";

	@Inject
	private CourseService courseService;

	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	@Override
	protected CrudService<Course, Long, CourseDTO> getCrudService() {
		return courseService;
	}

	@Override
	protected BatchResourceAssembler<Course, Resource<Course>> getResourceAssembler() {
		return courseResourceAssembler;
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
