import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.wipro.sales.service.Administrator;
import com.wipro.sales.bean.Product;

public class InsertStockPage extends JFrame {
    private JTextField productNameField, quantityField, priceField, reorderField;

    public InsertStockPage() {
        setTitle("Insert Stock");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Product Name:"));
        productNameField = new JTextField();
        add(productNameField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Reorder Level:"));
        reorderField = new JTextField();
        add(reorderField);

        JButton submitBtn = new JButton("Insert");
        submitBtn.addActionListener(e -> insertStock());
        add(submitBtn);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void insertStock() {
        String name = productNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());
        int reorder = Integer.parseInt(reorderField.getText());

        Product product = new Product();
        product.setProductName(name);
        product.setQuantityOnHand(quantity);
        product.setProductUnitPrice(price);
        product.setReorderLevel(reorder);

        Administrator admin = new Administrator();
        String result = admin.insertStock(product);
        JOptionPane.showMessageDialog(this, result);
    }
}
