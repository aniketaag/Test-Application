<%@ include file = "header.jsp" %>
	<h2>List of users</h2>
	<p class="error">${message}</p>
	<div class="row">
		<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">First Name</th>
				<th scope="col">Last Name</th>
				<th scope="col">Email</th>
				<th scope="col">Phone Number</th>
				<th scope="col">Status</th>
				<th scope="col">Action</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:out value=""/></td>
					<td><c:out value="${user.firstName}" /></td>
					<td><c:out value="${user.lastName}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.phoneNumber}" /></td>
					<td><c:out value="${user.status}" /></td>
					<td><spring:url value="/updateuser/${user.id}" var="updateUrl" /> <spring:url value="/deleteuser/${user.id}" var="deleteUrl" />
						<button class="btn btn-primary"onclick="location.href='${updateUrl}'">Edit</button>
				  		<button class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
                    </td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<spring:url value="createuser" var="createuser" />
			<button class="btn btn-primary"onclick="location.href='${createuser}'">Add New User</button>
	</div>		
<%@ include file = "footer.jsp" %>