package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.User;

public interface UserService {

	public void addUser(User user);
	
	public List<User> listUsers();
}