package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Courses;
import com.blog.model.Videos;

public class VideoDAO {
	private final DataSource dataSource ;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public VideoDAO(DataSource dataSource) {
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

	public boolean updateCourse(Courses course) {
		boolean success = false;
		String sql = "UPDATE courses SET name = ? WHERE id = ?";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, course.getName());
			pStmt.setLong(2, course.getId());

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

	public List<Videos> getAllVideobyCourseId(Long course_id) {
		List<Videos> videoList = new ArrayList<>();
		String sql = "SELECT * FROM videos WHERE course_id = ? AND delete_date IS NULL";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setLong(1, course_id);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Videos video = new Videos(rs.getLong("id"), rs.getString("title"), rs.getString("video"),rs.getLong("course_id"));
				videoList.add(video);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}

		return videoList;
	}

	// Get a shop item by ID
	public Videos getVideoById(long id) {
		Videos video = null;
		String sql = "SELECT * FROM videos WHERE id = ?";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setLong(1, id);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				video = new Videos(rs.getLong("id"), rs.getString("title"), rs.getString("video"),rs.getLong("course_id"));
																									

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, rs);
		}

		return video;
	}

	public boolean createNewVideo(Videos video) {
		boolean ok = false;
		String sql = "INSERT INTO videos (title,video,course_id) VALUES (?, ?,?)";
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, video.getTitle());
			pStmt.setString(2, video.getVideo());
			pStmt.setLong(3, video.getCourse_id());
			
			int rowsAffected = pStmt.executeUpdate();

			ok = (rowsAffected > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}
		return ok;
	}
	
	 public boolean updateVideo(Videos video) {
	        boolean success = false;
	        String sql = "UPDATE videos SET title = ? WHERE id = ?";

	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            pStmt.setString(1, video.getTitle());
	            pStmt.setLong(2, video.getId());

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
	 
	 
	 public boolean deleteVideoById(Long id) {
		    String sql = "UPDATE videos SET delete_date = NOW() WHERE id = ?";
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
