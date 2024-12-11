package com.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.sql.DataSource;

import com.blog.dao.ShopItemDAO;
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

@MultipartConfig(location = "C:\\JavaEE_Workspace\\blog\\src\\main\\webapp\\template\\shop\\shopMedia", fileSizeThreshold = 1024
		* 1024, // 1 MB

		maxFileSize = 1024 * 1024 * 10000, // 10000MB
		maxRequestSize = 1024 * 1024 * 10000 // 10000 MB

)
@WebServlet("/shop")
public class ShopController extends HttpServlet {

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

	private ShopItemDAO shopItemDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub

		shopItemDAO = new ShopItemDAO(dataSource);

	}

	public ShopController() {

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mode = req.getParameter("mode");
		if (mode == null) {
			mode = "SHOP";
		}

		switch (mode) {

		case "SHOP":
			showPage(req, resp);
			break;
		case "CREATE":
			createShopItem(req, resp);
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
			updateShopItem(req, resp);
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
		    boolean ok = shopItemDAO.deleteShopItemById(id);
		    
		    if(ok) {
		    	showPage(req, resp);
		    }
		    
		     
		

	}
	private void showEditPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		ShopItems shopItem = shopItemDAO.getShopItemById(id);

		if (shopItem != null) {
			// Set the product details as a request attribute
			req.setAttribute("shopItem", shopItem);

			RequestDispatcher dispatcher = req.getRequestDispatcher("template/shop/shop-edit.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}

	private void showCreatePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/shop/create-shopItem.jsp");
		dispatcher.forward(req, resp);
	}

	private void showPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			List<ShopItems> preshopItems = shopItemDAO.getAllShopItems();

			List<ShopItems> shopItems = new ArrayList<>(preshopItems);

			Collections.reverse(shopItems);

			req.setAttribute("shopItems", shopItems);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/shop/shop.jsp");
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
		List<ShopItems> shopItems = shopItemDAO.getAllShopItems();
		List<ShopItems> filteredItems = shopItems.stream().filter(e -> e.getName().contains(query)).toList();
		req.setAttribute("shopItems", filteredItems);
		RequestDispatcher dispatcher = req.getRequestDispatcher("template/shop/shop.jsp");
		dispatcher.forward(req, resp);

	}

	private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("id"));
		// Fetch product details from DAO
		ShopItems shopItem = shopItemDAO.getShopItemById(id);

		if (shopItem != null) {
			// Set the product details as a request attribute
			req.setAttribute("shopItem", shopItem);
			RequestDispatcher dispatcher = req.getRequestDispatcher("template/shop/shop-detail.jsp");
			dispatcher.forward(req, resp);
		} else {
			// Handle case where product is not found, possibly show an error page
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
		}
	}

	 private void updateShopItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Extract parameters from the request
	        Long id = Long.parseLong(req.getParameter("id"));
	        String name = req.getParameter("name");
	        String price = req.getParameter("price");
	        String description = req.getParameter("description");
	        
	        // Create a ShopItem object
	        ShopItems shopItem = new ShopItems(id,name,price,description); // Assuming `null` for create_date
	        
	        // Update the ShopItem in the database
	        boolean success = shopItemDAO.updateShopItem(shopItem);

	        if (success) {
	            // Redirect to a success page or show a success message
	            resp.sendRedirect("shop");
	        } else {
	            // Forward to an error page or show an error message
	            req.setAttribute("errorMessage", "Failed to update shop item");
	            req.getRequestDispatcher("/error.jsp").forward(req, resp);
	        }
	    }

	private void createShopItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String price = req.getParameter("price");
	
		String description = req.getParameter("description");
		String message;
		String fileName;
		try {
			Part part = req.getPart("image");
			fileName = getFileName(part);
			String mimeType = part.getContentType();
			if (!ALLOWED_MIME_TYPES.contains(mimeType)) {
				req.setAttribute("name", name);
				req.setAttribute("price", price);
				req.setAttribute("description", description);
				req.setAttribute("message", "Unsupported file type:  or no File Upload !!");
				req.getRequestDispatcher("shop?mode=CREATE").forward(req, resp);
				return;
			}

			String uniqueFileName = generateUniqueFileName(fileName);
			// Write the file
			part.write(uniqueFileName);

			ShopItems newShopItem = new ShopItems(name, price, description, uniqueFileName);

			Boolean ok = shopItemDAO.createNewShopItems(newShopItem);
			if (ok) {
				showPage(req, resp);
			} else {

				message = "Create Fail";
				req.setAttribute("message", message);
				req.setAttribute("name", name);
				req.setAttribute("price", price);
				req.setAttribute("description", description);
				req.getRequestDispatcher("shop?mode=CREATEPAGE").forward(req, resp);

			}

		} catch (Exception ex) {
			message = "Unsupported File or Error uploading file";
			req.setAttribute("message", message);
			req.setAttribute("name", name);
			req.setAttribute("price", price);
			req.setAttribute("description", description);
			req.getRequestDispatcher("shop?mode=CREATEPAGE").forward(req, resp);
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
