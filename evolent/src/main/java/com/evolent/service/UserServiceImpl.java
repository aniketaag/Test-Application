package com.evolent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.evolent.dao.User;
import com.evolent.dao.UserDaoImpl;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDaoImpl userDaoImpl;

	public int addUser(User user) throws DataAccessException{
		return userDaoImpl.save(user);
	}

	public int updateUser(User user) throws DataAccessException{
		return userDaoImpl.update(user);
	}

	public List<User> getAllUsers() throws DataAccessException{
		return userDaoImpl.getAll();
	}

	public User getUser(int id) throws DataAccessException {
		return userDaoImpl.get(id);
	}

	public int deleteUser(int id) throws DataAccessException{
		return userDaoImpl.delete(id);
	}

	public int deleteAllUser() throws DataAccessException{
		return userDaoImpl.deleteAll();
	}
}
