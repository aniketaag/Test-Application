package com.evolent.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import com.evolent.dao.User;

@Aspect
public class UserServiceAspect {
	 
	     
		@After("execution(* com.evolent.service.UserServiceImpl.add*(..))&&" + "args(user,..)")
	    public void logAfterCreateUser(JoinPoint joinPoint, User user) 
	    {
	        System.out.println("User created: " + user );
	    }
	    
	    @After("execution(* com.evolent.service.UserServiceImpl.update*(*))&&" + "args(user,..)")
	    public void logAfterUpdateUser(JoinPoint joinPoint, User user) 
	    {
	    	System.out.println("User updated: " + user );
	    }
	    
	    @After("execution(* com.evolent.service.UserServiceImpl.delete*(*))&&" + "args(id,..)")
	    public void logAfterDeleteUser(JoinPoint joinPoint, int id) 
	    {
	    	System.out.println("User deleted: " + id );
	    }
}
