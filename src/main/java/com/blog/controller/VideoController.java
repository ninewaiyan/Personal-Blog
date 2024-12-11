package com.blog.controller;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.sql.DataSource;
import com.blog.dao.VideoDAO;
import com.blog.model.Courses;
import com.blog.model.Videos;
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

@MultipartConfig(location = "C:\\JavaEE_Workspace\\blog\\src\\main\\webapp\\template\\video\\videoFile", fileSizeThreshold = 1024
* 1024, // 1 MB

maxFileSize = 1024 * 1024 * 10000, // 10000MB
maxRequestSize = 1024 * 1024 * 10000 // 10000 MB

)
@WebServlet("/watch")
public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Set<String> ALLOWED_MIME_TYPES = new HashSet<>();

	// Initialize allowed media types (image and video)
	static {
		ALLOWED_MIME_TYPES.add("video/mp4");
		ALLOWED_MIME_TYPES.add("video/mpeg");
	}

	@Resource(name = "jdbc/blog_site")
	private DataSource dataSource;
	
	private VideoDAO videoDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		videoDAO = new VideoDAO(dataSource);

	}

	public VideoController() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "WATCH";
		}

		switch (mode) {

		case "WATCH":
			showPage(req, resp);
			break;
		case "CREATEPAGE":
			showCreatePage(req, resp);
			break;
		case "CREATE":
			createVideo(req, resp);
			break;
		case "SEARCH":
			search(req, resp);
			break;
		case "EDIT":
			showEditPage(req, resp);
			break;
		case "UPDATE":
			updateVideo(req, resp);
			break;
		case "DELETE":
			deleteVideo(req, resp);
			break;


		}

	}
	
	private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Long course_id = Long.parseLong(req.getParameter("course_id"));
		Long id = Long.parseLong(req.getParameter("id"));
		Videos watch = videoDAO.getVideoById(id);

		if (watch!= null) {
			// Set the product details as a request attribute
			req.setAttribute("watch", watch);
			req.setAttribute("course_id", course_id);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/video/video-edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}
	
	
	private void updateVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Extract parameters from the request
        Long id = Long.parseLong(req.getParameter("id"));
        Long course_id = Long.parseLong(req.getParameter("course_id"));
        String title = req.getParameter("title");
        
        // Create a ShopItem object
        Videos video = new Videos(id,title); // Assuming `null` for create_date
        
        // Update the ShopItem in the database
        boolean success = videoDAO.updateVideo(video);

        if (success) {
            // Redirect to a success page or show a success message
        	
            resp.sendRedirect("watch?mode=WATCH&course_id="+course_id);
        } else {
            // Forward to an error page or show an error message
            req.setAttribute("errorMessage", "Failed to update shop item");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }
	
	private void deleteVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.parseLong(req.getParameter("id"));
		Long course_id = Long.parseLong(req.getParameter("course_id"));
		boolean ok = videoDAO.deleteVideoById(id);

		if (ok) {
			
			resp.sendRedirect("watch?mode=WATCH&course_id="+course_id);
		}

	}


	
	
	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long course_id = Long.parseLong(req.getParameter("course_id"));
		try {

			List<Videos> watches = videoDAO.getAllVideobyCourseId(course_id);
			req.setAttribute("course_id", course_id);
			req.setAttribute("watches",watches );
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/video/video.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load posts.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);
		}

	}
	
	
	private void createVideo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		Long courseId = Long.parseLong(req.getParameter("course_id"));
		String message;
		String fileName;
		try {
			Part part = req.getPart("video");
			fileName = getFileName(part);
			String mimeType = part.getContentType();
			if (!ALLOWED_MIME_TYPES.contains(mimeType)) {
				req.setAttribute("title", title);
				req.setAttribute("message", "Unsupported file type:  or no File Upload !!");
				req.getRequestDispatcher("watch?mode=CREATEPAGE").forward(req, resp);
				return;
			}

			String uniqueFileName = generateUniqueFileName(fileName);
			
			System.out.println(uniqueFileName);
			// Write the file
			part.write(uniqueFileName);

			Videos newVideo = new Videos(title,uniqueFileName,courseId);

			Boolean ok = videoDAO.createNewVideo(newVideo);
			if (ok) {
				showPage(req, resp);
			} else {

				message = "Create Fail";
				req.setAttribute("title", title);
				req.setAttribute("message", message);
				req.getRequestDispatcher("watch?mode=CREATEPAGE").forward(req, resp);

			}

		} catch (Exception ex) {
			message = "Errro uploading file :" + ex.getMessage();
			req.setAttribute("title", title);
			req.setAttribute("message", message);
			req.getRequestDispatcher("shop?mode=CREATEPAGE").forward(req, resp);
		}

	}
	
	
	
	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long course_id = Long.parseLong(req.getParameter("course_id"));
		String query = req.getParameter("query");
		System.out.println(course_id + " " + query);
		if (query == null) {
			req.setAttribute("course_id", course_id);
			resp.sendRedirect("watch?mode=WATCH");
		}
		List<Videos> videos = videoDAO.getAllVideobyCourseId(course_id);
		List<Videos> filteredVideos = videos.stream().filter(e -> e.getTitle().contains(query)).toList();
		req.setAttribute("course_id", course_id);
		req.setAttribute("watches",filteredVideos);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/video/video.jsp");
		dispatcher.forward(req, resp);

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
		Long course_id = Long.parseLong(req.getParameter("course_id"));
		req.setAttribute("course_id", course_id);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/video/video-create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}


}
