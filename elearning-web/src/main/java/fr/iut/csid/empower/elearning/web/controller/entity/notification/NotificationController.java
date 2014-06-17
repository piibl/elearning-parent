package fr.iut.csid.empower.elearning.web.controller.entity.notification;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.service.NotificationService;
import fr.iut.csid.empower.elearning.web.service.UserService;

@Controller
@RequestMapping("*/notifications")
public class NotificationController {

	private String mainView = "display/notifications :: display-notifications";
	private String detailsView = "display/notifications :: display-details";
	private String entitiesAttributeName = "notifications";
	private String singleEntityAttributeName = "notification";

	@Inject
	private UserService userService;
	@Inject
	private NotificationService notificationService;

	// @Inject
	// private CourseResourceAssembler courseResourceAssembler;

	/**
	 * Fonctionnalité d'inscription à un cours.<br/>
	 * Celle-ci se base sur le postulat que l'utilisateur appelant est un étudiant. <br/>
	 * TODO cette fonction ne devrait pas être assumée par un controller de domaine.... Refactor à faire, conception à peaufiner
	 * 
	 * @param model
	 * @param courseId
	 * @param user
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String getNotifications(Model model, @AuthenticationPrincipal User user) {
		fr.iut.csid.empower.elearning.core.domain.user.User loggedUser = userService.findByLogin(user.getUsername());
		model.addAttribute(getEntitiesAtributeName(), notificationService.findByUser(loggedUser));
		return getBaseView();
	}

	// protected BatchResourceAssembler<Course, Resource<Course>> getResourceAssembler() {
	// return courseResourceAssembler;
	// }

	protected String getBaseView() {
		return mainView;
	}

	protected String getEntitiesAtributeName() {
		return entitiesAttributeName;
	}

	protected String getSingleEntityAtributeName() {
		return singleEntityAttributeName;
	}

	protected String getDetailsView() {
		return detailsView;
	}

}
