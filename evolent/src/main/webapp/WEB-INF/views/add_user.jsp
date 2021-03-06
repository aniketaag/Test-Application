<%@ include file="header.jsp"%>
<h2>Add User</h2>
<div>
	<form:form method="POST" action="createuser" modelAttribute="user">
		<div class="form-group">
			<label>First Name :</label>
			<form:input path="firstName" class="form-control" />
			<small><form:errors path="firstName" cssClass="error" /></small>
		</div>

		<div class="form-group">
			<label>Last Name :</label>
			<form:input path="lastName" class="form-control" />
			<small><form:errors path="lastName" cssClass="error" /></small>
		</div>

		<div class="form-group">
			<label>Email :</label>
			<form:input path="email" class="form-control" />
			<small><form:errors path="email" cssClass="error" /></small>
		</div>

		<div class="form-group">
			<label>Phone Number :</label>
			<form:input path="phoneNumber" class="form-control" />
			<small><form:errors path="phoneNumber" cssClass="error" /></small>
		</div>

		<div class="form-group">
			<label>Status :</label>
			<form:radiobutton path="status" value="Active" label="Active" />
			<form:radiobutton path="status" value="Inactive" label="Inactive" />
			<small><form:errors path="status" cssClass="error" /></small>
		</div>
		<div>
			<input type="submit" value="Register">
		</div>
	</form:form>
</div>
<%@ include file="footer.jsp"%>