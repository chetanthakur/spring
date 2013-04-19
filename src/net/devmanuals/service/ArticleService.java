package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.Article;

public interface ArticleService {

	public void addArticle(Article article);

	public List<Article> listArticles();
}