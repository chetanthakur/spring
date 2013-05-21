package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.User;


public interface UserDao  {
	
	public void saveUser ( User user );
	
	public List<User> listUsers();
	
	public User findByUserName(String username);	
}