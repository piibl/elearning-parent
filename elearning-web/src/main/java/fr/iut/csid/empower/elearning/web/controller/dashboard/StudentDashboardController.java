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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.web.link.assembler.CourseResourceAssembler;
import fr.iut.csid.empower.elearning.web.link.assembler.SessionResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.service.CourseSessionService;
import fr.iut.csid.empower.elearning.web.service.CourseSubscriptionService;
import fr.iut.csid.empower.elearning.web.service.ResourceService;
import fr.iut.csid.empower.elearning.web.service.StudentService;

@Controller
@RequestMapping("/dashboard/student")
public class StudentDashboardController extends AbstractDashboardController {

	// paths
	private static final String coursesView = "templates/courses";
	private static final String courseView = "templates/course";
	private static final String sessionView = "templates/session";
	private static final String partialSubscribedView = "templates/partial/courses-partial :: subscribed-partial";
	private static final String partialNotSubscribedView = "templates/partial/courses-partial :: not-subscribed-partial";
	// Services
	@Inject
	private StudentService studentService;
	@Inject
	private CourseSubscriptionService courseSubscriptionService;
	@Inject
	private CourseSessionService courseSessionService;
	@Inject
	private ResourceService resourceService;
	@Inject
	private SessionResourceAssembler sessionResourceAssembler;
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
	 * Retourne les cours auxquels un étudiant est inscrit
	 * 
	 * @param model
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/courses/subscribed", method = RequestMethod.GET)
	public String getSubscribedCourses(Model model, @AuthenticationPrincipal User user) {
		buildSubscribedViews(model, user);
		return coursesView;
	}

	/**
	 * Fonctionnalité de désinscription à un cours.<br/>
	 * 
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(value = "/courses/subscribed/{courseId}/unsubscribe", method = RequestMethod.GET)
	public @ResponseBody
	void unsubscribe(Model model, @PathVariable Long courseId, @AuthenticationPrincipal User user) {

		Student student = studentService.findByLogin(user.getUsername());
		courseSubscriptionService.unsubscribe(student, courseId);

	}

	@RequestMapping(value = "/courses/subscribed/partial", method = RequestMethod.GET)
	public String getSubscribedPartialView(Model model, @AuthenticationPrincipal User user) {
		buildSubscribedViews(model, user);
		return partialSubscribedView;
	}

	@RequestMapping(value = "/courses/subscribed/{courseId}", method = RequestMethod.GET)
	public String getCourseView(Model model, @PathVariable Long courseId, @AuthenticationPrincipal User user) {
		model.addAttribute("course", courseResourceAssembler.toResource(studentService.findSubscribedCourse(courseId)));
		model.addAttribute("baseLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.withSelfRel());
		// Retourne la vue d'un cours en particulier
		return courseView;
	}

	@RequestMapping(value = "/courses/subscribed/{courseId}/sessions/{sessionRank}", method = RequestMethod.GET)
	public String getSessionView(Model model, @PathVariable Long courseId, @PathVariable Long sessionRank, @AuthenticationPrincipal User user) {
		CourseSession session = courseSessionService.findByCourseAndSessionRank(courseId, sessionRank);
		model.addAttribute("courseSession", session);
		model.addAttribute("resources", sessionResourceAssembler.toResource(resourceService.findByOwner(session.getId())));
		model.addAttribute("baseLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.withSelfRel());
		// Retourne la vue d'un cours en particulier
		return sessionView;
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
		buildNotSubscribedViews(model, user);
		return coursesView;
	}

	/**
	 * Fonctionnalité d'inscription à un cours.<br/>
	 * Retourne une vue partielle pour chargement ajax
	 * 
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(value = "/courses/not-subscribed/{courseId}/subscribe", method = RequestMethod.GET)
	public @ResponseBody
	void subscribe(Model model, @PathVariable Long courseId, @AuthenticationPrincipal User user) {
		Student student = studentService.findByLogin(user.getUsername());
		courseSubscriptionService.subscribe(student, courseId);
	}

	@RequestMapping(value = "/courses/not-subscribed/partial", method = RequestMethod.GET)
	public String getNotSubscribedPartialView(Model model, @AuthenticationPrincipal User user) {
		buildNotSubscribedViews(model, user);
		return partialNotSubscribedView;
	}

	@Override
	protected String getMainView() {
		return PathFragment.STUDENT_DASHBOARD.getPath();
	}

	@Override
	protected Class<?> getConcreteClass() {
		return StudentDashboardController.class;
	}

	/*
	 * UTILITAIRES
	 */

	private void buildSubscribedViews(Model model, User user) {
		model.addAttribute("isSubscribedView", true);
		// Base des liens de désinscription
		model.addAttribute("baseLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.withSelfRel());
		// Pour rechargement de la page après désinscription à un cours
		model.addAttribute(
				"redirectLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.slash(PathFragment.PARTIAL.getPath()).withSelfRel());
		model.addAttribute("othersAvailablesCoursesLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.NOTSUBSCRIBED.getPath())
						.withSelfRel());
		// On assume le fait que l'id est bien celui d'un étudiant
		// TODO contrôle ? cohérence des données ? recherche par login ?
		List<Course> courses = studentService.findSubscribedCourses(studentService.findByLogin(user.getUsername()).getId());
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
	}

	private void buildNotSubscribedViews(Model model, User user) {
		model.addAttribute("isNotSubscribedView", true);
		// Base des liens d'inscription
		model.addAttribute("baseLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.NOTSUBSCRIBED.getPath())
						.withSelfRel());
		// Pour rechargement de la page après inscription à un cours
		model.addAttribute("redirectLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.NOTSUBSCRIBED.getPath())
						.slash(PathFragment.PARTIAL.getPath()).withSelfRel());
		model.addAttribute("subscribedCoursesLink",
				linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.COURSES.getPath()).slash(PathFragment.SUBSCRIBED.getPath())
						.withSelfRel());
		List<Course> courses = studentService.findUnsubscribedCourses(studentService.findByLogin(user.getUsername()).getId());
		// Construction des liens d'action et mise en container
		// Le container contient à la fois l'objet cible et les liens des ressources afférentes
		List<Resource<Course>> coursesResources = courseResourceAssembler.toResource(courses);
		model.addAttribute("courses", coursesResources);
	}

}
