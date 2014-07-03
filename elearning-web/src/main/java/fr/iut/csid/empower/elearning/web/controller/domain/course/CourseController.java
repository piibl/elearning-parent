package fr.iut.csid.empower.elearning.web.controller.domain.course;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractDomainController;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.DTOSupport;

@Controller
@RequestMapping("/courses")
public class CourseController extends AbstractDomainController<Course, Long, CourseDTO> {

	private String mainView = "display/courses :: display-courses";
	// private String subscribedCoursesView = "dashboards/display/student/student-courses :: display-subscribed-courses";
	private String detailsView = "display/courses :: display-details";
	private String entitiesAttributeName = "courses";
	private String singleEntityAttributeName = "course";
	private String addForm = "forms/add-forms :: add-course-form";
	private String editForm = "forms/edit-forms :: edit-course-form";
	// private String subscriptionSuccessMessageView = "dashboards/display/student/student-courses :: display-subscription-message";

	@Inject
	private CourseService courseService;

	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	@Override
	protected DTOSupport<Course, Long, CourseDTO> getDTOSupport() {
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

	@Override
	protected String getEditFormPath() {
		return editForm;
	}
}
