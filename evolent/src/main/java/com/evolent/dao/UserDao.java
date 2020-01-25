package com.evolent.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.evolent.exception.UserNotFoundException;

public interface UserDao {
	public int save(User user)throws DataAccessException, UserNotFoundException;
	public List<User> getAll()throws DataAccessException, UserNotFoundException;
	public int delete(int id)throws DataAccessException, UserNotFoundException;
	public int deleteAll()throws DataAccessException, UserNotFoundException;
	public User get(int id)throws DataAccessException, UserNotFoundException;
}
