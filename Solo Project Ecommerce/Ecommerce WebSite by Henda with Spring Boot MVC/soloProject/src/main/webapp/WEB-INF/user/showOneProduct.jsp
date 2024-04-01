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
<title>Products Details</title>
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
		<div style="display: flex; justify-content: space-between;">
			<div>
				<p>
					Product Name:
					<c:out value="${product.name}" />
				</p>
				<p>
					Description:
					<c:out value="${product.description}" />
				</p>
				<p>
					Price:
					<c:out value="${product.price}" />
					$
				</p>
			</div>
			<!-- Comment List -->
			<div>
				<h4>List of all comments</h4>
				<ul>
					<c:forEach var="comment" items="${comments}">
						<li>Comment of ${comment.user.firstName} ${comment.user.lastName}: ${comment.text}</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<br />
		<div style="display: flex; justify-content: space-around">
			<a href="/product/${product.id}/purchase">
				<button class="btn btn-primary">Add to my purchase list</button>
			</a> <a href="/product/${product.id}/comment">Add Comment</a> <a
				href="/home">back to home page</a>
		</div>
	</div>
</html>