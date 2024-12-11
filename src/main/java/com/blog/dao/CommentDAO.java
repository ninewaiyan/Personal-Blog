package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Comments;
import com.blog.model.Media;
import com.blog.model.Posts;
import com.blog.model.User;

public class  CommentDAO {
	
	private final DataSource dataSource ;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public CommentDAO(DataSource dataSource)
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
	
	 public void createNewComment(Comments comment ) {
	        String sql = "INSERT INTO comments (comment,username,post_id) VALUES (?, ?,?)";
	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            pStmt.setString(1,comment.getComment() );
	            pStmt.setString(2,comment.getUserName() );
	            pStmt.setLong(3, comment.getPostId());
	            pStmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            close(connection, pStmt, null);
	        }
	    }
	 
	 
	
	 public List<Comments> getCommentByPostId(Long postId) {
		    String sql = "SELECT * FROM comments WHERE post_id = ? AND delete_date IS NULL";
		    List<Comments> commentList = new ArrayList<>();
		    
		    try {
		        connection = dataSource.getConnection();
		        pStmt = connection.prepareStatement(sql);
		        pStmt.setLong(1, postId);
		        rs = pStmt.executeQuery();
		        
		        // Iterate through the result set and add each media item to the list
		        while (rs.next()) {
		            Comments comment = new Comments(
		                rs.getLong("id"),        
		                rs.getString("comment"),  
		                rs.getString("username"),
		                rs.getLong("post_id")    
		            );
		            commentList.add(comment);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(connection, pStmt, rs);
		    }
		    
		    return commentList;
		}
	 
	 public void deleteCommentByPostId(Long postId) {
		    String sql = "UPDATE  comments SET delete_date = NOW() WHERE post_id = ?";

		    try {
		        connection = dataSource.getConnection();
		        pStmt = connection.prepareStatement(sql);
		        pStmt.setLong(1, postId);

		        // Execute the update
		        int rowsAffected = pStmt.executeUpdate();
		        if (rowsAffected > 0) {
		            System.out.println("Post soft-deleted by setting delete_date for post_id: " + postId);
		        } else {
		            System.out.println("No post found with post_id: " + postId);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        close(connection, pStmt, null);
		    }
		}

	 
	 public boolean deleteCommentById(Long id) {
		    String sql = "UPDATE comments SET delete_date = NOW() WHERE id = ?";
		    try (Connection connection = dataSource.getConnection();
		         PreparedStatement pStmt = connection.prepareStatement(sql)) {

		        pStmt.setLong(1, id);
		        int affectedRows = pStmt.executeUpdate();
		        return affectedRows > 0; // Returns true if at least one row is updated

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

	
	 
	 

}
