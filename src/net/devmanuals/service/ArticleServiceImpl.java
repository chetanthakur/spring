package net.devmanuals.service;

import java.util.List;

import net.devmanuals.dao.ArticleDao;
import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	public CacheManager cacheManager;

	public ArticleServiceImpl() {
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addArticle(Article article) {
		articleDao.saveArticle(article);
	}

	public List<Article> listArticles() {
		System.out.println("listArticles()");
		return articleDao.listArticles();
	}

	public List<Employee> listEmployee() {
		Cache cache=cacheManager.getCache("employee");
		cache.put(new Element("chetan",articleDao.listEmployee()));
		return articleDao.listEmployee();
	}

}