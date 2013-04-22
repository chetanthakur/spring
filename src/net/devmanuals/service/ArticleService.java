package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;

public interface ArticleService {

	public void addArticle(Article article);

	public List<Article> listArticles();
	
	public List<Employee> listEmployee();
}