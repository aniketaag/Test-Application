package com.evolent.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evolent.dao.User;
import com.evolent.service.UserServiceImpl;


@Controller
public class UserRestController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@RequestMapping(value="/rest/users", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(){
		return userServiceImpl.getAllUsers();
	}

	@RequestMapping(value = "/rest/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int id)
	{
		try{
			User user = userServiceImpl.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch(Exception e){
		     return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
		}
	}	
}
