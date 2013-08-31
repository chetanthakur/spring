package net.devmanuals.service;

import java.util.List;

import net.devmanuals.model.Users;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface UserService {

	public void addUser(Users user);

	public List<Users> listUsers();
}