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
		<nav class="navbar" style="width: 100%;">
			<div class="category-container"
				style="display: flex; justify-content: center; flex-direction: row; flex-wrap: wrap; margin: 0 auto;">
				<c:forEach var="category" items="${categories}">
					<div class="category"
						style="flex-shrink: 0; min-width: 200px; margin-right: 20px; text-align: center;">
						<button class="btn btns" style="width: 100%;">${category.name}</button>
						<div class="products" style="margin-top: 10px;">
							<c:forEach var="product" items="${category.products}">
								<div style="margin-top: 5px;"><a href="product/${product.id}">${product.name}</a></div>
							</c:forEach>
						</div>
					</div>
				</c:forEach>
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
