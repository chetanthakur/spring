package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.Employee;
import net.devmanuals.model.Meeting;
import net.devmanuals.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

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
        
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
	}
	
	public List<User> listUsers() {		
		return (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
}