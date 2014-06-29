package fr.iut.csid.empower.elearning.web.controller.dashboard;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.core.reference.UserRole;
import fr.iut.csid.empower.elearning.core.service.UserResearchService;
import fr.iut.csid.empower.elearning.web.link.ControllerLinkBuilderFactory;
import fr.iut.csid.empower.elearning.web.reference.PathFragment;

/**
 * Controller dont le seul rôle est de dispatcher les requêtes sur les pages des différents types d'utilisateur
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Inject
	private ControllerLinkBuilderFactory linkBuilderFactory;

	@Inject
	private UserResearchService userResearchService;

	@RequestMapping(method = RequestMethod.GET)
	public String getDashboardPage(Model model, @AuthenticationPrincipal User user) {
		if (user != null) {
			EndUser endUser = userResearchService.findByLogin(user.getUsername());
			if (endUser != null) {
				// Admin
				if (endUser.getRole().equals(UserRole.ADMINISTRATOR)) {
					return PathFragment.REDIRECT.getPath() + "dashboard/admin";
				}
				// Enseignant
				else if (endUser.getRole().equals(UserRole.TEACHER)) {
					return PathFragment.REDIRECT.getPath() + "dashboard/teacher";
				}
				// Etudiant
				else if (endUser.getRole().equals(UserRole.STUDENT)) {
					return PathFragment.REDIRECT.getPath() + "dashboard/student";
				}
				// TODO erreur role inconnu
			}
			// TODO erreur utilisateur non connu
		}
		// Retourne la page d'accueil, comportement par défaut si non loggué
		return PathFragment.HOME.getPath();
	}
}
