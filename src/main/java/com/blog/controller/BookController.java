package com.blog.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import com.blog.dao.BookDAO;
import com.blog.model.Books;
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

@MultipartConfig(location = "C:\\JavaEE_Workspace\\blog\\src\\main\\webapp\\template\\book\\bookMedia", fileSizeThreshold = 1024
		* 1024, // 1 MB

		maxFileSize = 1024 * 1024 * 10000, // 10000MB
		maxRequestSize = 1024 * 1024 * 10000 // 10000 MB

)
@WebServlet("/book")
public class BookController extends HttpServlet {

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

	private static final Set<String> ALLOWED_FILE_TYPES = new HashSet<>();

	// Initialize allowed media types (image and video)
	static {
		ALLOWED_FILE_TYPES.add("application/pdf"); // Allows PDF files

	}

	@Resource(name = "jdbc/blog_site")
	private DataSource dataSource;

	private BookDAO bookDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		bookDAO = new BookDAO(dataSource);

	}

	public BookController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "BOOK";
		}

		System.out.println(mode);

		switch (mode) {

		case "BOOK":
			showPage(req, resp);
			break;
		case "CREATE":
			createBook(req, resp);
			break;

		case "CREATEPAGE":
			showCreatePage(req, resp);
			break;
		case "SEARCH":
			search(req, resp);
			break;
		case "DETAIL":
			showDetail(req, resp);
			break;
		case "EDIT":
			showEditPage(req, resp);
			break;
		case "UPDATE":
			updateBook(req, resp);
			break;
		case "DELETE":
			deleteItem(req, resp);
			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	private void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Long id = Long.parseLong(req.getParameter("id"));
		boolean ok = bookDAO.deleteBookById(id);

		if (ok) {
			showPage(req, resp);
		}

	}

	private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		Books book = bookDAO.getBookById(id);

		if (book != null) {
			// Set the product details as a request attribute
			req.setAttribute("book", book);

			RequestDispatcher dispatcher = req.getRequestDispatcher("template/book/book-edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}

	private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/book/book-create.jsp");
		dispatcher.forward(req, resp);
	}

	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			System.out.println("ShowPage Work");

			List<Books> preBooks = bookDAO.getAllBook();

			List<Books> books = new ArrayList<>(preBooks);

			Collections.reverse(books);

			req.setAttribute("books", books);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/book/books.jsp");
			dispatcher.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Unable to load posts.");
			RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
			dispatcher.forward(req, resp);
		}

	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = req.getParameter("query");
		if (query == null) {
			showPage(req, resp);
		}
		List<Books> books = bookDAO.getAllBook();
		List<Books> filteredBook = books.stream().filter(e -> e.getName().contains(query)).toList();
		req.setAttribute("books", filteredBook);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/book/books.jsp");
		dispatcher.forward(req, resp);

	}

	private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		// Fetch product details from DAO
		Books book = bookDAO.getBookById(id);

		if (book != null) {
			// Set the product details as a request attribute
			req.setAttribute("book", book);

			RequestDispatcher dispatcher = req.getRequestDispatcher("template/book/book-detail.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
		}
	}

	private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Extract parameters from the request
		Long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");

		// Create a ShopItem object
		Books book = new Books(id, name, description); // Assuming `null` for create_date

		// Update the ShopItem in the database
		boolean success = bookDAO.updateBook(book);

		if (success) {
			// Redirect to a success page or show a success message
			resp.sendRedirect("book");
		} else {
			// Forward to an error page or show an error message
			req.setAttribute("errorMessage", "Failed to update shop item");
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	private void createBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		String message;
		String fileName = null;
		String bookfile = null;
		boolean ok = false;

		try {
			// Handling Image Upload
			Part imagePart = req.getPart("image");
			 if (imagePart != null && imagePart.getSize() > 0) {
				 fileName = getFileName(imagePart);
			 }
			
			String imageMimeType = imagePart.getContentType();

			// Handling Book File Upload
			Part bookPart = req.getPart("bookfile");
			bookfile = getFileName(bookPart);
			String bookMimeType = bookPart.getContentType();

			// Check MIME types
			if (!ALLOWED_MIME_TYPES.contains(imageMimeType) || !ALLOWED_FILE_TYPES.contains(bookMimeType)) {
				System.out.println("unsupported File");
				req.setAttribute("name", name);
				req.setAttribute("description", description);
				req.setAttribute("message", "Unsupported file type or no file uploaded!");
				req.getRequestDispatcher("book?mode=CREATE").forward(req, resp);
				return;
			}else {
				
			

			// Write the image file
			if(fileName != null && bookfile != null) {
				
				String uniqueImageName = generateUniqueFileName(fileName);
				imagePart.write(uniqueImageName);
				System.out.println(uniqueImageName);

				// Write the book file
				String uniqueBookName = generateUniqueFileName(bookfile);
				bookPart.write(uniqueBookName);
				System.out.println(uniqueBookName);
				
				// Save to database
				Books newBook = new Books(name, uniqueImageName, uniqueBookName, description);
				ok = bookDAO.createBook(newBook);
				System.out.println("ok :" + ok);
			}
			

			}

			if (ok) {
				message = "Book created successfully!";
				System.out.println(message);
				req.setAttribute("message", message);
				showPage(req, resp);
			} else {
				message = "Create Failed";
				req.setAttribute("name", name);
				req.setAttribute("description", description);
				req.setAttribute("message", message);
				req.getRequestDispatcher("book?mode=CREATEPAGE").forward(req, resp);
			}

		
	}catch (Exception ex) {
		message = "Unsupported Fie or Error uploading file :";
		req.setAttribute("message", message);
		req.setAttribute("name", name);
		req.setAttribute("description", description);
		req.getRequestDispatcher("book?mode=CREATEPAGE").forward(req, resp);
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

}
