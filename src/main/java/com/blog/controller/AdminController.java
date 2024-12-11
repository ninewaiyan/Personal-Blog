package com.blog.controller;



import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.blog.dao.UserDAO;
import com.blog.model.Books;
import com.blog.model.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/blog_site")
	private DataSource dataSource;
	
	private UserDAO userDAO;
	
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDAO = new UserDAO(dataSource);
	}

		


	public AdminController() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if(mode == null) {
			mode = "PORTAL";
		}
		
		switch(mode) {
		case "PORTAL":
			showPortal(req, resp);
			break;
			
		case "DISABLE":
			disable(req, resp);
			break;
			
		case "ENABLE":
			enable(req, resp);
			break;
		case "SEARCH":
			System.out.println("SEARCH");
			search(req, resp);
			break;
		
			
		default:
			break;
		}
	}
	
	protected void enable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.enableUser(userId);
		
		if(ok) {
			showPortal(req, resp);
		}else {
			System.out.println("user enable action is filed");
		}
	}
	
	protected void disable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long userId = Long.parseLong(req.getParameter("userId"));
		boolean ok = userDAO.disableUser(userId);
		
		if(ok) {
			showPortal(req, resp);
		}else {
			System.out.println("user disable  atcion is filed");
		}
	}
	
	protected void showPortal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	List<User>users = userDAO.getAllUsers();
	req.setAttribute("users", users);
	RequestDispatcher dispatcher = req.getRequestDispatcher("template/admin-portal.jsp");
	dispatcher.forward(req, resp);
		

	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = req.getParameter("query");
		System.out.println(query);
		if (query == null) {
			showPortal(req, resp);
		}
		List<User> users = userDAO.getAllUsers();
		List<User> filteredUsers = users.stream()
			    .filter(e -> e.getUsername().contains(query) || 
			                 e.getEmail().contains(query) || 
			                 e.getFirstname().contains(query) || 
			                 e.getLastname().contains(query))
			    .toList();
	
		req.setAttribute("users", filteredUsers);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/admin-portal.jsp");
		dispatcher.forward(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	
}
