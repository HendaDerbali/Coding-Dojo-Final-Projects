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
<title>Login - Registration Page</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container mt-5">
		<!-- Optional Logout and Error Messages Display -->
		<c:if test="${logoutMessage != null}">
			<div class="alert alert-success" role="alert">
				<c:out value="${logoutMessage}"></c:out>
			</div>
		</c:if>
		<c:if test="${errorMessage != null}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${errorMessage}"></c:out>
			</div>
		</c:if>

		<!-- Registration Form -->
		<div class="row">
			<div class="col-md-6">
				<h1>Register</h1>
				<form:form action="/register" method="post" modelAttribute="user"
					class="form-horizontal">
					<form:errors path="*" class="alert alert-danger" />
					<div class="form-group">
						<label>First Name:</label>
						<form:input path="firstName" class="form-control" />
						<form:errors path="firstName" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Last Name:</label>
						<form:input path="lastName" class="form-control" />
						<form:errors path="lastName" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Email:</label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Password:</label>
						<form:input path="password" class="form-control" type="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Confirm Password:</label>
						<form:input path="confirm" class="form-control" type="password" />
						<form:errors path="confirm" class="text-danger" />
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>

			<!-- Login Form -->
			<div class="col-md-6">
				<h1>Login</h1>
				<form:form action="/login" method="post" modelAttribute="user"
					class="form-horizontal">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div class="form-group">
						<label>Email:</label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<label>Password:</label>
						<form:input path="password" class="form-control" type="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
