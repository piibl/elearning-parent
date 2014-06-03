package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;

/**
 * 
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Student, Long> implements StudentService{

	@Inject
	private StudentDAO studentDAO;

	@Override
	protected JpaRepository<Student, Long> getDAO() {
		return studentDAO;
	}

}
