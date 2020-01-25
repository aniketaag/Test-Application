package com.evolent.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evolent.dao.User;
import com.evolent.exception.UserNotFoundException;

@Controller
public class UserController extends AbstractUserController{

	/****** Fetch all users *******/
	@RequestMapping(value = UserURIConstants.GET_ALL_USER, method = RequestMethod.GET)
	public String listUsers(Model model) {
		List<User> users = userServiceImpl.getAllUsers();
		model.addAttribute("users", users);
		return "index";
	}
	
	/******* Add user page*******/
	@RequestMapping(value = UserURIConstants.CREATE_USER, method = RequestMethod.GET)
	public String createUser(Model model) {
		model.addAttribute("user", new User());
		return "add_user";
	}
	
	/******* Edit user page *******/
	@RequestMapping(value = UserURIConstants.EDIT_USER, method = RequestMethod.GET)
	public String editUser(@PathVariable("id") int id,RedirectAttributes attributes, Model model) {
		User user = null;
		try{
			user = userServiceImpl.getUser(id);
			model.addAttribute("user", user);
			return "edit_user";
		}catch(DataAccessException de){
			attributes.addFlashAttribute("message", "User not exist!");
		}catch(UserNotFoundException de){
			attributes.addFlashAttribute("message", de.getMessage());
		}
		return "redirect:" + UserURIConstants.GET_ALL_USER;
	}

	/******* Create user *******/
	@RequestMapping(value = UserURIConstants.CREATE_USER, method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("user") User user, @Validated User validateduser, BindingResult bindingResult,RedirectAttributes attributes, Model model) {
		if (bindingResult.hasErrors()) return "add_user";
		try{
			userServiceImpl.addUser(user);
			attributes.addFlashAttribute("message", "User created successfully.");
		}catch(DataAccessException de){
			attributes.addFlashAttribute("message", "Exception Occured: Unable to create user.");
		}
		return "redirect:" + UserURIConstants.GET_ALL_USER;
	}
	
	/******* Update user *******/
	@RequestMapping(value = UserURIConstants.UPDATE_USER, method = RequestMethod.POST)
	public String UpdateForm(@ModelAttribute("user") User user, @Validated User validateduser, BindingResult bindingResult,RedirectAttributes attributes, Model model) {
		if (bindingResult.hasErrors()) return "edit_user";
		try{
			userServiceImpl.updateUser(user);
			attributes.addFlashAttribute("message", "User updated successfully");
		}catch(DataAccessException de){
			attributes.addFlashAttribute("message", "Exception Occured: Unable to update user.");
		}catch(UserNotFoundException de){
			attributes.addFlashAttribute("message", de.getMessage());
		}
		
		return "redirect:" + UserURIConstants.GET_ALL_USER;
	}
	
	/******* Delete user *******/
	@RequestMapping(value = UserURIConstants.DELETE_USER, method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id,RedirectAttributes attributes, Model model) {
		try{
			userServiceImpl.deleteUser(id);
			attributes.addFlashAttribute("message", "User deleted!");
		}catch(DataAccessException de){
			attributes.addFlashAttribute("message", "Exception Occured: Unable to delete user.");
		}catch(UserNotFoundException de){
			attributes.addFlashAttribute("message", de.getMessage());
		}	
		return "redirect:" + UserURIConstants.GET_ALL_USER;
	}
	
	/******* Error handling *******/
	@RequestMapping(value = UserURIConstants.ERRORS, method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest) {
		return "redirect:" + UserURIConstants.GET_ALL_USER;
	}
}
