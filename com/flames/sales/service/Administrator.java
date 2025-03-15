package com.wipro.sales.service;

import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.dao.SalesDao;
import com.wipro.sales.dao.StockDao;

import java.util.ArrayList;

public class Administrator {

    private StockDao stockDao = new StockDao();
    private SalesDao salesDao = new SalesDao();

    public String insertStock(Product stockObj) {
        if (stockObj == null || stockObj.getProductName().length() < 2) {
            return "Data not Valid for insertion";
        }
        String productID = stockDao.generateProductID(stockObj.getProductName());
        stockObj.setProductID(productID);
        int rows = stockDao.insertStock(stockObj);
        return (rows > 0) ? productID : "Error";
    }

    public String deleteStock(String productID) {
        int rows = stockDao.deleteStock(productID);
        return (rows > 0) ? "deleted" : "record cannot be deleted";
    }

    public String insertSales(Sales salesObj) {
        if (salesObj == null) {
            return "Object not valid for insertion";
        }
        Product product = stockDao.getStock(salesObj.getProductID());
        if (product == null) {
            return "Unknown Product for sales";
        }
        if (product.getQuantityOnHand() < salesObj.getQuantitySold()) {
            return "Not enough stock on hand for sales";
        }
        if (salesObj.getSalesDate().after(new java.util.Date())) {
            return "Invalid date";
        }
        String salesID = salesDao.generateSalesID(salesObj.getSalesDate());
        salesObj.setSalesID(salesID);
        int rows = salesDao.insertSales(salesObj);
        if (rows > 0) {
            stockDao.updateStock(salesObj.getProductID(), salesObj.getQuantitySold());
            return "Sales Completed";
        } else {
            return "Error";
        }
    }

    public ArrayList<SalesReport> getSalesReport() {
        return salesDao.getSalesReport();
    }
}
