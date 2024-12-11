<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Detail</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">
<style>
.product-image {
	position: relative;
	width: 100%;
	height: 300px;
	overflow: hidden;
}

.product-image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.product-info {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	padding: 10px;
	background: rgba(0, 0, 0, 0.6);
	color: #fff;
	text-align: center;
}

.product-description {
	padding: 20px;
}

.btn-group {
	margin-top: 10px;
}

.dropdown-menu-end {
	right: 0;
	left: auto;
}

.dropdown-menu {
	border-radius: 10px;
	padding: 0;
}

.dropdown-item {
	padding: 10px 15px;
	border-radius: 10px;
}

.dropdown-item:hover {
	background-color: #f1f1f1;
}

.dropdown-toggle::after {
	display: none;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg  navbar-dark bg-dark sticky-top">
		<div class="container-fluid">
			<a class="navbar-brand" href="post">NovaTech</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="post">Home</a></li>


					<c:if test="${ empty sessionScope.user  }">
						<li><a class="dropdown-item" href="login">Login</a></li>
					</c:if>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<c:if test="${ not empty sessionScope.user }">
								<i class="fa-solid fa-user"></i>
							</c:if> ${sessionScope.user.username } <c:if
								test="${sessionScope.user.role eq 'admin' }">
								(Admin)
							</c:if>
					</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="login?mode=LOGOUT">Log
									Out</a></li>
							<c:if test="${sessionScope.user.role eq 'admin' }">
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="admin">Admin Portal</a></li>
							</c:if>

						</ul></li>

					<c:if test="${sessionScope.user.role eq 'admin' }">

						<li class="nav-item"><a class="nav-link active btn"
							aria-current="page" href="shop?mode=CREATEPAGE">Create New </a></li>

					</c:if>



				</ul>




			</div>
		</div>
	</nav>


	<div class="container mt-5">
		<div class="row">
			<!-- Image and Name/Price -->
			<div class="col-md-6">
				<div class="product-image">
					<img
						src="${pageContext.request.contextPath}/template/shop/shopMedia/${shopItem.image}"
						alt="Product Image">
					<div class="product-info">
						<h4 class="mb-1">${shopItem.name }</h4>
						<p>${shopItem.price }</p>
					</div>
				</div>
			</div>
			<!-- Description and Actions -->
			<div class="col-md-6">
				<div class="product-description">
					<h5>Description</h5>
					<p>${shopItem.description }</p>

					<c:if test="${sessionScope.user.role eq 'admin' }">
					<div class="d-flex justify-content-end">
					<div class="">
							<a href="shop?mode=EDIT&id=${shopItem.id }"
								class="btn btn-outline-success">Edit</a> <a
								data-bs-toggle="modal"
								data-bs-target="#deleteModal${shopItem.id}"
								class="btn btn-outline-danger">Delete</a>
						</div>
					</div>
						

						<div class="modal fade" id="deleteModal${shopItem.id}" tabindex="-1"
							aria-labelledby="deleteModalLabel${shopItem.id}" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="deleteModalLabel${shopItem.id}">Confirm
											Delete</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Are you sure you want to delete
										this Item?</div>

									<c:url var="deleteLink" value="shop">
										<c:param name="mode" value="DELETE" />
										<c:param name="id" value="${shopItem.id}" />

									</c:url>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Cancel</button>
										<a href="${deleteLink }" class="btn btn-danger">Delete</a>
									</div>
								</div>
							</div>
						</div>

					</c:if>
				</div>
			</div>
		</div>
	</div>



	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!--  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
