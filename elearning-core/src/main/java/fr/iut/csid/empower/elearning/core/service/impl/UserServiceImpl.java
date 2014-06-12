package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.User;
import fr.iut.csid.empower.elearning.core.service.UserService;
import fr.iut.csid.empower.elearning.core.service.dao.user.UserDAO;

/**
 * 
 */
@Named
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userDAO;


	@Override
	@Transactional(readOnly=true)
	public User findByLogin(String login) {
		return userDAO.findByLogin(login);
	}

}
