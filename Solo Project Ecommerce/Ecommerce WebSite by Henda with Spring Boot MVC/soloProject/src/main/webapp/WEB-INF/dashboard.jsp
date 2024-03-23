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
<title>Dashboard E-commerce Website</title>
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
<body style="background-color: #faf9f6;">
	<div class="container1">
		<h1 class="title">Online Shopping</h1>
		<nav class="navbar">
			<div>
				<c:forEach var="category" items="${categories}">
					<button class="btns">
						<c:out value="${category.name}"></c:out>
					</button>
				</c:forEach>
			</div>
			<div>
				<a href="/login"><button style="background-color: #03032152">Register
						& Login</button></a>
			</div>
		</nav>
		<br />
		<div class="images">
			<img src="/images/beauty.jpg" alt="Beauty Image" class="logo">
			<img src="/images/electronic.png" alt="Beauty Image" class="logo">
			<img src="/images/3.png" alt="Beauty Image" class="logo">

		</div>
		<div class="social-media-links">
			<a href="">contact us</a> <a href="">FB</a> <a href="">Instagram</a>
			<a href="">other informations to add later</a>
		</div>
	</div>
</body>
</html>