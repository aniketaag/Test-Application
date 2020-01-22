package com.evolent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.evolent.service.UserServiceImpl;
import com.evolent.validation.UserValidator;

public abstract class AbstractUserController {
	
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	UserServiceImpl userServiceImpl;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}

}
