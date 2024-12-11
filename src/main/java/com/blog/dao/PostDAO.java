package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Posts;
import com.blog.model.User;

public class PostDAO {
	
	private final DataSource dataSource ;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public PostDAO(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	private void close(Connection connection, PreparedStatement pStmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pStmt != null) pStmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public long getMaxPostId() {
	    String sql = "SELECT MAX(id) AS max_id FROM posts";
	    long maxId = -1; // Default value indicating no ID found

	    try {
	        connection = dataSource.getConnection();
	        pStmt = connection.prepareStatement(sql);
	        ResultSet rs = pStmt.executeQuery();

	        if (rs.next()) {
	            maxId = rs.getLong("max_id");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(connection, pStmt, null);
	    }

	    return maxId;
	}

	
	 public boolean createNewPost(Posts post) {
		 boolean ok = false;
	        String sql = "INSERT INTO posts (title, content,create_time) VALUES (?, ?,?)";
	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            pStmt.setString(1, post.getTitle());
	            pStmt.setString(2, post.getContent());
	            pStmt.setTimestamp(3, post.getCreateTime());
	            int rowsAffected = pStmt.executeUpdate();
	            
	            ok = (rowsAffected > 0);
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(connection, pStmt, null);
	        }
	        return ok;
	    }
	 
	 public List<Posts> getAllPosts() {
	        List<Posts> postsList = new ArrayList<Posts>();
	        String sql = "SELECT * FROM posts where delete_date is null";
	        

	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            rs = pStmt.executeQuery();

	            while (rs.next()) {
	                Posts post = new Posts(
	                		rs.getLong("id"),
	                		rs.getString("title"), 
	                		rs.getString("content"),
	                		rs.getTimestamp("create_time"));
	               
	                postsList.add(post);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources in the reverse order of their creation
	            close(connection, pStmt, rs);
	        }

	        return postsList;
	    }
	 
	 public List<Posts> getAllPostsByTitle(String title) {
		    List<Posts> postsList = new ArrayList<>();
		    String sql = "SELECT * FROM posts WHERE delete_date IS NULL AND title LIKE ?";

		    try {
		        connection = dataSource.getConnection();
		        pStmt = connection.prepareStatement(sql);
		        
		        // Set the title parameter using a wildcard for partial matches
		        pStmt.setString(1, "%" + title + "%");
		        
		        rs = pStmt.executeQuery();

		        while (rs.next()) {
		            Posts post = new Posts(
		                rs.getLong("id"),
		                rs.getString("title"), 
		                rs.getString("content"),
		                rs.getTimestamp("create_time")
		            );
		            postsList.add(post);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close resources in the reverse order of their creation
		        close(connection, pStmt, rs);
		    }

		    return postsList;
		}

	 
	 public boolean updatePost(Posts post) {
		    // SQL query to update the post where the ID matches
		    String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ? AND delete_date IS NULL";
		    boolean updated = false;

		    try {
		        // Get the connection from the DataSource
		        connection = dataSource.getConnection();
		        
		        // Prepare the statement
		        pStmt = connection.prepareStatement(sql);
		        
		        // Set the parameters for the query
		        pStmt.setString(1, post.getTitle());
		        pStmt.setString(2, post.getContent());
		        pStmt.setLong(3, post.getId());

		        // Execute the update statement
		        int rowsAffected = pStmt.executeUpdate();
		        
		        // Check if the post was successfully updated
		        updated = (rowsAffected > 0);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close resources
		        close(connection, pStmt, rs);
		    }

		    return updated;
		}

	 
	 public Posts getPostById(Long postId) {
		    Posts post = null; // Initialize to null
		    String sql = "SELECT * FROM posts WHERE id = ? AND delete_date IS NULL";

		    try {
		        connection = dataSource.getConnection();
		        pStmt = connection.prepareStatement(sql);
		        pStmt.setLong(1, postId); // Set the postId parameter in the query
		        rs = pStmt.executeQuery();

		        if (rs.next()) { // Use if instead of while since you're expecting a single result
		            post = new Posts(
		                rs.getLong("id"),
		                rs.getString("title"),
		                rs.getString("content"),
		                rs.getTimestamp("create_time")
		            );
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close resources in the reverse order of their creation
		        close(connection, pStmt, rs);
		    }

		    return post; // Return the post object or null if not found
		}

	 
	 public void deletePostById(Long postId) {
		    String sql = "UPDATE posts SET delete_date = NOW() WHERE id = ?";
		    try {
		        connection = dataSource.getConnection();
		        pStmt = connection.prepareStatement(sql);
		        pStmt.setLong(1, postId);
		        pStmt.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(connection, pStmt, null);
		    }
		}
	 
	 public void realdeletePostById(Long postId) {
		    String sql = "DELETE FROM posts WHERE id = ?";
		    try (Connection connection = dataSource.getConnection();
		         PreparedStatement pStmt = connection.prepareStatement(sql)) {
		        pStmt.setLong(1, postId);
		        int rowsAffected = pStmt.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Post with ID " + postId + " has been permanently deleted.");
		        } else {
		            System.out.println("No post found with ID " + postId);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}


	
	 
	 

}
