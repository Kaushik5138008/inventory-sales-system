import javax.swing.*;
import java.awt.*;
import com.wipro.sales.service.Administrator;
import com.wipro.sales.bean.Sales;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertSalesPage extends JFrame {
    private JTextField productIdField, quantityField, priceField, dateField;

    public InsertSalesPage() {
        setTitle("Insert Sales");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        add(productIdField);

        add(new JLabel("Quantity Sold:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Sales Price Per Unit:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Sales Date (yyyy-MM-dd):"));
        dateField = new JTextField();
        add(dateField);

        JButton submitBtn = new JButton("Insert Sale");
        submitBtn.addActionListener(e -> insertSale());
        add(submitBtn);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void insertSale() {
        try {
            String productId = productIdField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());
            Date salesDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateField.getText());

            Sales sale = new Sales();
            sale.setProductID(productId);
            sale.setQuantitySold(quantity);
            sale.setSalesPricePerUnit(price);
            sale.setSalesDate(salesDate);

            Administrator admin = new Administrator();
            String result = admin.insertSales(sale);
            JOptionPane.showMessageDialog(this, result);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}
