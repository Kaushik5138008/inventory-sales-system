package com.wipro.sales.dao;

import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class SalesDao {

    public int insertSales(Sales sales) {
        int rows = 0;
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "INSERT INTO TBL_SALES (Sales_ID, Sales_Date, Product_ID, Quantity_Sold, Sales_Price_Per_Unit) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, sales.getSalesID());
            ps.setDate(2, new java.sql.Date(sales.getSalesDate().getTime()));
            ps.setString(3, sales.getProductID());
            ps.setInt(4, sales.getQuantitySold());
            ps.setDouble(5, sales.getSalesPricePerUnit());
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public String generateSalesID(java.util.Date salesDate) {
        String salesID = "";
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "SELECT SEQ_SALES_ID.NEXTVAL FROM DUAL";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int seqID = rs.getInt(1);
                String yearPart = String.valueOf(salesDate.getYear()).substring(1);
                salesID = yearPart + seqID;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesID;
    }

    public ArrayList<SalesReport> getSalesReport() {
        ArrayList<SalesReport> salesReports = new ArrayList<>();
        Connection connection = DBUtil.getDBConnection();
        try {
            String query = "SELECT * FROM V_SALES_REPORT";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SalesReport sr = new SalesReport();
                sr.setSalesID(rs.getString("Sales_ID"));
                sr.setSalesDate(rs.getDate("Sales_Date"));
                sr.setProductID(rs.getString("Product_ID"));
                sr.setProductName(rs.getString("Product_Name"));
                sr.setQuantitySold(rs.getInt("Quantity_Sold"));
                sr.setProductUnitPrice(rs.getDouble("Product_Unit_Price"));
                sr.setSalesPricePerUnit(rs.getDouble("Sales_Price_Per_Unit"));
                sr.setProfitAmount(rs.getDouble("Profit_Amount"));
                salesReports.add(sr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesReports;
    }
}
