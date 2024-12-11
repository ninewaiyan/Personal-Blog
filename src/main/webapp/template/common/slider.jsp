<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!-- Toggle Button -->
<button class="toggle-btn slider-btn" id="toggleButton">
	<i class="fas fa-chevron-left"></i>
</button>

<!-- Sidebar -->
<div class="sidebar" id="sidebar">
	<h4 class="text-center mt-3">Menu</h4>
	<a href="post"><i class="fas fa-home me-2"></i> Home</a>


	<c:if test="${sessionScope.user.role eq 'admin'}">
		<a href="upload?mode=POSTCREATEPAGE"><i
			class="fas fa-pencil-alt me-2"></i> Posts</a>
	</c:if>
	<a href="course"><i class="fas fa-graduation-cap me-2"></i> Courses</a>
	<a href="shop"><i class="fas fa-shopping-cart me-2"></i> Shop</a> <a
		href="book"><i class="fas fa-book me-2"></i> Book</a>
		 


</div>
