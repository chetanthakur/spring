package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.User;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface UserService {

	public void addUser(User user);
	
	public List<User> listUsers();
}