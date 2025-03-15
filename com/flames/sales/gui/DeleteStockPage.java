
import javax.swing.*;
import java.awt.*;
import com.wipro.sales.service.Administrator;

public class DeleteStockPage extends JFrame {
    private JTextField productIdField;

    public DeleteStockPage() {
        setTitle("Delete Stock");
        setSize(300, 150);
        setLayout(new GridLayout(2, 2));

        add(new JLabel("Product ID:"));
        productIdField = new JTextField();
        add(productIdField);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.addActionListener(e -> deleteStock());
        add(deleteBtn);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void deleteStock() {
        String productId = productIdField.getText();
        Administrator admin = new Administrator();
        String result = admin.deleteStock(productId);
        JOptionPane.showMessageDialog(this, result);
    }
}
