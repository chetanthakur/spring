package net.devmanuals.service;

import java.util.List;

import net.devmanuals.dao.ArticleDao;
import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;
import net.sf.ehcache.CacheManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;

@Service("articleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl implements ArticleService {
	
	private final static Logger LOGGER = Logger.getLogger(ArticleServiceImpl.class); 
	
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

	@Cacheable(cacheName = "employee")
	public List<Employee> listEmployee() {
		return articleDao.listEmployee();
	}

}