package com.wipro.sales.dao;

import com.wipro.sales.bean.Product;
import com.wipro.sales.util.DBUtil;

import java.sql.*;

public class StockDao {

    public int insertStock(Product product) {
        int rows = 0;
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "INSERT INTO TBL_STOCK (Product_ID, Product_Name, Quantity_On_Hand, Product_Unit_Price, Reorder_Level) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, product.getProductID());
            ps.setString(2, product.getProductName());
            ps.setInt(3, product.getQuantityOnHand());
            ps.setDouble(4, product.getProductUnitPrice());
            ps.setInt(5, product.getReorderLevel());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public String generateProductID(String productName) {
        String productID = "";
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "SELECT SEQ_PRODUCT_ID.NEXTVAL FROM DUAL";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int seqID = rs.getInt(1);
                String namePart = productName.substring(0, 2).toUpperCase();
                productID = namePart + seqID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productID;
    }

    public int updateStock(String productID, int soldQty) {
        int rows = 0;
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "UPDATE TBL_STOCK SET Quantity_On_Hand = Quantity_On_Hand - ? WHERE Product_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, soldQty);
            ps.setString(2, productID);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public Product getStock(String productID) {
        Product product = null;
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "SELECT * FROM TBL_STOCK WHERE Product_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, productID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setProductID(rs.getString("Product_ID"));
                product.setProductName(rs.getString("Product_Name"));
                product.setQuantityOnHand(rs.getInt("Quantity_On_Hand"));
                product.setProductUnitPrice(rs.getDouble("Product_Unit_Price"));
                product.setReorderLevel(rs.getInt("Reorder_Level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public int deleteStock(String productID) {
        int rows = 0;
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "DELETE FROM TBL_STOCK WHERE Product_ID = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, productID);
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
