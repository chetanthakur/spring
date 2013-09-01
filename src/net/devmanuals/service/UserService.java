package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.Users;

public interface UserService {

	public void addUser(Users user);

	public List<Users> listUsers();
}