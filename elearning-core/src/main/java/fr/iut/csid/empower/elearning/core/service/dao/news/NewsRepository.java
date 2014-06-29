package fr.iut.csid.empower.elearning.core.service.dao.news;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iut.csid.empower.elearning.core.domain.news.News;

public interface NewsRepository extends JpaRepository<News, Long> {

}
