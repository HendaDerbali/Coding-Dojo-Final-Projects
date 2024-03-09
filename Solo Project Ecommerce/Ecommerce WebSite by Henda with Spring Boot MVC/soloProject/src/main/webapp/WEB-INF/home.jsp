<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<!-- Custom CSS -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>

	<div class="container">
		<h1>Welcome, ${user.firstName}</h1>
		<form id="logoutForm" method="POST" action="/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> <input type="submit"
				class="btn btn-danger" value="Logout!" />
		</form>

		<div class="row">
			<div class="col-md-6">
				<p>First Name: ${user.firstName}</p>
				<p>Last Name: ${user.lastName}</p>
				<p>Email: ${user.email}</p>
			</div>
			<div class="col-md-6">
				<p>
					Signup Date:
					<fmt:formatDate pattern="MMMM dd, y" value="${user.createdAt}" />
				</p>
				<p>
					Last Sign in:
					<fmt:formatDate pattern="MMMM dd, y" value="${user.lastLogin}" />
				</p>
			</div>
		</div>
	</div>

</body>
</html>
