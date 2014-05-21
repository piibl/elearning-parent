package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * DAO étudiant
 * 
 */
public interface StudentDAO extends JpaRepository<Student, Long> {

}
