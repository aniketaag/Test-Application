# Test-Application
Test crud application
---------------------------------------------------------------
Please for below stpes to run the application functionality-
---------------------------------------------------------------
1. Command to download "Test-Application" project code -
	1. Open GIT Bash
	2. Go to suitable path.
	3. Run below command to make a clone of Test-Application-
		git clone https://github.com/aniketaag/Test-Application.git

2. In Eclips,Import project as Exsting Maven Project.

3. Run the application on (Tomcat 7) server.

4. Hit the below Url to Add new user-
	http://localhost:8080/evolent/adduser

5. To perfom operations on users hit below url-
	http://localhost:8080/evolent/homepage
	
6. Edit, Update or delete the user on below request-
	http://localhost:8080/evolent/homepage

7. Hit below url to generate REST API request-
	For all users - http://localhost:8080/evolent/rest/users
	For specific user - http://localhost:8080/evolent/rest/user/{id}

8. For any HttpStatus response error, Redirected to below url-
	http://localhost:8080/evolent/homepage
	
9. Code can be review here-
	https://github.com/aniketaag/Test-Application/tree/master/evolent
---------------------------------------------------------------

