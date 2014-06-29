package fr.iut.csid.empower.elearning.core.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.EndUser;
import fr.iut.csid.empower.elearning.core.service.dao.user.UserRepository;

@Named
public class UserResearchServiceImpl implements UserResearchService {

	@Inject
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public EndUser findByLogin(String login) {
		return userRepository.findByLogin(login);
	}

}
