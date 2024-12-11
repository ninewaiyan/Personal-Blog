<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>NovaTech Book</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">
<style>
body, html {
	margin: 0;
	padding: 0;
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	color: #333;
}

.main-content {
	padding: 20px;
}

.video-card {
	position: relative;
	transition: transform 0.3s, box-shadow 0.3s;
	cursor: pointer;
}

.video-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
}

.card-img-top {
	height: 200px;
	object-fit: cover;
}

.card-body h5 {
	font-size: 1.1rem;
}

.btn-action {
	background-color: #007bff;
	color: white;
}

.btn-delete {
	background-color: #dc3545;
	color: white;


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
							</c:if>
					${sessionScope.user.username } 
					<c:if test="${sessionScope.user.role eq 'admin' }">
								(Admin)
							</c:if></a>
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
							aria-current="page" href="book?mode=CREATEPAGE">Create New </a></li>

					</c:if>
					

			</ul>



				<form action="book" class="d-flex" method="post">
					<input type="hidden" name="mode" value="SEARCH"> <input
						class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search" name="query">

					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>

			</div>
		</div>
	</nav>


	<!-- Main Content -->
	<div class="container main-content">
		<div class="row">
			<c:forEach var="book" items="${books}">
				<!-- Video Card 1 -->
				<div class="col-md-3 col-sm-6 mb-4">
					<div class="card video-card">
						<img
							src="${pageContext.request.contextPath}/template/book/BookMedia/${book.image}"
							class="card-img-top">
						<div class="card-body">
							<h4 class="card-title text-dark">${book.name }</h4>

							<a href="book?mode=DETAIL&id=${book.id }"
								class="btn btn-primary">See More</a>
								
			
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

	</div>













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
