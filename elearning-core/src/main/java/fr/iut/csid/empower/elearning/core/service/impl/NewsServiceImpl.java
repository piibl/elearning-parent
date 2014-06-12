package fr.iut.csid.empower.elearning.core.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import fr.iut.csid.empower.elearning.core.domain.news.News;
import fr.iut.csid.empower.elearning.core.service.NewsService;
import fr.iut.csid.empower.elearning.core.service.dao.news.NewsDAO;

@Named
public class NewsServiceImpl implements NewsService {

	@Inject
	private NewsDAO newsDAO;

	@Override
	public List<News> getNews() {
		return newsDAO.findAll();
	}
}
