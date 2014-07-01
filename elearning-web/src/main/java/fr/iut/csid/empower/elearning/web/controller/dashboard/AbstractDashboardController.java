package fr.iut.csid.empower.elearning.web.controller.dashboard;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

public abstract class AbstractDashboardController {

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	/**
	 * Retourne la vue correspondant au tableau de bord
	 */
	protected abstract String getMainView();

	/**
	 * Retourne la classe concrète, permettant de construire des liens vers celle-ci
	 */
	protected abstract Class<?> getConcreteClass();

	/**
	 * Retourne le login de l'utilisateur loggué
	 * 
	 * @param user
	 * @return
	 */
	@ModelAttribute("login")
	public String getLogin(@AuthenticationPrincipal User user) {
		// TODO nullcheck sur entité. Le cas ne devrait pas se produire, mais sait-on jamais...
		return user.getUsername();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboardPage(Model model, @AuthenticationPrincipal User user) {
		model.addAttribute("dashboardLink", linkBuilderFactory.linkTo(getConcreteClass()).withSelfRel());
		model.addAttribute("notificationsLink", linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.NOTIFICATIONS.getPath())
				.withSelfRel());
		model.addAttribute("newsLink", linkBuilderFactory.linkTo(getConcreteClass()).slash(PathFragment.NEWS.getPath()).withSelfRel());
		// Retourne la page d'accueil
		return getMainView();
	}
}
