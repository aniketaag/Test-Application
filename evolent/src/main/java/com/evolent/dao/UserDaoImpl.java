package com.evolent.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Transactional;

import com.evolent.exception.UserNotFoundException;

public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Transactional(rollbackFor=DataAccessException.class, readOnly = true)
	public int save(User user) throws DataAccessException {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("user").usingGeneratedKeyColumns("id");
		Map<String, Object> parameters = new HashMap<String, Object>(5);
		parameters.put("first_name", user.getFirstName());
		parameters.put("last_name", user.getLastName());
		parameters.put("email", user.getEmail());
		parameters.put("phone_number", user.getPhoneNumber());
		parameters.put("status", user.getStatus());
		Number insertedId = simpleJdbcInsert.executeAndReturnKey(parameters);
		return insertedId.intValue();
	}
	
	@Transactional
	public int update(User user) throws DataAccessException, UserNotFoundException {
		int resp = jdbcTemplate.update("update user set first_name=?, last_name=?, email=?, phone_number=?, status=? where id = ?",
				user.firstName, user.lastName, user.email, user.phoneNumber, user.status, user.id);
		if(resp == 0){
			throw new UserNotFoundException("User not found");
		}
		return resp;
	}

	@Transactional
	public List<User> getAll() throws DataAccessException {
		List<User> users = (List<User>) jdbcTemplate.query("select * from user", new UserRowMapper());
		return users;
	}

	@Transactional
	public int delete(int id) throws DataAccessException,UserNotFoundException {
		int resp = jdbcTemplate.update("delete from user where id = ?", id);
		if(resp == 0){
			throw new UserNotFoundException("User not found");
		}
		return resp;
	}

	@Transactional
	public int deleteAll() throws DataAccessException,UserNotFoundException {
		int resp = jdbcTemplate.update("delete from user");
		if(resp == 0){
			throw new UserNotFoundException("User not found");
		}
		return resp;
	}
	
	@Transactional
	public User get(int id) throws DataAccessException,UserNotFoundException {
		User user = (User) jdbcTemplate.queryForObject("select * from user where id = ?", new Object[] { id }, new UserRowMapper());
		if(null == user){
			throw new UserNotFoundException("User not found");
		}
		return user;
	}
}
