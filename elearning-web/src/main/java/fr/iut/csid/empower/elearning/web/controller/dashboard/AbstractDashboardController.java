package fr.iut.csid.empower.elearning.web.controller.dashboard;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;

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

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboardPage(Model model, Principal principal) {
		model.addAttribute("login", principal.getName());
		model.addAttribute("dashboardLink", linkBuilderFactory.linkTo(getConcreteClass()).withSelfRel());
		// Retourne la page d'accueil
		return getMainView();
	}
}
