package com.evolent.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 100L;

	public UserNotFoundException(String message){
		super(message);	
	}
}
