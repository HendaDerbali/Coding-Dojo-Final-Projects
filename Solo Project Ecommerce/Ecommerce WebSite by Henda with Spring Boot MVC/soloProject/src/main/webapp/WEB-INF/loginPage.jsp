<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login - Registration Page</title>
<!--CSS & js Folder-->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/app.js"></script>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css" />
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #faf9f6;">

	<div class="container1">

		<div class="container mt-5">

			<%-- Add this section to display backend validation errors --%>
			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger">${errorMessage}</div>
			</c:if>

			<!-- Registration and Login Forms -->
			<div class="row">
				<div class="col-md-6">
					<h1>Register</h1>
					<form:form action="/register" method="post" modelAttribute="user"
						class="form-horizontal">
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
						<br />
						<button type="submit" class="btn btn-primary">Submit</button>
					</form:form>
				</div>

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
						<br />
						<button type="submit" class="btn btn-success">Submit</button>
					</form:form>
				</div>
			</div>
		</div>
		<div class="logout-style">
			<a href="/">back to dashboard</a>
		</div>
	</div>

</body>
</html>
