package fr.iut.csid.empower.elearning.core.service.dao.course.session;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.course.session.CourseSession;
import fr.iut.csid.empower.elearning.core.domain.course.session.resource.Resource;

public interface ResourceDAO extends JpaRepository<Resource, Long> {

	/**
	 * Retourne les resources enregistrées pour un chapitre de cours
	 * 
	 * @param course
	 * @return
	 */
	public List<Resource> findByOwnerSession(CourseSession session);

}
