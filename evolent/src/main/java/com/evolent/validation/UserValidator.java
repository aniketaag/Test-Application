package com.evolent.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.evolent.dao.User;

public class UserValidator implements Validator {

	final int MAX_LENGTH = 20;
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserValConstants.FNAME_CODE, UserValConstants.FNAME_CODE,UserValConstants.FNAME_EMPTY_MSG);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserValConstants.LNAME_CODE, UserValConstants.LNAME_CODE, UserValConstants.LNAME_EMPTY_MSG);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserValConstants.EMAIL_CODE, UserValConstants.EMAIL_CODE, UserValConstants.EMAIL_EMPTY_MSG);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserValConstants.PHNUMBER_CODE, UserValConstants.PHNUMBER_CODE,UserValConstants.PHNUMBER_EMPTY_MSG);
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, UserValConstants.STATUS_CODE, UserValConstants.STATUS_CODE, UserValConstants.STATUS_EMPTY_MSG);
		
		User user = (User) target;

		if (!user.getFirstName().matches(UserValConstants.NAME_REGEX)) {
			errors.rejectValue(UserValConstants.FNAME_CODE,UserValConstants.FNAME_CODE, UserValConstants.FNAME_MSG);
		} else if(user.getFirstName().length() > MAX_LENGTH){
			errors.rejectValue(UserValConstants.FNAME_CODE,UserValConstants.FNAME_CODE, UserValConstants.NAME_LENGTH_MSG);
		}
		
		if (!errors.hasFieldErrors(UserValConstants.LNAME_CODE) && !user.getLastName().matches(UserValConstants.NAME_REGEX)) {
			errors.rejectValue(UserValConstants.LNAME_CODE,UserValConstants.LNAME_CODE, UserValConstants.LNAME_MSG);
		} else if(user.getLastName().length() > MAX_LENGTH){
			errors.rejectValue(UserValConstants.LNAME_CODE,UserValConstants.LNAME_CODE, UserValConstants.NAME_LENGTH_MSG);
		}
		
		if (!errors.hasFieldErrors(UserValConstants.EMAIL_CODE) && !user.getEmail().matches(UserValConstants.EMAIL_REGEX)) {	
			errors.rejectValue(UserValConstants.EMAIL_CODE,UserValConstants.EMAIL_CODE, UserValConstants.EMAIL_MSG);
		}
		
		if (!errors.hasFieldErrors(UserValConstants.PHNUMBER_CODE) && !user.getPhoneNumber().matches(UserValConstants.PHNUMBER_REGEX)) {
			errors.rejectValue(UserValConstants.PHNUMBER_CODE,UserValConstants.PHNUMBER_CODE,UserValConstants.PHNUMBER_MSG);
		}
		
		if (!errors.hasFieldErrors(UserValConstants.STATUS_CODE) && (!UserValConstants.ACTIVE_STATUS.equals(user.getStatus()) && !UserValConstants.INACTIVE_STATUS.equals(user.getStatus()))) {
			errors.rejectValue(UserValConstants.STATUS_CODE,UserValConstants.STATUS_CODE,UserValConstants.STATUS_MSG);
		}
	}
}
