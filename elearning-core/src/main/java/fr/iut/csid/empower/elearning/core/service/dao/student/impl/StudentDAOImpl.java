package fr.iut.csid.empower.elearning.core.service.dao.student.impl;

import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.student.Student;
import fr.iut.csid.empower.elearning.core.service.dao.AbstractGenericDAO;
import fr.iut.csid.empower.elearning.core.service.dao.student.StudentDAO;

@Named
public class StudentDAOImpl extends AbstractGenericDAO<Student> implements
		StudentDAO {

	public StudentDAOImpl() {
		super(Student.class);
	}

}
