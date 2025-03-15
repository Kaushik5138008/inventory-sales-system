# Inventory and Sales System  

## Project Overview  
This is a **console-based** Java application that manages inventory and sales operations. The project uses **Oracle 11g Express Edition** as the database backend and follows a modular architecture with different packages handling database operations, business logic, and GUI.

---

## Features  
- **Stock Management:** Insert, delete, and update stock items.  
- **Sales Management:** Record sales transactions and update inventory.  
- **Sales Reports:** View sales reports with profit calculations.  
- **GUI Interface:** User-friendly interface to interact with the system.  

---

## Project Structure  
```
Inventory-Sales-System/
│── src/
│   ├── com.wipro.sales.util/       # Database connection class
│   ├── com.wipro.sales.bean/       # Bean classes (Stock, Sales, SalesReport)
│   ├── com.wipro.sales.dao/        # Data Access Object (DAO) classes
│   ├── com.wipro.sales.service/    # Business logic (Administrator class)
│   ├── com.wipro.sales.main/       # Main application (SalesApplication.java)
│   ├── com.wipro.sales.gui/        # GUI classes (HomePage.java, InsertStockPage.java, etc.)
│── README.md
│── .gitignore
```

---

## Database Setup (Oracle 11g XE)  
### **1. Install Oracle 11g Express Edition**
Download and install Oracle 11g XE from [Oracle's website](https://www.oracle.com/database/technologies/xe-prior-release-downloads.html).

### **2. Open SQL*Plus and Login**
Run the following command to log in:  
```sh
sqlplus system/password
```
*Replace `password` with your actual Oracle password.*

### **3. Create the Database Schema**  
```sql
-- Creating TBL_STOCK table
CREATE TABLE TBL_STOCK (
    Product_ID VARCHAR2(6) PRIMARY KEY,
    Product_Name VARCHAR2(20) UNIQUE,
    Quantity_On_Hand NUMBER CHECK (Quantity_On_Hand >= 0),
    Product_Unit_Price NUMBER CHECK (Product_Unit_Price >= 0),
    Reorder_Level NUMBER CHECK (Reorder_Level >= 0)
);

-- Creating TBL_SALES table
CREATE TABLE TBL_SALES (
    Sales_ID VARCHAR2(6) PRIMARY KEY,
    Sales_Date DATE,
    Product_ID VARCHAR2(6),
    Quantity_Sold NUMBER CHECK (Quantity_Sold >= 0),
    Sales_Price_Per_Unit NUMBER CHECK (Sales_Price_Per_Unit >= 0),
    CONSTRAINT fk_product FOREIGN KEY (Product_ID) REFERENCES TBL_STOCK(Product_ID)
);
```

### **4. Insert Sample Data**
```sql
INSERT INTO TBL_STOCK (Product_ID, Product_Name, Quantity_On_Hand, Product_Unit_Price, Reorder_Level) 
VALUES ('RE1001', 'REDMI Note 3', 20, 12000, 5);

INSERT INTO TBL_STOCK (Product_ID, Product_Name, Quantity_On_Hand, Product_Unit_Price, Reorder_Level) 
VALUES ('ip1002', 'Iphone 5S', 10, 21000, 2);

INSERT INTO TBL_STOCK (Product_ID, Product_Name, Quantity_On_Hand, Product_Unit_Price, Reorder_Level) 
VALUES ('PA1003', 'Panasonic P55', 50, 5500, 5);
```

### **5. Create Sequences**
```sql
-- Sequence for Sales_ID
CREATE SEQUENCE SEQ_SALES_ID START WITH 1000 INCREMENT BY 1;

-- Sequence for Product_ID
CREATE SEQUENCE SEQ_PRODUCT_ID START WITH 1004 INCREMENT BY 1;
```

### **6. Create View for Sales Report**
```sql
CREATE VIEW V_SALES_REPORT AS
SELECT 
    S.Sales_ID,
    S.Sales_Date,
    S.Product_ID,
    ST.Product_Name,
    S.Quantity_Sold,
    ST.Product_Unit_Price,
    S.Sales_Price_Per_Unit,
    (S.Sales_Price_Per_Unit - ST.Product_Unit_Price) AS Profit_Amount
FROM TBL_SALES S
JOIN TBL_STOCK ST ON S.Product_ID = ST.Product_ID
ORDER BY Profit_Amount DESC, S.Sales_ID ASC;
```

---

## Running the Java Application  
### **1. Compile and Run in Console Mode**  
```sh
javac -d bin src/com/wipro/sales/main/SalesApplication.java
java -cp bin com.wipro.sales.main.SalesApplication
```

### **2. Run in GUI Mode**  
```sh
javac -d bin src/com/wipro/sales/gui/HomePage.java
java -cp bin com.wipro.sales.gui.HomePage
```

---

## GUI Screens  
The project includes the following GUI screens under `com.wipro.sales.gui`:  
- **HomePage.java** – Main dashboard  
- **InsertStockPage.java** – Add new stock items  
- **InsertSalesPage.java** – Record a sale  
- **DeleteStockPage.java** – Remove stock items  
- **SalesReportPage.java** – View sales reports  

---

## Contributing  
If you'd like to contribute:  
1. Fork the repository.  
2. Create a new branch: `git checkout -b feature-branch`  
3. Commit changes: `git commit -m "Added new feature"`  
4. Push to GitHub: `git push origin feature-branch`  
5. Open a pull request.  

---

## License  
This project is for educational purposes. Feel free to use and modify it.  

---

### **Author**  
Developed by **Team Flames** for the **Inventory and Sales System** project.  
```
