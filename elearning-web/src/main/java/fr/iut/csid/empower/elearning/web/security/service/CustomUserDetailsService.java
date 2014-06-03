package fr.iut.csid.empower.elearning.web.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Administrator;
import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.dao.user.AdministratorDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

/**
 * {@link UserDetailsService} customisé, l'information sur les utilisateurs sont issues des repositories
 */
@Named
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Inject
	private StudentDAO studentDAO;
	@Inject
	private TeacherDAO teacherDAO;
	@Inject
	private AdministratorDAO administratorDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Recherche de l'utilisateur dans les repos
		fr.iut.csid.empower.elearning.core.domain.user.User user = getUser(username);
		if (user == null) {
			logger.debug("No user registred for [" + username + "]");
			// Aucun utilisateur enregistré pour ce login
			throw new UsernameNotFoundException("User with login " + username + " doesn't exist.");
		}
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		Collection<? extends GrantedAuthority> authorities = getAuthorities(user);
		if (authorities.isEmpty()) {
			// TODO erreur, roles non définis pour ce type d'utilisateurs
		}
		return new User(user.getLogin(), user.getPassword().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				authorities);

	}

	private fr.iut.csid.empower.elearning.core.domain.user.User getUser(String login) {
		/**
		 * TODO refactor, code immonde
		 */
		fr.iut.csid.empower.elearning.core.domain.user.User user = studentDAO.findByLogin(login);
		if (user == null) {
			user = teacherDAO.findByLogin(login);
		}
		if (user == null) {
			user = administratorDAO.findByLogin(login);
		}
		return user;

	}

	/**
	 * @param user
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(fr.iut.csid.empower.elearning.core.domain.user.User user) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(user));
		for (GrantedAuthority grantedAuthority : authList) {
			logger.debug("Authority : [" + grantedAuthority.getAuthority() + "]");
		}
		return authList;
	}

	/**
	 * Retourne les rôles associés à un type d'utilisateur
	 * 
	 * @param user
	 * @return
	 */
	public List<String> getRoles(fr.iut.csid.empower.elearning.core.domain.user.User user) {
		List<String> roles = new ArrayList<String>();
		// Si admin
		if (user instanceof Administrator) {
			logger.debug("User [" + user.getLogin() + "] is administrator");
			// Tous les droits sur tout !
			roles.add("ROLE_STUDENT");
			roles.add("ROLE_TEACHER");
			roles.add("ROLE_ADMIN");

		} else if (user instanceof Teacher) {
			logger.debug("User [" + user.getLogin() + "] is teacher");
			roles.add("ROLE_TEACHER");
		} else if (user instanceof Student) {
			logger.debug("User [" + user.getLogin() + "] is student");
			roles.add("ROLE_STUDENT");
		}
		return roles;
	}

	/**
	 * Encapsule les roles dans des objets {@link SimpleGrantedAuthority}
	 * 
	 * @param roles
	 *            Liste des rôles à encapsuler
	 * @return
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
}
