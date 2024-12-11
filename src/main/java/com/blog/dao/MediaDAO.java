package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Media;
import com.blog.model.Posts;
import com.blog.model.User;

public class MediaDAO {
	
	private final DataSource dataSource ;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	
	public MediaDAO(DataSource dataSource)
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
	
	public boolean createNewMedia(Media media) {
		boolean ok = false;
        String sql = "INSERT INTO media (path, post_id) VALUES (?, ?)";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, media.getPath());
            pStmt.setLong(2, media.getPostId());
            int rowEffected = pStmt.executeUpdate();
            ok = ( rowEffected > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, null);
        }
        
        return ok;
    }
	
	
	public List<Media> getAllMedia() {
        List<Media> media = new ArrayList<>();
        String sql = "SELECT * FROM media ";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                media.add(new Media(
                		rs.getString("path"), 
                		rs.getLong("post_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, rs);
        }
        return media;
    }
	
	
	public List<Media> getMediaByPostId(Long postId) {
	    String sql = "SELECT * FROM media WHERE post_id = ? and delete_date is  null";
	    List<Media> mediaList = new ArrayList<>();
	    
	    try {
	        connection = dataSource.getConnection();
	        pStmt = connection.prepareStatement(sql);
	        pStmt.setLong(1, postId);
	        rs = pStmt.executeQuery();
	        
	        // Iterate through the result set and add each media item to the list
	        while (rs.next()) {
	            Media media = new Media(
	                rs.getLong("id"),        
	                rs.getString("path"),     
	                rs.getLong("post_id")    
	            );
	            mediaList.add(media);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        close(connection, pStmt, rs);
	    }
	    
	    return mediaList;
	}
	
	public void deleteMediaByPostId(Long postId) {
	    String sql = "UPDATE media SET delete_date = NOW() WHERE post_id = ?";

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


	
	
	

}
