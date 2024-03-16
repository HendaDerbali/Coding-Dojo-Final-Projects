<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- Only in Form page to use data binding and to deal with Model error validation -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Job</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="d-flex justify-content-end" style="margin-right: 50px">
		<p>
			<a href="/dashboard" style="margin-right: 10px">Back</a> <a href="/logout">logout</a> 
		</p>
	</div>
	<!-- The Logged User is -->
	<h1>

		<c:out value="${job.title}" />
	</h1>

	<p>
		<c:out value="${job.description}" />

	</p>
	<p>
		Location:
		<c:out value="${job.location}" />
	</p>
	<p>
		Posted by:
		<c:out value="${job.user.firstName}" />
		<c:out value="${job.user.lastName}" />
	</p>
	<p>
		Posted on:
		<!-- Formatting Date -->
		<fmt:formatDate value="${job.user.createdAt}" pattern="MMMM, dd yyyy" />
	</p>

	<h4>
		<a href="/add/${job.id}">Add To My Jobs</a>
	</h4>

</body>
</html>