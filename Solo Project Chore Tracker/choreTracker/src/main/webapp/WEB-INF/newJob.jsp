<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Only in Form page to use data binding and to deal with Model error validation -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add A Job</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<!-- Add Book -->
	<h1>Add a Job</h1>
	<div class="d-flex justify-content-end" style="margin-right: 50px">
		<p>
			<a href="/logout" style="margin-right: 10px">logout</a> <a
				href="/dashboard">Back</a>
		</p>
		</div>

	<form:form action="/addJob" method="post" modelAttribute="job">
		<div class="form-group row">
			<form:label path="title">Title:</form:label>
			<br />
			<form:errors path="title" class="text-danger" />
			<form:input path="title" style="width:250px;" />
		</div>
		<div class="form-group row">
			<form:label path="description">Description:</form:label>
			<br />
			<form:errors path="description" class="text-danger" />
			<form:textarea path="description" style="width:250px;" rows="3"></form:textarea>
		</div>
		<div class="form-group row">
			<form:label path="location">Location:</form:label>
			<br />
			<form:errors path="location" class="text-danger" />
			<form:input path="location" style="width:250px;" />
		</div>
		<!-- This is a hidden row to submit user id when creating a new Book -->
		<div class="form-group row">
			<form:errors path="user" class="error" />
			<form:input type="hidden" path="user" value="${user.id}"
				class="form-control" />
		</div>
		<input type="submit" value="Submit" class="btn btn-primary" />
	</form:form>
</body>
</html>