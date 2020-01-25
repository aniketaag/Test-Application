package com.evolent.controllers;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.evolent.controllers.AbstractUserController;
import com.evolent.dao.User;
import com.evolent.exception.UserNotFoundException;

@RestController
public class UserRestController extends AbstractUserController{
	
	/****** Fetch all users *******/
	@RequestMapping(value= UserURIConstants.GET_REST_ALL_USER, method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(){
		return userServiceImpl.getAllUsers();
	}

	/******* Fetch single user *******/
	@RequestMapping(value = UserURIConstants.GET_REST_USER, method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") int id)
	{
		try{
			User user = userServiceImpl.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch(Exception e){
		     return new ResponseEntity<String>("User not exist", HttpStatus.NOT_FOUND);
		}
	}
	
	/******* Create user *******/
	@RequestMapping(value = UserURIConstants.CREATE_REST_USER, method=RequestMethod.POST)
	public ResponseEntity<?> createUser( @Validated @RequestBody User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()){
			return new ResponseEntity<List<FieldError>>(bindingResult.getFieldErrors(), HttpStatus.NOT_FOUND);
		}
		
		try{
			userServiceImpl.addUser(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch(Exception e){
		     return new ResponseEntity<String>("Exception Occurred", HttpStatus.NOT_FOUND);
		}
	}

	/******* Update user *******/
	@RequestMapping(value = UserURIConstants.UPDATE_REST_USER, method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@Validated @RequestBody User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()){
			return new ResponseEntity<List<FieldError>>(bindingResult.getFieldErrors(), HttpStatus.NOT_FOUND);
		}
		try{
			userServiceImpl.updateUser(user);
			return new ResponseEntity<String>("User updated", HttpStatus.OK);
		} catch(DataAccessException e){
		     return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		} catch(UserNotFoundException de){
			 return new ResponseEntity<String>(de.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	/******* Delete user *******/
	@RequestMapping(value = UserURIConstants.DELETE_REST_USER, method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id)
	{
		try{
			userServiceImpl.deleteUser(id);
			return new ResponseEntity<String>("User deleted", HttpStatus.OK);
		} catch(DataAccessException e){
		     return new ResponseEntity<String>("User not found", HttpStatus.NOT_FOUND);
		} catch(UserNotFoundException de){
			 return new ResponseEntity<String>(de.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
