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
<title>Read Share</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<!-- Register Form -->
				<h2>Register</h2>
				<form:form action="/register" method="post" modelAttribute="newUser">
					<div class="form-group">
						<label for="name">Name:</label>
						<form:errors path="name" class="text-danger" />
						<form:input class="form-control" path="name" />
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<form:errors path="email" class="text-danger" />
						<form:input class="form-control" path="email" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<form:errors path="password" class="text-danger" />
						<form:input class="form-control" path="password" type="password" />
					</div>
					<div class="form-group">
						<label for="confirm">Confirm Password:</label>
						<form:errors path="confirm" class="text-danger" />
						<form:input class="form-control" path="confirm" type="password" />
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>

			<!-- Login Form -->
			<div class="col-md-6">
				<h2>Login</h2>
				<form:form action="/login" method="post" modelAttribute="newLogin">
					<div class="form-group">
						<label for="email">Email:</label>
						<form:input class="form-control" path="email" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<label for="password">Password:</label>
						<form:input class="form-control" path="password" type="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS (Optional) -->
	<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
</body>
</html>
</html>