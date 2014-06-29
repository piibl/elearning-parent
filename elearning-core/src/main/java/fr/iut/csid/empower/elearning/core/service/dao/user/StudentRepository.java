package fr.iut.csid.empower.elearning.core.service.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.user.Student;

/**
 * Repository étudiant
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * Retourne l'étudiant correspondant au login passé en paramètre
	 * 
	 * @param login
	 * @return
	 */
	public Student findByLogin(String login);

}
