package fr.iut.csid.empower.elearning.web.controller.domain.notification;

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

import fr.iut.csid.empower.elearning.core.domain.notification.Notification;
import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.web.controller.dashboard.StudentDashboardController;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.link.assembler.NotificationResourceAssembler;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;
import fr.iut.csid.empower.elearning.web.service.NotificationService;
import fr.iut.csid.empower.elearning.web.service.UserService;

@Controller
public class NotificationController {

	private String mainView = "display/notifications :: display-notifications";
	private String templateView = "templates/notifications";

	private String entitiesAttributeName = "notifications";

	@Inject
	private UserService userService;
	@Inject
	private NotificationService notificationService;
	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	@Inject
	private NotificationResourceAssembler notificationResourceAssembler;

	/**
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String getNotifications(Model model, @AuthenticationPrincipal User user) {
		EndUser loggedUser = userService.findByLogin(user.getUsername());
		List<Resource<Notification>> notifications = notificationResourceAssembler.toResource(notificationService.findByUser(loggedUser));
		model.addAttribute(getEntitiesAtributeName(), notifications);
		return getBaseView();
	}

	@RequestMapping(value = "dashboard/notifications", method = RequestMethod.GET)
	public String getNotificationsInDashboard(Model model, @AuthenticationPrincipal User user) {
		EndUser loggedUser = userService.findByLogin(user.getUsername());
		List<Resource<Notification>> notifications = notificationResourceAssembler.toResource(notificationService.findByUser(loggedUser));
		model.addAttribute(getEntitiesAtributeName(), notifications);
		// TODO refactor
		model.addAttribute("coursesLink", linkBuilderFactory.linkTo(StudentDashboardController.class).slash(PathFragment.COURSES.getPath())
				.withSelfRel());
		return templateView;
	}

	/**
	 * Supprime l'entité correspondant à l'id passé en paramètre
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "notifications/{notificationId}/delete", method = RequestMethod.GET)
	public String deleteEntity(@PathVariable("notificationId") Long id, Model model, @AuthenticationPrincipal User user) {
		Notification entityTodelete = notificationService.find(id);
		notificationService.delete(entityTodelete);
		return getNotifications(model, user);
	}

	// protected BatchResourceAssembler<Course, SessionResource<Course>> getResourceAssembler() {
	// return courseResourceAssembler;
	// }

	protected String getBaseView() {
		return mainView;
	}

	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

}
