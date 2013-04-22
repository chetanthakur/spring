package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;


public interface ArticleDao  {
	// To Save the article detail
	public void saveArticle ( Article Article );
	
	// To get list of all articles
	public List<Article> listArticles();
	
	// To get list of all Employees
	public List<Employee> listEmployee();
}