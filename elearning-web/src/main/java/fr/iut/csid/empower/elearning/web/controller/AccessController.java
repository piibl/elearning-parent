package fr.iut.csid.empower.elearning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.web.reference.PathFragment;

@Controller
public class AccessController {

	@RequestMapping(value = { "/denied" }, method = RequestMethod.GET)
	public String getMainPage(Model model) {
		// Retourne la page d'accès refusé
		return PathFragment.BASE.getName() + "denied";
	}
}
