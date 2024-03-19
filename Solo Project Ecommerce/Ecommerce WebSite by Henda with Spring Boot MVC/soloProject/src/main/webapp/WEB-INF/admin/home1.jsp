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
<body style="background-color: #faf9f6;">
	<div class="container1">

		<h1 class="title">Welcome, ${user.firstName} ${user.lastName}</h1>

		<!-- Logout -->
		<div class="logout-style">
			<!-- Logout -->
			<div class="logout-style">
				<form id="logoutForm" method="POST" action="/logout">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="submit"
						class="btn btn-danger" value="Logout!" />
				</form>
			</div>
		</div>
		<br />
		<div>
			<a href="categories-products/new">Add New Category & Product</a>
		</div>
		<br />

		<div>
			<a href="/">back to admin dashboard</a>
		</div>
		<br /> <br />
		<!-- Table To Display All Jobs -->
		<div class="table-container">
			<table class="table table-bordered">
				<thead>
					<tr class="thead-color">
						<th>All Categories</th>
						<th>All Products Associated to Categories</th>
					</tr>
				</thead>
				<tbody>
					<!-- Display All Categories and Products associated to them -->
					<c:forEach var="category" items="${categories}">
						<tr>
							<c:choose>
								<c:when test="${category.user.id eq user.id}">
									<!-- Display the category as a link if it was created by the logged-in user -->
									<td><a href="categories/${category.id}">${category.name}</a></td>
								</c:when>
								<c:otherwise>
									<!-- Display the category as simple text if it was not created by the logged-in user -->
									<td>${category.name}</td>
								</c:otherwise>
							</c:choose>
							<td>
								<ul>
									<!-- Iterate over Products for this Category -->
									<c:forEach var="product" items="${category.products}">
										<li>${product.name}</li>
									</c:forEach>
								</ul>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th style="background-color: skyblue;">My Products (Added by
							: ${user.firstName} ${user.lastName} )</th>
					</tr>
				</thead>
				<tbody>
					<!-- Display All Products-->
					<c:forEach var="product" items="${user.products}">
						<tr>

							<!-- Display Product Name -->
							<td><a href="products/${product.id}">${product.name}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>

