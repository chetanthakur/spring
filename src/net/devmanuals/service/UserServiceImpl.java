package net.devmanuals.service;

import java.util.List;

import net.devmanuals.dao.UserDao;
import net.devmanuals.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public UserServiceImpl() {
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addUser(User user) {
		userDao.saveUser(user);
	}
	
	public List<User> listUsers() {
		return userDao.listUsers();
	}
}