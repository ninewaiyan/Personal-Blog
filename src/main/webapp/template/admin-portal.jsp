<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css"
	rel="stylesheet">

<style>
/* Custom Styles for Admin Dashboard */
body {
	background-color: #f8f9fa;
	font-family: Arial, sans-serif;
}

.sidebar {
	background-color: #343a40;
	color: #fff;
	height: 100vh;
	padding-top: 20px;
}

.sidebar .nav-link {
	color: #adb5bd;
	font-size: 16px;
}

.sidebar .nav-link.active {
	color: #fff;
	background-color: #495057;
}

.sidebar .nav-link i {
	margin-right: 8px;
}

main {
	padding: 20px;
}

.table th, .table td {
	vertical-align: middle;
}

.btn-sm {
	padding: 0.25rem 0.5rem;
	font-size: 0.875rem;
}

.btn-warning {
	background-color: #ffc107;
	border-color: #ffc107;
}

.btn-warning:hover {
	background-color: #e0a800;
	border-color: #d39e00;
}

.btn-success {
	background-color: #28a745;
	border-color: #28a745;
}

.btn-success:hover {
	background-color: #218838;
	border-color: #1e7e34;
}
</style>
</head>
<body>

<c:if
	test="${empty sessionScope.user || sessionScope.user.role ne 'admin'}">
			<c:redirect url="login" />
		</c:if>

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
							</c:if> </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="login?mode=LOGOUT">Log
									Out</a></li>
							<c:if test="${sessionScope.user.role eq 'admin' }">
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="admin">Admin Portal</a></li>
							</c:if>

						</ul></li>





				</ul>


				<form action="admin" class="d-flex" method="get">
					<input type="hidden" name="mode" value="SEARCH"> <input
						class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search" name="query">

					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>

			</div>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="row">

			<main role="main" class="col-md-9 ml-sm-auto col-lg-12 px-4">
				<h2>User Management</h2>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>User Name</th>
							<th>Email</th>
							<th>Enable</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${users }">

							<tr>

								<td>${user.userId }</td>
								<td>${user.firstname }</td>
								<td>${user.lastname }</td>
								<td>${user.username }</td>
								<td>${user.email }</td>
								<td>${user.enable ? 'Yes' : 'No' }</td>
								<td>action</td>
								<td><c:url var="enableLink" value="admin">

										<c:param name="mode" value="ENABLE" />
										<c:param name="userId" value="${user.userId }" />

									</c:url> <c:url var="disableLink" value="admin">

										<c:param name="mode" value="DISABLE" />
										<c:param name="userId" value="${user.userId}" />

									</c:url> <c:if test="${user.enable }">

										<a href="${disableLink }" class="btn btn-success">Enable</a>

									</c:if> <c:if test="${not user.enable }">

										<a href="${enableLink }" class="btn btn-danger">Disable</a>

									</c:if></td>

							</tr>

						</c:forEach>


					</tbody>
					<tfoot>
						<tr>
							<th>ID</th>
							<th>FirstName</th>
							<th>LastName</th>
							<th>User Name</th>
							<th>Email</th>
							<th>Enable</th>
							<th>Action</th>
						</tr>
					</tfoot>
				</table>
				</table>
			</main>
		</div>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<c:import url="common/footer.jsp"></c:import>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
