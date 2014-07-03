package fr.iut.csid.empower.elearning.web.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String getHomePage(Model model, @AuthenticationPrincipal User user) {
		if (user == null) {
			// Retourne la page d'accueil
			return PathFragment.HOME.getPath();
		} else {
			return PathFragment.REDIRECT.getPath() + PathFragment.DASHBOARD.getPath();
		}

	}
}
