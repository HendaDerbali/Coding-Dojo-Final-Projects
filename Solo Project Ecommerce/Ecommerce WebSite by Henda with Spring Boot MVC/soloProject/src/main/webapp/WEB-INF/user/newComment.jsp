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
<title>Comment</title>
<!--CSS & js Folder-->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<script type="text/javascript" src="/js/app.js"></script>

<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	test
	<div class="container1">
		<h1 class="title">Welcome, ${user.firstName} ${user.lastName}</h1>
		<br /> <br />
		<!-- Logout -->
		<!-- Logout -->
		<div class="logout-style">
			<form id="logoutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit"
					class="btn btn-danger" value="Logout!" />
			</form>
		</div>
		<!-- Add Comment  -->
		<form:form action="/addComment" method="POST" modelAttribute="comment">
			<div class="form-group">
				<form:label path="text">Comment:</form:label>
				<form:errors path="text" class="text-danger" />
				<form:input path="text" class="form-control" />
			</div>
			<!-- This is a hidden row to submit user id when creating a new Category -->
			<div class="form-group row">
				<form:errors path="user" class="error" />
				<form:input type="hidden" path="user" value="${user.id}"
					class="form-control" />
				<!-- This is a hidden row to submit user id when creating a new Category -->
			</div>
			<!-- This is a hidden row to submit user id when creating a new Category -->
			<div class="form-group row">
				<form:errors path="product" class="error" />
				<form:input type="hidden" path="product" value="${product.id}"
					class="form-control" />
			</div>
			<br />
			<input type="submit" value="Add" class="btn btn-success" />
		</form:form>
	</div>
</html>