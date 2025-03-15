import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Inventory & Sales Management");
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        JButton insertStockBtn = new JButton("Insert Stock");
        JButton deleteStockBtn = new JButton("Delete Stock");
        JButton insertSalesBtn = new JButton("Insert Sales");
        JButton viewReportBtn = new JButton("View Sales Report");

        insertStockBtn.addActionListener(e -> new InsertStockPage());
        deleteStockBtn.addActionListener(e -> new DeleteStockPage());
        insertSalesBtn.addActionListener(e -> new InsertSalesPage());
        viewReportBtn.addActionListener(e -> new SalesReportPage());

        add(insertStockBtn);
        add(deleteStockBtn);
        add(insertSalesBtn);
        add(viewReportBtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }
}
