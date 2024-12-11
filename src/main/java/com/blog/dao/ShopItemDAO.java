package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.blog.model.Posts;
import com.blog.model.ShopItems;
import com.blog.model.User;

public class ShopItemDAO {

	private final DataSource dataSource;
	private Connection connection;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public ShopItemDAO(DataSource dataSource) {
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

	public boolean createNewShopItems(ShopItems shopItem) {
		boolean ok = false;
		String sql = "INSERT INTO shopitems (name, description,image,price) VALUES (?, ?,?,?)";
		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setString(1, shopItem.getName());
			pStmt.setString(2, shopItem.getDescription());
			pStmt.setString(3, shopItem.getImage());
			pStmt.setString(4, shopItem.getPrice());

			int rowsAffected = pStmt.executeUpdate();

			ok = (rowsAffected > 0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}
		return ok;
	}

	public List<ShopItems> getAllShopItems() {
		List<ShopItems> shopItemList = new ArrayList<>();
		String sql = "SELECT * FROM shopitems WHERE delete_date IS NULL";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			rs = pStmt.executeQuery();

			while (rs.next()) {
				ShopItems shopItem = new ShopItems(rs.getLong("id"), rs.getString("name"), rs.getString("price"),
						rs.getString("description"), rs.getString("image"));
				shopItemList.add(shopItem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, null);
		}

		return shopItemList;
	}
	
	
	    public boolean updateShopItem(ShopItems shopItem) {
	        boolean success = false;
	        String sql = "UPDATE shopitems SET name = ?, description = ?, price = ? WHERE id = ?";

	        try {
	            connection = dataSource.getConnection();
	            pStmt = connection.prepareStatement(sql);
	            pStmt.setString(1, shopItem.getName());
	            pStmt.setString(2, shopItem.getDescription());
	            pStmt.setString(3, shopItem.getPrice());
	            pStmt.setLong(4, shopItem.getId());

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
	public ShopItems getShopItemById(long id) {
		ShopItems shopItem = null;
		String sql = "SELECT * FROM shopitems WHERE id = ?";

		try {
			connection = dataSource.getConnection();
			pStmt = connection.prepareStatement(sql);
			pStmt.setLong(1, id);
			rs = pStmt.executeQuery();

			if (rs.next()) {
				shopItem = new ShopItems(rs.getLong("id"), rs.getString("name"), rs.getString("price"),
						rs.getString("description"), rs.getString("image")// Assuming create_date is of type DATE
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, pStmt, rs);
		}

		return shopItem;
	}
	public boolean deleteShopItemById(Long id) {
	    String sql = "UPDATE shopitems SET delete_date = NOW() WHERE id = ?";
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
