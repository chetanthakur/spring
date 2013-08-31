package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.Employee;
import net.devmanuals.model.Meeting;
import net.devmanuals.model.Users;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
// @Transactional
public class UserDaoImpl implements UserDao {

	private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public void saveUser(Users user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		sessionFactory.getCurrentSession().flush();

	}

	@SuppressWarnings("unchecked")
	public List<Users> listUsers() {
		Statistics statistics = sessionFactory.getStatistics();
		LOGGER.info("=====================" + statistics + "===========================");
		return (List<Users>) sessionFactory.getCurrentSession().createCriteria(Users.class).list();
	}

	public Users findByUserName(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Users where username = :username");
		query.setParameter("username", username);
		List list = query.list();
		return (Users) list.get(0);

	}
}