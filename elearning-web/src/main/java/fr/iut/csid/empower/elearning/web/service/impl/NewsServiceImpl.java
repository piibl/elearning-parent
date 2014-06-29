package fr.iut.csid.empower.elearning.web.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.news.News;
import fr.iut.csid.empower.elearning.core.service.dao.news.NewsRepository;
import fr.iut.csid.empower.elearning.web.service.NewsService;

@Named
public class NewsServiceImpl implements NewsService {

	@Inject
	private NewsRepository newsRepository;

	@Override
	public List<News> getNews() {
		return newsRepository.findAll();
	}
}
