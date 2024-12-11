package com.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import com.blog.dao.CourseDAO;
import com.blog.dao.ShopItemDAO;
import com.blog.model.Courses;
import com.blog.model.ShopItems;

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

@MultipartConfig(location = "C:\\JavaEE_Workspace\\blog\\src\\main\\webapp\\template\\course\\courseImage", fileSizeThreshold = 1024
		* 1024, // 1 MB

		maxFileSize = 1024 * 1024 * 10000, // 10000MB
		maxRequestSize = 1024 * 1024 * 10000 // 10000 MB

)
@WebServlet("/course")
public class CourseController extends HttpServlet {

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
	}

	@Resource(name = "jdbc/blog_site")
	private DataSource dataSource;

	private CourseDAO courseDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		courseDAO = new CourseDAO(dataSource);

	}

	public CourseController() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "COURSE";
		}

		switch (mode) {

		case "COURSE":
			showPage(req, resp);
			break;
		case "CREATEPAGE":
			showCreatePage(req, resp);
			break;
		case "CREATE":
			createCourse(req,resp);
			break;
		case "EDIT":
			showEditPage(req,resp);
			break;
		case "UPDATE":
			updateCourse(req, resp);
			break;
		case "SEARCH":
			search(req, resp);
			break;
		case "DELETE":
			deleteCourse(req, resp);
			break;
		}

	}
	
	private void deleteCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.parseLong(req.getParameter("id"));

		boolean ok = courseDAO.deleteCourseById(id);

		if (ok) {
			
			showPage(req, resp);
		}

	}

	
	private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		Courses course = courseDAO.getCourseById(id);;

		if (course!= null) {
			// Set the product details as a request attribute
			req.setAttribute("course", course);

			RequestDispatcher dispatcher = req.getRequestDispatcher("template/course/course-edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = req.getParameter("query");
		if (query.equals("")) {
			System.out.println("empty search");
			showPage(req, resp);
		}
		
		List<Courses> courses = courseDAO.getAllCourse();
		 List<Courses> filteredCourse = courses.stream()
		            .filter(e -> e.getName().toLowerCase().contains(query.toLowerCase()))
		            .toList();
		
		req.setAttribute("courses",filteredCourse);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/course/course.jsp");
		dispatcher.forward(req, resp);

	}
	
	private void updateCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extract parameters from the request
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        
        // Create a ShopItem object
        Courses course = new Courses(id,name); // Assuming `null` for create_date
        
        // Update the ShopItem in the database
        boolean success = courseDAO.updateCourse(course);

        if (success) {
            // Redirect to a success page or show a success message
            resp.sendRedirect("course");
        } else {
            // Forward to an error page or show an error message
        	req.setAttribute("name", name);
            req.setAttribute("errorMessage", "Failed to update shop item");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
	


	

	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			List<Courses> pcourses = courseDAO.getAllCourse();

			List<Courses> courses = new ArrayList<>(pcourses);

			Collections.reverse(courses);

			req.setAttribute("courses", courses);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/course/course.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load posts.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);
		}

	}

	private void createCourse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String message;
		String fileName;
		try {
			Part part = req.getPart("image");
			fileName = getFileName(part);
			String mimeType = part.getContentType();
			if (!ALLOWED_MIME_TYPES.contains(mimeType)) {
				req.setAttribute("message", "Unsupported file type:  or no File Upload !!");
				req.getRequestDispatcher("course?mode=CREATE").forward(req, resp);
				return;
			}

			String uniqueFileName = generateUniqueFileName(fileName);
			// Write the file
			part.write(uniqueFileName);

			Courses newCourse = new Courses(name,uniqueFileName);

			Boolean ok = courseDAO.createNewCourse(newCourse);
			if (ok) {
				showPage(req, resp);
			} else {

				message = "Create Fail";
				req.setAttribute("message", message);
				req.getRequestDispatcher("course?mode=CREATEPAGE").forward(req, resp);

			}

		} catch (Exception ex) {
			
			message = "Unsupported File or Error uploading file :" ;
			req.setAttribute("name", name);
			req.setAttribute("message", message);
			req.getRequestDispatcher("course?mode=CREATEPAGE").forward(req, resp);
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


	private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/course/course-create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
