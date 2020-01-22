package com.evolent.validation;

public class UserValConstants {

	public static final String FNAME_CODE = "firstName";
	public static final String LNAME_CODE = "lastName";
	public static final String EMAIL_CODE = "email";
	public static final String PHNUMBER_CODE = "phoneNumber";
	public static final String STATUS_CODE = "status";
	
	public static final String FNAME_EMPTY_MSG = "First name is required.";
	public static final String LNAME_EMPTY_MSG = "* Last name is required.";
	public static final String EMAIL_EMPTY_MSG = "* Email is required.";
	public static final String PHNUMBER_EMPTY_MSG = "* Phone number is required.";
	public static final String STATUS_EMPTY_MSG = "* Status is required.";

	public static final String NAME_REGEX = "^[a-zA-Z]*$";
	public static final String EMAIL_REGEX = "(^$|^.*@.*\\..*$)";
	public static final String PHNUMBER_REGEX = "^$|\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
	
	public static final String FNAME_MSG = "* Invalid Firstname";
	public static final String LNAME_MSG = "* Invalid Lastname";
	public static final String EMAIL_MSG = "* Invalid Email";
	public static final String PHNUMBER_MSG = "* Invalid Phone Number";
	public static final String NAME_LENGTH_MSG = "* Only upto 30 charecters are allowed";
}
