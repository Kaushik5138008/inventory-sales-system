import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import com.wipro.sales.service.Administrator;
import com.wipro.sales.bean.SalesReport;

public class SalesReportPage extends JFrame {
    public SalesReportPage() {
        setTitle("Sales Report");
        setSize(600, 400);
        setLayout(new BorderLayout());

        String[] columnNames = {"Sales ID", "Date", "Product ID", "Product Name", "Qty Sold", "Unit Price", "Sales Price", "Profit"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        Administrator admin = new Administrator();
        ArrayList<SalesReport> reports = admin.getSalesReport();

        for (SalesReport report : reports) {
            Object[] row = {
                report.getSalesID(), report.getSalesDate(), report.getProductID(),
                report.getProductName(), report.getQuantitySold(), report.getProductUnitPrice(),
                report.getSalesPricePerUnit(), report.getProfitAmount()
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
