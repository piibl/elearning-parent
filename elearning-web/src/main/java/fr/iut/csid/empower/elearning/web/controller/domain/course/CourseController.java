package fr.iut.csid.empower.elearning.web.controller.domain.course;

import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.CrudService;
import fr.iut.csid.empower.elearning.web.controller.domain.AbstractDomainController;
import fr.iut.csid.empower.elearning.web.dto.impl.CourseDTO;
import fr.iut.csid.empower.elearning.web.link.BatchResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.service.CourseService;
import fr.iut.csid.empower.elearning.web.service.CourseSubscriptionService;
import fr.iut.csid.empower.elearning.web.service.DTOSupport;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/dashboard/*/courses")
public class CourseController extends AbstractDomainController<Course, Long, CourseDTO> {

	private String mainView = "templates/courses";
	// private String subscribedCoursesView = "dashboards/display/student/student-courses :: display-subscribed-courses";
	private String detailsView = "display/courses :: display-details";
	private String entitiesAttributeName = "courses";
	private String singleEntityAttributeName = "course";
	private String addForm = "forms/add-forms :: add-course-form";
	private String editForm = "forms/edit-forms :: edit-course-form";
	// private String subscriptionSuccessMessageView = "dashboards/display/student/student-courses :: display-subscription-message";

	@Inject
	private CourseService courseService;
	// @Inject
	// private TeacherService teacherService;
	@Inject
	private StudentService studentService;
	@Inject
	private CourseSubscriptionService courseSubscriptionService;

	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Fonctionnalité d'inscription à un cours.<br/>
	 * Celle-ci se base sur le postulat que l'utilisateur appelant est un étudiant. <br/>
	 * TODO cette fonction ne devrait pas être assumée par un controller de domaine.... Refactor à faire, conception à peaufiner
	 * 
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(value = "{courseId}/subscribe", method = RequestMethod.GET)
	public String subscribe(Model model, @PathVariable Long courseId, @AuthenticationPrincipal User user) {
		// TODO déléguer appel
		Student student = studentService.findByLogin(user.getUsername());
		// TODO refactor
		model.addAttribute("subscribedCourse", courseSubscriptionService.subscribe(student, courseId).getCourse());
		return mainView;
	}

	/**
	 * Retourne les cours auxquels un étudiant est inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(params = { "subscribedBy" }, method = RequestMethod.GET)
	public String getSubscribedCourses(Model model, Long subscribedBy) {
		// On assume le fait que l'id est bien celui d'un étudiant
		// TODO contrôle ? cohérence des données ? recherche par login ?
		List<Course> courses = studentService.findSubscribedCourses(subscribedBy);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return mainView;
	}

	@RequestMapping(params = { "notSubscribedBy" }, method = RequestMethod.GET)
	public String getUnsubscribedCourses(Model model, Long notSubscribedBy) {
		List<Course> courses = studentService.findUnsubscribedCourses(notSubscribedBy);
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return mainView;
	}

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
