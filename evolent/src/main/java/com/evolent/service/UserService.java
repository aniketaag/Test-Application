package com.evolent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.evolent.dao.User;

@Service
public interface UserService {
	public int addUser(User user);
	public int updateUser(User user);
	public List<User> getAllUsers();
	public User getUser(int id);
	public int deleteUser(int id);
	public int deleteAllUser();
}
