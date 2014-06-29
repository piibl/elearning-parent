package fr.iut.csid.empower.elearning.web.controller.dashboard;

import javax.inject.Inject;

import org.springframework.hateoas.Link;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.reference.Relation;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/dashboard/student")
public class StudentDashboardController extends AbstractDashboardController {

	// Services
	@Inject
	private StudentService studentService;

	/**
	 * Retourne le lien vers les cours auquels a souscrit l'étudiant
	 * 
	 * @param user
	 * @return
	 */
	@ModelAttribute("subscribedCoursesLink")
	public Link getSubscribedCoursesLink(@AuthenticationPrincipal User user) {
		// Recherche de l'utilisateur
		Student student = studentService.findByLogin(user.getUsername());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath())
				.addRequestParam("subscribedBy", student.getId().toString()).withRel(Relation.SUBSCRIBED.getName());
	}

	/**
	 * Retourne le lien vers les cours auquels l'étudiant n'est pas inscrit
	 * 
	 * @param principal
	 * @return
	 */
	@ModelAttribute("othersAvailablesCoursesLink")
	public Link getAvailableCoursesLink(@AuthenticationPrincipal User user) {
		// Recherche de l'utilisateur
		Student student = studentService.findByLogin(user.getUsername());
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath())
				.addRequestParam("notSubscribedBy", student.getId().toString()).withRel(Relation.NOTSUBSCRIBED.getName());
	}

	/**
	 * Retourne les cours auxquels l'étudiant est inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	// @RequestMapping(value = "/{studentId}/courses", method = RequestMethod.GET)
	// public String getSubscribedCourses(Model model, @PathVariable Long studentId) {
	// List<Course> courses = studentService.findSubscribedCourses(studentId);
	// // Construction des liens d'action et mise en container
	// // Le container contient à la fois l'objet cible et les liens des ressources afférentes
	// List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
	// model.addAttribute("courses", coursesResources);
	// return subscribedCoursesView;
	// }

	// /**
	// * Retourne les cours auxquels l'étudiant n'est pas inscrit
	// *
	// * @param model
	// * @param studentId
	// * @return
	// */
	// @RequestMapping(value = "/{studentId}/courses/not-subscribed", method = RequestMethod.GET)
	// public String getUnsubscribedCourses(Model model, @PathVariable Long studentId) {
	// List<Course> courses = studentService.findUnsubscribedCourses(studentId);
	// // Construction des liens d'action et mise en container
	// // Le container contient à la fois l'objet cible et les liens des ressources afférentes
	// List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
	// model.addAttribute("courses", coursesResources);
	// return notSubscribedCoursesView;
	// }

	@Override
	protected String getMainView() {
		return PathFragment.STUDENT_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return StudentDashboardController.class;
	}

}
