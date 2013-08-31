package net.devmanuals.dao;

import java.util.Date;
import java.util.List;

import net.devmanuals.model.Article;
import net.devmanuals.model.Employee;
import net.devmanuals.model.Statement;
import net.devmanuals.model.Users;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("articleDao")
@Transactional
public class ArticleDaoImpl implements ArticleDao {

	@Autowired
	private SessionFactory sessionFactory;

	// To Save the article detail
	public void saveArticle(Article article) {
		article.setDateAdded(new Date());
		sessionFactory.getCurrentSession().saveOrUpdate(article);
	}

	// To get list of all articles
	@SuppressWarnings("unchecked")
	public List<Article> listArticles() {
		return (List<Article>) sessionFactory.getCurrentSession().createCriteria(Article.class)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> listEmployee() {
		return (List<Employee>) sessionFactory.getCurrentSession().createCriteria(Employee.class)
				.list();
	}

	@Override
	public List<Statement> getstatement() {
		Criteria criteria = sessionFactory
				.getCurrentSession()
				.createCriteria(Statement.class)
				.createAlias("verb", "verbAli")
				.createAlias("object", "objectAli")
				.add(Restrictions.eq("verbAli.urlId", "http://localhost:8080/submit"))
				.add(Restrictions.eq("objectAli.url", "http://localhost:8080/assignment/100"))
				.setProjection(Projections.projectionList().add(Projections.groupProperty("users")));

		List<Users> statements = (List<Users>) criteria.list();

		for (Users statement : statements) {
			System.out.println("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc"
					+ statement.getStatements().size());
			for (Statement users : statement.getStatements()) {
				System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp"
						+ users.getStore());
			}
		}

		return null;
	}
}