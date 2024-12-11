
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!--  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script> -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<!--<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<!-- JavaScript to Handle Toggle Functionality -->
<!-- JavaScript to Handle Toggle Functionality -->
<script>
    document.getElementById('toggleButton').addEventListener('click', function() {
        const sidebar = document.getElementById('sidebar');
        const mainContent = document.getElementById('mainContent');
        const toggleButton = document.getElementById('toggleButton');
        sidebar.classList.toggle('collapsed');
        mainContent.classList.toggle('collapsed');
        toggleButton.classList.toggle('collapsed');
        toggleButton.innerHTML = sidebar.classList.contains('collapsed') ? '<i class="fas fa-chevron-right"></i>' : '<i class="fas fa-chevron-left"></i>';
    });

    function toggleComments(element) {
        const commentSection = element.closest('.post-card-footer').querySelector('.comment-section');
        commentSection.style.display = commentSection.style.display === 'none' ? 'block' : 'none';
        element.textContent = commentSection.style.display === 'none' ? 'Show Comments' : 'Hide Comments';
      }

      function toggleReplyInput(element) {
        const replySection = element.closest('.comment').querySelector('.reply-section');
        replySection.querySelector('.reply-input-group').style.display = replySection.querySelector('.reply-input-group').style.display === 'none' ? 'flex' : 'none';
      }

      function deletePost() {
        // Perform the delete action here, e.g., make a server request to delete the post
        console.log("Post deleted"); // Replace this with actual delete functionality
    
        // Close the modal after deletion
        var deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
        deleteModal.hide();
      }
      
      document.addEventListener('DOMContentLoaded', function () {
    	    const deleteModal = document.getElementById('deleteModal');

    	    deleteModal.addEventListener('show.bs.modal', function (event) {
    	        const button = event.relatedTarget; // Button that triggered the modal
    	        const postId = button.getAttribute('data-post-id'); // Extract post ID from data attribute
    	        
    	        const modalPostId = deleteModal.querySelector('#modalPostId');
    	        const deleteLink = deleteModal.querySelector('#deleteLink');

    	        // Update modal content with the correct post ID
    	        modalPostId.textContent = postId;

    	        // Update the delete link with the correct post ID
    	        const deleteUrl = `post?mode=DELETE&post_id=${postId}`;
    	        deleteLink.setAttribute('href', deleteUrl);
    	    });
    	});

      
      $('.comment-btn').click(
				function() {
					var postId = $(this).data('post-id'); // Get the post ID
					var commentInput = $(this).closest('.input-group').find(
							'.comment-input'); // Get the input field
					var comment = commentInput.val(); // Get the comment text

					var mode = $(this).siblings('input[name="mode"]').val(); // Get the mode value
					var username = $(this).siblings('input[name="username"]').val();

					console.log("Post ID: " + postId);
					console.log("Comment: " + comment);
					console.log("Mode: " + mode);
					console.log("username:" + username)
					console.log($);

					if (comment.trim() === "") {
						alert("Comment cannot be empty!");
						return;
					}

					// AJAX request to send comment
					$.ajax({
						url : "post", // Server-side URL to handle comment submission
						method : 'GET',
						data : {
							postId : postId,
							comment : comment,
							username : username,
							mode : mode
						},
						success : function(response) {
							//console.log("Response from server:", response);
							alert("Comment added successfully!");
							// Optionally, update the UI with the new comment
							   window.location.href = "post";
						},
						error : function() {
							alert("Failed to add comment. Please try again.");
						}
					});
				});
      
      
      $('.btn-reply').click(
				function() {
					var postId = $(this).data('post-id'); // Get the post ID
					var commentInput = $(this).closest('.input-group').find(
							'.reply-input'); // Get the input field
					var comment = commentInput.val(); // Get the comment text

					var mode = $(this).siblings('input[name="mode"]').val(); // Get the mode value
					var username = $(this).siblings('input[name="username"]').val();

					console.log("Post ID: " + postId);
					console.log("Comment: " + comment);
					console.log("Mode: " + mode);
					console.log("username:" + username)
					console.log($);

					if (comment.trim() === "") {
						alert("Comment cannot be empty!");
						return;
					}

					// AJAX request to send comment
					$.ajax({
						url : "post", // Server-side URL to handle comment submission
						method : 'GET',
						data : {
							postId : postId,
							comment : comment,
							username : username,
							mode : mode
						},
						success : function(response) {
							//console.log("Response from server:", response);
							alert("Comment added successfully!");
							// Optionally, update the UI with the new comment
							   window.location.href = "post";
						},
						error : function() {
							alert("Failed to add comment. Please try again.");
						}
					});
				});
    
      
      
     
      document.addEventListener('DOMContentLoaded', function () {
          var carousel = new bootstrap.Carousel(document.getElementById('carouselExampleControls'), {
              interval: 2000, // Adjust as needed
              wrap: true
          });
      });
     

      

      
    
</script>
