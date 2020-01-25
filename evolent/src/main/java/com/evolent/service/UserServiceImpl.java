package com.evolent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.evolent.dao.User;
import com.evolent.dao.UserDaoImpl;
import com.evolent.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDaoImpl userDaoImpl;

	public void addUser(User user) throws DataAccessException,UserNotFoundException{
		userDaoImpl.save(user);
	}

	public void updateUser(User user) throws DataAccessException, UserNotFoundException{
		userDaoImpl.update(user);
	}

	public List<User> getAllUsers() throws DataAccessException,UserNotFoundException{
		return userDaoImpl.getAll();
	}

	public User getUser(int id) throws DataAccessException,UserNotFoundException {
		return userDaoImpl.get(id);
	}

	public void deleteUser(int id) throws DataAccessException,UserNotFoundException{
		userDaoImpl.delete(id);
	}

}
