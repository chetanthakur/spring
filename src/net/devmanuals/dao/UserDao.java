package net.devmanuals.dao;

import java.util.List;

import net.devmanuals.model.Users;

public interface UserDao {

	public void saveUser(Users user);

	public List<Users> listUsers();

	public Users findByUserName(String username);
}