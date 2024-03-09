<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Welcome</title>
</head>
<body>
		<h1>Welcome, ${user.firstName}  ${user.lastName}</h1>
<div>
	<a href="categories-products/new">Add New Category & Product</a> 
</div>
</body>
</html>
