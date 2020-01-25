package com.evolent.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.evolent.dao.User;
import com.evolent.exception.UserNotFoundException;

@Service
public interface UserService {
	public void addUser(User user) throws DataAccessException, UserNotFoundException;
	public void updateUser(User user) throws DataAccessException, UserNotFoundException;
	public List<User> getAllUsers() throws DataAccessException, UserNotFoundException;
	public User getUser(int id) throws DataAccessException, UserNotFoundException;
	public void deleteUser(int id) throws DataAccessException, UserNotFoundException;
}
