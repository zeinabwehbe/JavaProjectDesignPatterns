package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import model.ProductData;
import controller.CartController;

public class CartView {
    private JPanel panel;
    private DefaultTableModel tableModel;
    private JTable cartTable;
    private JLabel totalLabel;
    private final CartController cartController;

    public CartView(CartController cartController) {
        this.cartController = cartController;
        initializeUI();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeUI() {
        panel = new JPanel(new BorderLayout());

        String[] columnNames = {"Product ID", "Category", "Name", "Status", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 14));
        cartTable.setRowHeight(20);
        cartTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        cartTable.getTableHeader().setBackground(new Color(100, 149, 237));
        cartTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(780, 200));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton checkoutButton = createButton();
        checkoutButton.addActionListener(e -> checkout());
        checkoutPanel.add(checkoutButton);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutPanel.add(totalLabel);

        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(checkoutPanel, BorderLayout.SOUTH);

        updateCartTable();
    }

    private JButton createButton() {
        JButton button = new JButton("Checkout");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    public void updateCartTable() {
        List<ProductData> cartItems = cartController.getCart();
        tableModel.setRowCount(0);

        for (ProductData product : cartItems) {
            Object[] row = {
                    product.getProductId(),
                    product.getProductCategory(),
                    product.getProductName(),
                    product.getProductStatus(),
                    product.getPrice()
            };
            tableModel.addRow(row);
        }

        double total = cartController.getTotal();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

    private void checkout() {
        cartController.updateCart();
    }
}
