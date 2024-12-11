package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Courses;
import com.blog.model.ShopItems;

public class CourseDAO {
	
	private final DataSource dataSource;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public CourseDAO(DataSource dataSource) {
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
	   
	
	public List<Courses> getAllCourse() {
		List<Courses> courseList = new ArrayList<>();
		String sql = "SELECT * FROM courses WHERE delete_date IS NULL";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				Courses course = new Courses(rs.getLong("id"), rs.getString("name"),
						 rs.getString("image"));
				courseList.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}

		return courseList;
	}
	
	// Get a shop item by ID
		public Courses getCourseById(long id) {
			Courses course= null;
			String sql = "SELECT * FROM courses WHERE id = ?";

			try {
				connection = dataSource.getConnection();
				pStmt = connection.prepareStatement(sql);
				pStmt.setLong(1, id);
				rs = pStmt.executeQuery();

				if (rs.next()) {
					course = new Courses(rs.getLong("id"), rs.getString("name"), rs.getString("image"));// Assuming create_date is of type DATE
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(connection, pStmt, rs);
			}

			return course;
		}
	public boolean createNewCourse(Courses course) {
		boolean ok = false;
		String sql = "INSERT INTO courses (name,image) VALUES (?, ?)";
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, course.getName());
			pStmt.setString(2, course.getImage());
			int rowsAffected = pStmt.executeUpdate();

			ok = (rowsAffected > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}
		return ok;
	}
	
	 public boolean deleteCourseById(Long id) {
		    String sql = "UPDATE courses SET delete_date = NOW() WHERE id = ?";
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
