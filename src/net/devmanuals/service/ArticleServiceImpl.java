package net.devmanuals.service;

import java.util.List;

import net.devmanuals.dao.ArticleDao;
import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;
import net.devmanuals.model.Statement;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	private final static Logger LOGGER = Logger.getLogger(ArticleServiceImpl.class);

	@Autowired
	private ArticleDao articleDao;

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
		return articleDao.listEmployee();
	}

	@Override
	public List<Statement> getstatement() {
		return articleDao.getstatement();
	}

}