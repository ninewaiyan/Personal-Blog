<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="jakarta.tags.core" prefix="c"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Create Post</title>
<!-- Bootstrap CSS for responsive design -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f0f2f5;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.container {
	max-width: 720px;
	margin-top: 30px;
}

.card {
	padding: 2rem;
	border-radius: 12px;
	box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	background-color: #fff;
}

.form-label {
	font-weight: 600;
}

.cancel {
	border-radius: 30px;
	font-size: 1rem;
	transition: background-color 0.3s ease;
}

.btn-primary {
	background-color: #007bff;
	border: none;
	padding: 0.7rem 1.5rem;
	border-radius: 30px;
	font-size: 1rem;
	transition: background-color 0.3s ease;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.form-control, .form-control-file {
	border-radius: 10px;
	padding: 12px;
}

.form-text {
	color: #6c757d;
}
</style>
</head>
<body>

<c:if
	test="${empty sessionScope.user || sessionScope.user.role ne 'admin'}">
			<c:redirect url="login" />
		</c:if>

	<div class="container">
		<div class="card">
			<h2 class="mb-4">Edit Post</h2>
			<c:if test="${not empty message}">
				<span class="text-danger">${message}</span>
			</c:if>
			<form action="upload" method="POST">
				<input type="hidden" name="mode" value="UPDATEPOST"> <input
					type="hidden" name="postId" value="${post.id }">

				<!-- Title Input -->
				<div class="mb-3">
					<label for="postTitle" class="form-label">Post Title</label> <input
						type="text" class="form-control" id="postTitle" name="title"
						placeholder="Enter your post title" value="${post.title }" required>
				</div>

				<!-- Content Input -->
				<div class="mb-3">
					<label for="postContent" class="form-label">Post Content</label>
					<textarea class="form-control" id="postContent" name="content"
						rows="5" placeholder="Write your post content here..." required>${post.content}</textarea>

				</div>

				<!-- Submit and Cancel Buttons -->
				<div class="d-flex justify-content-end">
					<a type="button" class="btn btn-secondary me-2 cancel" href="post">Cancel</a>
					<button type="submit" class="btn btn-primary me-2">Submit</button>

				</div>
			</form>
		</div>
	</div>


	<!-- Bootstrap JS (Optional for enhanced functionality) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
