package fr.iut.csid.empower.elearning.core.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.iut.csid.empower.elearning.core.domain.user.Student;
import fr.iut.csid.empower.elearning.core.dto.UserDTO;
import fr.iut.csid.empower.elearning.core.service.StudentService;
import fr.iut.csid.empower.elearning.core.service.dao.user.StudentDAO;

/**
 * 
 */
@Named
public class StudentServiceImpl extends AbstractCrudService<Student, Long> implements StudentService {

	@Inject
	private StudentDAO studentDAO;

	@Override
	protected JpaRepository<Student, Long> getDAO() {
		return studentDAO;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Student saveFromDTO(UserDTO entityDTO) {
		Student student = new Student(entityDTO.getFirstName(), entityDTO.getLastName(), entityDTO.getLogin(), entityDTO.getPassword(),
				entityDTO.getEmail());
		return studentDAO.save(student);
	}

}
