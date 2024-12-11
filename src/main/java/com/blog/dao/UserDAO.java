package com.blog.dao;

import com.blog.crypto.PasswordEncoder;
import com.blog.crypto.PasswordValidator;
import com.blog.model.User;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class UserDAO {
    private final DataSource dataSource;
    private Connection connection;
    private PreparedStatement pStmt;
    private ResultSet rs;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Close resources
    private void close(Connection connection, PreparedStatement pStmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pStmt != null) pStmt.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create a new user
    public boolean createUser(User user) {
    	boolean ok = false;
        String sql = "INSERT INTO users(firstname,lastname,username, email, password, role,enable) VALUES (?,?,?,?,?,?,?)";
        try {
        	
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1,user.getFirstname());
            pStmt.setString(2,user.getLastname());
            pStmt.setString(3,user.getUsername());
            pStmt.setString(4,user.getEmail());
            try {
				pStmt.setString(5,PasswordEncoder.encode(user.getPassword()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            pStmt.setString(6,user.getRole());
            pStmt.setBoolean(7, user.isEnable());
            
            int rowEffected = pStmt.executeUpdate();
    		if(rowEffected > 0 )
    		{
    			ok = true;
    		}
    		
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, null);
        }      
        return ok;
    }
    
    public boolean isAuthenticated(String usernameORemail,String password)
	{
		boolean ok = false;
		User userByEmail = getUserByEmail(usernameORemail);
		User userByUserName = getUserByUsername(usernameORemail);
		
		
		try {
			if((userByEmail != null && userByEmail.isEnable() && PasswordValidator.validatePassword(password, userByEmail.getPassword()))||
			(userByUserName != null && userByUserName.isEnable() && PasswordValidator.validatePassword(password, userByUserName.getPassword())))
				
				
			ok = true;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;
		
	}
    
    
    // Get user by ID
    public User getUserById(long userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setLong(1, userId);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getBoolean("enable")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, rs);
        }
        return null;
    }
    
    // Get user by ID
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, email);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getBoolean("enable")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, rs);
        }
        return null;
    }

    // Get user by username
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, username);
            rs = pStmt.executeQuery();
            if (rs.next()) {
                return new User(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getBoolean("enable")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, rs);
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users WHERE role != 'admin'";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                    rs.getLong("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role"),
                    rs.getBoolean("enable")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, rs);
        }
        return users;
    }

    // Update a user
    public void updateUser(User user) {
        String sql = "UPDATE Users SET username = ?, email = ?, password = ?, role = ? WHERE user_id = ?";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setString(1, user.getUsername());
            pStmt.setString(2, user.getEmail());
            pStmt.setString(3, user.getPassword());
            pStmt.setString(4, user.getRole());
            pStmt.setLong(5, user.getUserId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, null);
        }
    }

    // Delete a user
    public void deleteUser(long userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            connection = dataSource.getConnection();
            pStmt = connection.prepareStatement(sql);
            pStmt.setLong(1, userId);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(connection, pStmt, null);
        }
    }
    
    public boolean enableUser(Long userId)
	{
		boolean  ok = false;
		
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update users set enable=? where id=?;");
			pStmt.setBoolean(1, true);
			pStmt.setLong(2, userId);
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected > 0) {
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(connection, pStmt, null);
		}
		return ok;
	}
	
	
	public boolean disableUser(Long userId)
	{
		boolean  ok = false;
			
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement("update users set enable=? where id=?;");
			pStmt.setBoolean(1, false);
			pStmt.setLong(2, userId);
			int rowEffected = pStmt.executeUpdate();
			if(rowEffected > 0) {
				ok = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(connection, pStmt, null);
		}
		return ok;
	}

}
