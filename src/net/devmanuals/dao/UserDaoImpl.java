package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.Employee;
import net.devmanuals.model.Meeting;
import net.devmanuals.model.User;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
//@Transactional
public class UserDaoImpl implements UserDao {
	
	private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	

	public void saveUser(User user) {
		
		Meeting meeting1 = new Meeting("Quaterly Sales meeting");
        Meeting meeting2 = new Meeting("Weekly Status meeting");
         
        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");
 
        employee1.getMeetings().add(meeting1);
        employee1.getMeetings().add(meeting2);
        employee2.getMeetings().add(meeting1);
        
        sessionFactory.getCurrentSession().save(employee1);
        sessionFactory.getCurrentSession().save(employee2);
        user.setUsername(null);
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {		
		Statistics statistics = sessionFactory.getStatistics();
		LOGGER.info("====================="+statistics+"===========================");
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
	
	public User findByUserName(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from User where username = :username");
		query.setParameter("username", username);
		List list = query.list();
		return (User) list.get(0);
        
	}
}