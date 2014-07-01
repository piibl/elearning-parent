package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/dashboard/student")
public class StudentDashboardController extends AbstractDashboardController {

	// paths
	private static final String coursesView = "templates/courses";
	// Services
	@Inject
	private StudentService studentService;
	@Inject
	private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Retourne le lien vers l'interface de cours -- lien de la navbar
	 * 
	 * @return
	 */
	@ModelAttribute("coursesLink")
	public Link getCoursesLink() {
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).withSelfRel();
	}

	/**
	 * Retourne les cours auxquels un étudiant est inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/courses/subscribed", method = RequestMethod.GET)
	public String getSubscribedCourses(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("isSubscribedView", true);
		// On assume le fait que l'id est bien celui d'un étudiant
		// TODO contrôle ? cohérence des données ? recherche par login ?
		List<Course> courses = studentService.findSubscribedCourses(studentService.findByLogin(user.getUsername()).getId());
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return coursesView;
	}

	@RequestMapping(value = "/courses", method = RequestMethod.GET)
	public String getCourses(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("isMainView", true);
		model.addAttribute("subscribedCoursesLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.withSelfRel());
		model.addAttribute("othersAvailablesCoursesLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.NOTSUBSCRIBED.getPath())
						.withSelfRel());
		return coursesView;
	}

	/**
	 * Retourne les cours auquels un étudiant n'est pas inscrit
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/courses/not-subscribed", method = RequestMethod.GET)
	public String getUnsubscribedCourses(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("isNotSubscribedView", true);
		// Pour rechargement de la page après inscription à un cours
		model.addAttribute("othersAvailablesCoursesLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.NOTSUBSCRIBED.getPath())
						.withSelfRel());
		List<Course> courses = studentService.findUnsubscribedCourses(studentService.findByLogin(user.getUsername()).getId());
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
		return coursesView;
	}

	@Override
	protected String getMainView() {
		return PathFragment.STUDENT_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return StudentDashboardController.class;
	}

}
