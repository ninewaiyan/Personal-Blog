package com.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import com.blog.dao.CommentDAO;
import com.blog.dao.MediaDAO;
import com.blog.dao.PostDAO;
import com.blog.model.Comments;
import com.blog.model.Media;
import com.blog.model.Posts;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(location = "C:\\JavaEE_Workspace\\blog\\src\\main\\webapp\\template\\postMedia", fileSizeThreshold = 1024
		* 1024, // 1 MB

		maxFileSize = 1024 * 1024 * 10000, // 10000MB
		maxRequestSize = 1024 * 1024 * 10000 // 10000 MB

)
@WebServlet("/upload")
public class UploadController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Set<String> ALLOWED_MIME_TYPES = new HashSet<>();

	// Initialize allowed media types (image and video)
	static {
		ALLOWED_MIME_TYPES.add("image/jpeg");
		ALLOWED_MIME_TYPES.add("image/png");
		ALLOWED_MIME_TYPES.add("image/jpg");
		ALLOWED_MIME_TYPES.add("video/mp4");
		ALLOWED_MIME_TYPES.add("video/mpeg");
	}

	@Resource(name = "jdbc/blog_site")
	private DataSource dataSource;

	private PostDAO postDAO;
	private MediaDAO mediaDAO;
	private CommentDAO commentDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		postDAO = new PostDAO(dataSource);
		mediaDAO = new MediaDAO(dataSource);
		commentDAO = new CommentDAO(dataSource);

	}

	public UploadController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "POSTCREATEPAGE";
		}

		switch (mode) {

		case "POSTCREATEPAGE":
			showPostCreate(req, resp);
			break;

		case "POSTCREATE":
			postCreate(req, resp);
			break;
		case "EDITPOST":
			editPost(req, resp);
			break;

		case "UPDATEPOST":
			updatePost(req, resp);
			break;

		}

	}

	protected void updatePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		long postId = Long.parseLong(req.getParameter("postId"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");

		Posts post = new Posts(postId, title, content);
		boolean ok = postDAO.updatePost(post);

		System.out.println(postId + title + content);

		if (ok) {
			System.out.println("post updated");
			resp.sendRedirect("post");
		} else {

			resp.sendRedirect("post?mode=EDITPOST");
		}

	}

	protected void editPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long postId = Long.parseLong(req.getParameter("postId"));
		Posts post = postDAO.getPostById(postId);
		if (post == null) {
			System.out.println("post with id=" + postId + " is not found");
			return;
		}
		req.setAttribute("post", post);

		RequestDispatcher dispatcher = req.getRequestDispatcher("template/edit-post.jsp");
		dispatcher.forward(req, resp);

	}

	private void showPostCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("template/create-post.jsp");
		dispatcher.forward(req, resp);
	}

	private void postCreate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String content = req.getParameter("content");

		Posts newPost = new Posts(title, content);
		System.out.println(title + content);
		postDAO.createNewPost(newPost);

		String fileName;
		boolean isFileUploaded = false;
		Long postId = postDAO.getMaxPostId();

		// Process media files (images/videos)

		try {
			Collection<Part> mediaParts = req.getParts();
			for (Part part : mediaParts) {
				fileName = getFileName(part);

				// Skip non-file parts (e.g., form fields)
				if (fileName == null) {
					continue;
				}

				if (fileName != null && part.getSize() > 0) {
					isFileUploaded = true;
				}

			}

			if (isFileUploaded) {

				for (Part part : mediaParts) {
					fileName = getFileName(part);

					// Skip non-file parts (e.g., form fields)
					if (fileName == null) {
						continue;
					}

					String mimeType = part.getContentType();
					if (!ALLOWED_MIME_TYPES.contains(mimeType)) {
						postDAO.realdeletePostById(postId);
						req.setAttribute("title", title);
						req.setAttribute("content", content);
						req.setAttribute("message", "Unsupported file type:  or no File Upload !!");
						req.getRequestDispatcher("upload?mode=POSTCREATEPAGE").forward(req, resp);
						return;
					}
					String uniqueFileName = generateUniqueFileName(fileName);

					// Write the file
					part.write(uniqueFileName);

					Media newMedia = new Media(uniqueFileName, postId);

					boolean ok = mediaDAO.createNewMedia(newMedia);

					if (ok) {
						showPage(req, resp);
					}

				}

			} else {

				showPage(req, resp);

			}

		} catch (IOException ex) {
			// Handle file size or I/O errors
			postDAO.realdeletePostById(postId);
			req.setAttribute("title", title);
			req.setAttribute("content", content);
			req.setAttribute("message", "Error uploading file: " + ex.getMessage());
			req.getRequestDispatcher("upload?mode=POSTCREATEPAGE").forward(req, resp);

		} catch (ServletException ex) {
			postDAO.realdeletePostById(postId);
			// Handle multipart form size errors
			req.setAttribute("title", title);
			req.setAttribute("content", content);
			req.setAttribute("message", "File size exceeds the maximum allowed size.");
			req.getRequestDispatcher("upload?mode=POSTCREATEPAGE").forward(req, resp);
		}

	}

	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		if (contentDisposition.contains("filename=")) {
			return contentDisposition
					.substring(contentDisposition.indexOf("filename=") + 10, contentDisposition.length() - 1)
					.replace("\"", ""); // Remove quotes around filename
		}
		return null;
	}

	private String generateUniqueFileName(String fileName) {
		String fileExtension = "";

		// Get the file extension, if any
		int dotIndex = fileName.lastIndexOf('.');
		if (dotIndex != -1) {
			fileExtension = fileName.substring(dotIndex);
			fileName = fileName.substring(0, dotIndex); // Remove extension from name
		}

		// Append a UUID to the file name to ensure uniqueness
		String uniqueFileName = fileName + "_" + UUID.randomUUID().toString() + fileExtension;
		return uniqueFileName;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			List<Posts> preposts = postDAO.getAllPosts();

			List<Posts> posts = new ArrayList<>(preposts);

			Collections.reverse(posts);

			for (Posts post : posts) {

				List<Media> mediaForPost = mediaDAO.getMediaByPostId(post.getId());
				post.setMediaList(mediaForPost);

				List<Comments> commentsForPost = commentDAO.getCommentByPostId(post.getId());
				post.setCommentList(commentsForPost);

			}

			req.setAttribute("posts", posts);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/index.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load posts.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
