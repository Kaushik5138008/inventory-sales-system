package com.wipro.sales.main;

import com.wipro.sales.bean.Product;
import com.wipro.sales.bean.Sales;
import com.wipro.sales.bean.SalesReport;
import com.wipro.sales.service.Administrator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SalesApplication {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Administrator admin = new Administrator();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("Main Menu:");
            System.out.println("1. Insert Stock");
            System.out.println("2. Delete Stock");
            System.out.println("3. Insert Sales");
            System.out.println("4. View Sales Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter Stock details:");
                    Product stock = new Product();
                    System.out.print("Product Name: ");
                    stock.setProductName(scanner.nextLine());
                    System.out.print("Quantity On Hand: ");
                    stock.setQuantityOnHand(scanner.nextInt());
                    System.out.print("Product Unit Price: ");
                    stock.setProductUnitPrice(scanner.nextDouble());
                    System.out.print("Reorder Level: ");
                    stock.setReorderLevel(scanner.nextInt());
                    scanner.nextLine(); // consume newline

                    String result = admin.insertStock(stock);
                    System.out.println(result);
                    break;

                case 2:
                    System.out.print("Enter Product ID to delete: ");
                    String productIdToDelete = scanner.nextLine();
                    String deleteResult = admin.deleteStock(productIdToDelete);
                    System.out.println(deleteResult);
                    break;

                case 3:
                    System.out.println("Enter Sales details:");
                    Sales sales = new Sales();
                    System.out.print("Product ID: ");
                    sales.setProductID(scanner.nextLine());
                    System.out.print("Quantity Sold: ");
                    sales.setQuantitySold(scanner.nextInt());
                    System.out.print("Sales Price Per Unit: ");
                    sales.setSalesPricePerUnit(scanner.nextDouble());
                    scanner.nextLine(); // consume newline
                    System.out.print("Sales Date (yyyy-MM-dd): ");
                    String salesDateStr = scanner.nextLine();

                    try {
                        Date salesDate = sdf.parse(salesDateStr);
                        sales.setSalesDate(salesDate);
                        String salesResult = admin.insertSales(sales);
                        System.out.println(salesResult);
                    } catch (ParseException e) {
                        System.out.println("Invalid date format");
                    }
                    break;

                case 4:
                    ArrayList<SalesReport> salesReports = admin.getSalesReport();
                    for (SalesReport report : salesReports) {
                        System.out.println("Sales ID: " + report.getSalesID());
                        System.out.println("Sales Date: " + sdf.format(report.getSalesDate()));
                        System.out.println("Product ID: " + report.getProductID());
                        System.out.println("Product Name: " + report.getProductName());
                        System.out.println("Quantity Sold: " + report.getQuantitySold());
                        System.out.println("Product Unit Price: " + report.getProductUnitPrice());
                        System.out.println("Sales Price Per Unit: " + report.getSalesPricePerUnit());
                        System.out.println("Profit Amount: " + report.getProfitAmount());
                        System.out.println("----------");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
