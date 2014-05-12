package fr.iut.csid.empower.elearning.core.service.dao.course.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.NoResultException;

import fr.iut.csid.empower.elearning.core.domain.course.Course;
import fr.iut.csid.empower.elearning.core.service.dao.AbstractGenericDAO;
import fr.iut.csid.empower.elearning.core.service.dao.course.CourseDAO;

@Named
public class CourseDAOImpl extends AbstractGenericDAO<Course> implements
		CourseDAO {

	public CourseDAOImpl() {
		super(Course.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Course> findBySubscriptionNumber(Long numberOfSubscriptions) {
		List<Course> resultList = em
				.createNamedQuery(getPrefix() + "findBySubscriptionNumber")
				.setParameter("numberOfSubscriptions", numberOfSubscriptions)
				.getResultList();
		return resultList;

	}

	@Override
	public Course findByLabel(String courseLabel) {
		try {
			Course result = (Course) em
					.createNamedQuery(getPrefix() + "findByLabel")
					.setParameter("courseLabel", courseLabel).getSingleResult();
			return result;
		} catch (NoResultException e) {
			// Pas de résultat pour ce label
			return null;
		}

	}
}
