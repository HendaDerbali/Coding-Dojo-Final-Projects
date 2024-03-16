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
<title>New Category</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

</head>
<body>
	<h1>Welcome, ${user.firstName} ${user.lastName}</h1>

	<div class="container">
		<div class="row">
			<!-- Add Category -->
			<div class="col-md-6">
				<h2>New Category</h2>
				<form:form action="/addCategory" method="POST"
					modelAttribute="category">
					<div class="form-group">
						<form:label path="name">Name:</form:label>
						<form:errors path="name" class="text-danger" />
						<form:input path="name" class="form-control" />
					</div>
					<!-- This is a hidden row to submit user id when creating a new Book -->
					<div class="form-group row">
						<form:errors path="user" class="error" />
						<form:input type="hidden" path="user" value="${user.id}"
							class="form-control" />
					</div>
					<input type="submit" value="Submit" class="btn btn-success" />
				</form:form>
			</div>


			<!-- Add Product -->
			<div class="col-md-6">
				<h2>New Product</h2>
				<form:form action="/addProduct" method="post"
					modelAttribute="product">
					<div class="form-group">
						<form:label path="name">Name:</form:label>
						<form:errors path="name" class="text-danger" />
						<form:input path="name" class="form-control" />
					</div>
					<div class="form-group">
						<form:label path="description">Description:</form:label>
						<form:errors path="description" class="text-danger" />
						<form:input path="description" class="form-control" />
					</div>
					<div class="form-group">
						<form:label path="price">Price:</form:label>
						<form:errors path="price" class="text-danger" />
						<form:input path="price" class="form-control" />
					</div>
					<!-- This is a hidden row to submit user id when creating a new Book -->
					<div class="form-group row">
						<form:errors path="user" class="error" />
						<form:input type="hidden" path="user" value="${user.id}"
							class="form-control" />
					</div>
					<input type="submit" value="Submit" class="btn btn-success" />
				</form:form>
			</div>
		</div>
	</div>


	<br />
	<br />
	<!-- Form Association -->
	<h2>Associate Category and Product</h2>
	<form action="/associateCategoriesProducts" method="post">
		<!-- Dropdown List for Categories -->
		<div class="form-group">
			<label for="categoryId">Select Category:</label> <select
				class="form-control" id="categoryId" name="categoryId">
				<option value="">Select Category</option>
				<c:forEach items="${categories}" var="category">
					<option value="${category.id}">${category.name}</option>
				</c:forEach>
			</select>
		</div>

		<!-- Dropdown List for Products created by user Logged in -->
		<div class="form-group">
			<label for="productId">Select Product:</label> <select
				class="form-control" id="productId" name="productId">
				<option value="">Select Product</option>
				<c:forEach items="${products}" var="product">
					<!-- Check if the product belongs to the logged-in user -->
					<c:if test="${product.user.id eq user.id}">
						<option value="${product.id}">${product.name}</option>
					</c:if>
				</c:forEach>
			</select>
		</div>


		<!-- Hidden Field for CSRF Token -->
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />

		<!-- Submit Button -->
		<button type="submit" class="btn btn-primary">Associate</button>
	</form>




</body>
</html>