
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!doctype html>
<html lang="en">
<head>

<c:import url="common/header.jsp"></c:import>

</head>

<body>

	<c:import url="common/nav.jsp"></c:import>
	<c:import url="common/slider.jsp"></c:import>

	<!-- Main Content Area -->
	<div class="main-content" id="mainContent">
		<div class="container ">

			<div class="row  justify-content-center">
			
			 <c:if test="${ empty posts  }">
			 <div class="col-md-10">
			 <h1>No post was found</h1>
			 </div>
					
				</c:if>

				<c:forEach var="postView" items="${posts}">

					<!-- Posts Section -->
					<div class="col-md-10">
						<!-- Post with Carousel for Multiple Images -->
						<div class="card post-card mb-4">
							<div class="card-body" id=${postView.id }>
								<h5 class="post-title text-primary">${postView.title }</h5>
								<small class="post-time">${postView.timeElapsed}</small>
								<p class="post-content">${postView.content}</p>


								<c:if test="${sessionScope.user.role eq 'admin' }">

									<!-- Dropdown Menu Button -->
									<div class="post-actions dropdown">
										<button class="btn btn-light dropdown-toggle" type="button"
											id="dropdownMenuButton" data-bs-toggle="dropdown"
											aria-expanded="false">&#x2026;</button>
										<ul class="dropdown-menu dropdown-menu-end"
											aria-labelledby="dropdownMenuButton">
											<li><a class="dropdown-item"
												href="upload?mode=EDITPOST&postId=${postView.id}">Edit</a></li>
											<li><a class="dropdown-item" data-bs-toggle="modal"
												data-bs-target="#deleteModal${postView.id}">Delete </a></li>
										</ul>
									</div>

								</c:if>



								<c:url var="deleteLink" value="post">
									<c:param name="mode" value="DELETE" />
									<c:param name="post_id" value="${postView.id}" />

								</c:url>

								<!-- Delete Confirmation Modal -->
								<div class="modal fade" id="deleteModal${postView.id}"
									tabindex="-1" aria-labelledby="deleteModalLabel${postView.id}"
									aria-hidden="true">
									<div class="modal-dialog">

										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="deleteModalLabel${postView.id}">Confirm
													Delete</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">Are you sure you want to delete
												this post?</div>
											<div class="modal-footer">


												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Cancel</button>
												<!--    <a href="${deleteLink }" >
                        <button type="button" class="btn btn-danger" onclick="deletePost()">Delete post_Id: ${post.id }</button>
                        </a>
                        -->
												<a href="${deleteLink}" class="btn btn-danger">Delete </a>
											</div>
										</div>
									</div>
								</div>

							</div>


							<!-- Carousel for Images/Videos -->
							<div id="carouselExampleControls${postView.id}"
								class="carousel slide" data-bs-ride="carousel">
								<div class="carousel-inner">
									<c:forEach var="media" items="${postView.mediaList}"
										varStatus="status">
										<div
											class="carousel-item ${status.first ? 'active' : ''} mediaDiv"
											data-bs-interval="2000">
											<c:choose>
												<c:when
													test="${media.path.endsWith('.mp4') || media.path.endsWith('.mpeg')}">
													<!-- Video Element -->
													<video class=" media" controls>
														<source
															src="${pageContext.request.contextPath}/template/postMedia/${media.path}"
															type="video/${media.path.endsWith('.mp4') ? 'mp4' : 'mpeg'}">
														Your browser does not support the video tag.
													</video>
												</c:when>
												<c:otherwise>
													<!-- Image Element -->
													<img
														src="${pageContext.request.contextPath}/template/postMedia/${media.path}"
														class=" media" alt="Media">
												</c:otherwise>
											</c:choose>
										</div>
									</c:forEach>
								</div>
								<button
									class="carousel-control-prev${postView.id} carousel-control-prev"
									type="button"
									data-bs-target="#carouselExampleControls${postView.id}"
									data-bs-slide="prev">
									<span class="carousel-control-prev-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Previous</span>
								</button>
								<button
									class="carousel-control-next${postView.id} carousel-control-next "
									type="button"
									data-bs-target="#carouselExampleControls${postView.id} "
									data-bs-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="visually-hidden">Next</span>
								</button>
							</div>



							<!-- Comments Section -->
							<div class="card-footer post-card-footer">
								<h6 class="post-title">
									${fn:length(postView.commentList)}
									Comments <a class="toggle-btn" onclick="toggleComments(this)">&#9660;</a>
								</h6>
								<div class="comment-section">

									<c:forEach var="comment" items="${postView.commentList}">
										<div class="comment">
											<h6>${comment.userName }<c:if
													test="${sessionScope.user.role eq 'admin' }">
													<c:url var="deletecomment" value="post">
														<c:param name="mode" value="DELETECOMMENT" />
														<c:param name="comment_id" value="${comment.id}" />

													</c:url>
													<a type="button"
														href="${deletecomment }"
														class="btn  btn-sm text-danger ">Delete</a>
												</c:if>
											</h6>
											<p>${comment.comment}</p>

											<span class="reply-btn" onclick="toggleReplyInput(this)">Reply</span>
											<div class="reply-section">
												<div class="reply-input-group input-group mt-2"
													id="${postView.id}">
													<input type="hidden" name="mode" value="CREATECOMMENT">
													<c:choose>
														<c:when test="${empty sessionScope.user}">
															<input type="hidden" name="username" value="Anonymous">
														</c:when>
														<c:otherwise>
															<input type="hidden" name="username"
																value="${sessionScope.user.firstname} ${sessionScope.user.lastname}">
														</c:otherwise>
													</c:choose>
													<input type="text" class="form-control reply-input "
														value="@${comment.userName }@  ">
													<button class="btn btn-primary reply-btn btn-reply"
														data-post-id="${postView.id}" type="button">Reply</button>
												</div>
											</div>
										</div>
									</c:forEach>

									<!-- Add more comments here -->
								</div>
								<div class="input-group mt-3 post" id="${postView.id}">
									<input type="hidden" name="mode" value="CREATECOMMENT">
									<c:choose>
										<c:when test="${empty sessionScope.user}">
											<input type="hidden" name="username" value="Anonymous">
										</c:when>
										<c:otherwise>
											<input type="hidden" name="username"
												value="${sessionScope.user.firstname} ${sessionScope.user.lastname}">
										</c:otherwise>
									</c:choose>


									<input type="text" class="form-control comment-input"
										placeholder="Add a comment..."> <a
										class="btn btn-primary comment-btn"
										data-post-id="${postView.id}" type="button">Comment</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>


		</div>

	</div>








	<c:import url="common/footer.jsp"></c:import>


</body>
</html>
