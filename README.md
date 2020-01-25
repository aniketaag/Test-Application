# Test-Application
Test crud application
---------------------------------------------------------------
Please follow below stpes to run the application functionality-
---------------------------------------------------------------
1. Command to download "Test-Application" project code -
	1. Open GIT Bash
	2. Go to suitable path.
	3. Run below command to make a clone of Test-Application-
		git clone https://github.com/aniketaag/Test-Application.git
	4. Else go to below path and download the code repository.
		https://github.com/aniketaag/Test-Application/tree/master/evolent

2. In Eclips, Import project as Exsting Maven Project.

3. Run the application on (Tomcat 7) server.

4. Login credentials -
	http://localhost:8080/evolent/spring_security_login
	username = test
	password = test123

5. Rest API URL's- (Postman application can be used)
		List of users - http://localhost:8080/evolent/rest/users
		Get a user	- http://localhost:8080/evolent/rest/user/{id}
		Create user - http://localhost:8080/rest/user/create
		Update User - http://localhost:8080/rest/user/update/{id}
		Delete User - http://localhost:8080/rest/user/delete/{id}

6. CRUD Application URL's -
		Home Page 	- http://localhost:8080/evolent/users
		Create User - http://localhost:8080/evolent/createuser
		Edit User 	- http://localhost:8080/updateuser/{id}
		Update User - http://localhost:8080/updateuser
		Delete User - http://localhost:8080/deleteuser/{id}

7. Junit and Mockito Testcase-
	Run JUnit Test for Rest API application. 
		
8. For any HttpStatus response error, Redirected to below url-
	http://localhost:8080/evolent/users
	
9. Code can be review here-
	https://github.com/aniketaag/Test-Application/tree/master/evolent
	
10. Technology used-
		1. Spring MVC
		2. h2 Inmemory DB
		3. JDBC Template
		4. Spring AOP
		5. Spring Transaction Management
		6. JUnit and Mockito.
		7. Bootstrap CSS.
		8. Spring Security. 
		9. Postman Application for Rest.
---------------------------------------------------------------
