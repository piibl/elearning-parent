package fr.iut.csid.empower.elearning.web.controller.dashboard;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;

/**
 * Controller dont le seul rôle est de dispatcher les requêtes sur les pages des différents types d'utilisateur
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController {

	@Inject
	protected ControllerLinkBuilderFactory linkBuilderFactory;

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboardPage(Model model, @AuthenticationPrincipal User user) {
		// Retourne la page d'accueil
		return null;
	}
}
