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
<a href="/home">login-register</a>

<c:forEach var="category" items="${categories}">

	<ul>
		<c:out value="${category.name}"></c:out>

	</ul>

</c:forEach>
</body>
</html>