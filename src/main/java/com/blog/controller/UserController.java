package com.blog.controller;

import java.io.IOException;

import javax.sql.DataSource;

import com.blog.dao.UserDAO;
import com.blog.model.User;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class UserController  extends HttpServlet{

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

	public UserController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		
		if(mode == null)
		{
			mode = "FORM";
		}
		
		switch(mode) {
		
		case "FORM":
			showRegisterForm(req, resp);
			break;
		case "REGISTER":
			register(req, resp);
			break;
			
		default:
			showRegisterForm(req, resp);
			break;
		}
	}
	
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = "user";
		boolean enable = true;
		
		User existedUsername= userDAO.getUserByUsername(username);
		User existedEmail = userDAO.getUserByEmail(email);
		
		System.out.println(firstname + lastname + username + email + password + role + enable);
		
		if(existedUsername !=null) {
			req.setAttribute("usernameAlreadyExists",true);
			req.setAttribute("firstname", firstname);
			req.setAttribute("lastname", lastname);
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("password", password);
			showRegisterForm(req,resp);
			return;
		}else if(existedEmail !=null) {
			
			
			req.setAttribute("firstname", firstname);
			req.setAttribute("lastname", lastname);
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("password", password);
			
			req.setAttribute("emailAlreadyExists",true);
			showRegisterForm(req,resp);
			return;
		}else {
			
			
			User user = new User(firstname, lastname, username, email, password, role,enable);
			boolean ok = userDAO.createUser(user);
			
			
			req.setAttribute("firstname", firstname);
			req.setAttribute("lastname", lastname);
			req.setAttribute("username", username);
			req.setAttribute("email", email);
			req.setAttribute("password", password);
			req.setAttribute("ok", ok);
			showRegisterForm(req, resp);	
			
		}
		
		
	}
	
	protected void showRegisterForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/register.jsp");
		dispatcher.forward(req,resp);
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	

}
