package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Teacher;
import fr.iut.csid.empower.elearning.core.service.TeacherService;
import fr.iut.csid.empower.elearning.core.service.dao.user.TeacherDAO;

/**
 * 
 */
@Named
public class TeacherServiceImpl extends AbstractCrudService<Teacher, Long> implements TeacherService {

	@Inject
	private TeacherDAO teacherDAO;

	@Override
	protected JpaRepository<Teacher, Long> getDAO() {
		return teacherDAO;
	}

}
