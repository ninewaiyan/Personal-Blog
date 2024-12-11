package com.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post")
public class PostController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	public PostController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "POST";
		}

		switch (mode) {

		case "POST":
			showPage(req, resp);
			break;

		case "DELETE":
			deletePost(req, resp);
			break;

		case "CREATECOMMENT":
			createComment(req, resp);
			break;
		case "DELETECOMMENT":
			deleteComment(req, resp);
			break;
		case "SEARCH":
			search(req, resp);

		}

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

	private void deletePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Long post_id = Long.parseLong(req.getParameter("post_id"));
			postDAO.deletePostById(post_id);
			mediaDAO.deleteMediaByPostId(post_id);
			commentDAO.deleteCommentByPostId(post_id);
			resp.sendRedirect("post");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load posts.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.parseLong(req.getParameter("comment_id"));

		boolean ok = commentDAO.deleteCommentById(id);

		if (ok) {
			
			showPage(req, resp);
		}

	}

	private void createComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long post_id = Long.parseLong(req.getParameter("postId"));
		String commentText = req.getParameter("comment");
		String username = req.getParameter("username");

		Comments comment = new Comments(commentText, username, post_id);
		commentDAO.createNewComment(comment);
		resp.sendRedirect(req.getContextPath() + "/post");
		System.out.println("comment success");

	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = req.getParameter("query");
		if (query.equals("")) {
			showPage(req, resp);
		}
		List<Posts>posts = postDAO.getAllPosts();
		List<Posts>filteredPost =posts.stream()
				.filter(e->e.getTitle().contains(query))
				.toList();
		
		for (Posts post : filteredPost) {

			List<Media> mediaForPost = mediaDAO.getMediaByPostId(post.getId());
			post.setMediaList(mediaForPost);

			List<Comments> commentsForPost = commentDAO.getCommentByPostId(post.getId());
			post.setCommentList(commentsForPost);

		}
		req.setAttribute("posts",filteredPost);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/index.jsp");
		dispatcher.forward(req, resp);
	
	}

}
