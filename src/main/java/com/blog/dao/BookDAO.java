package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Books;
import com.blog.model.Posts;
import com.blog.model.ShopItems;
import com.blog.model.User;

public class BookDAO {

	private final DataSource dataSource;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public BookDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void close(Connection connection, PreparedStatement pStmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pStmt != null)
				pStmt.close();
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createBook(Books book) {
		boolean ok = false;
		String sql = "INSERT INTO books (name,image,bookfile,description) VALUES (?, ?,?,?)";
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, book.getName());
			pStmt.setString(2, book.getImage());
			pStmt.setString(3, book.getBookfile());
			pStmt.setString(4, book.getDescription());
			
			

			int rowsAffected = pStmt.executeUpdate();

			ok = (rowsAffected > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}
		return ok;
	}

	public List<Books> getAllBook() {
		List<Books> bookList = new ArrayList<>();
		String sql = "SELECT * FROM books  WHERE delete_date IS NULL";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Books book = new Books(rs.getLong("id"), rs.getString("name"), rs.getString("image"),
						rs.getString("bookfile"),rs.getString("description"));
				
				bookList.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}

		return bookList;
	}
	
	
	    public boolean updateBook(Books book) {
	        boolean success = false;
	        String sql = "UPDATE books SET name = ?, description = ? WHERE id = ?";

	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            pStmt.setString(1, book.getName());
	            pStmt.setString(2, book.getDescription());
	            pStmt.setLong(3, book.getId());

	            

	            int rowEffected = pStmt.executeUpdate();
	            if (rowEffected > 0) {
	                success = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	        	close(connection, pStmt, null);
	        }

	        return success;
	    }
	   


	// Get a shop item by ID
	public Books getBookById(long id) {
		Books book = null;
		String sql = "SELECT * FROM books WHERE id = ?";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setLong(1, id);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				book = new Books(rs.getLong("id"), rs.getString("name"), rs.getString("image"),
						rs.getString("bookfile"), rs.getString("description")// Assuming create_date is of type DATE
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, rs);
		}

		return book;
	}
	public boolean deleteBookById(Long id) {
	    String sql = "UPDATE books SET delete_date = NOW() WHERE id = ?";
	    try {
	        connection = dataSource.getConnection();
	        pStmt = connection.prepareStatement(sql);
	        pStmt.setLong(1, id);
	        int affectedRows = pStmt.executeUpdate();
	        return affectedRows > 0; // Returns true if at least one row is updated
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        close(connection, pStmt, null);
	    }
	}

}
