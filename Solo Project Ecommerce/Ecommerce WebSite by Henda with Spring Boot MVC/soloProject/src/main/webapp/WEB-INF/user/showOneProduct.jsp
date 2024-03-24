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
<title>Products And Categories</title>
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
<body style="background-color: #faf9f6;">
	<div class="container1">

		<h1 class="title">Welcome, ${user.firstName} ${user.lastName}</h1>
		<!-- Logout -->
		<div class="logout-style">
			<form id="logoutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input type="submit"
					class="btn btn-danger" value="Logout!" />
			</form>
		</div>
		<br />
		<p>
			Product Name :
			<c:out value="${product.name}" />
		</p>
		<p>
			Description :
			<c:out value="${product.description}" />
		</p>
		<p>
			Price :
			<c:out value="${product.price}" />
			$
		</p>
		<br />
		<div style="display: flex; justify-content: space-around">
			<button class="btn btn-primary">Add to my purchase list</button>
			<a href="">Add Comment</a>
		</div>
	</div>
</html>