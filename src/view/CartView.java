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

        // Initialize the table
        String[] columnNames = {"Product ID", "Category", "Name", "Status", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        cartTable = new JTable(tableModel);
        cartTable.setFont(new Font("Arial", Font.PLAIN, 14));
        cartTable.setRowHeight(20);
        cartTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        cartTable.getTableHeader().setBackground(new Color(100, 149, 237));
        cartTable.getTableHeader().setForeground(Color.WHITE);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(780, 200));

        // Create a panel for the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the checkout button and total label
        JPanel checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton checkoutButton = createButton();
        checkoutButton.addActionListener(e -> checkout());
        checkoutPanel.add(checkoutButton);

        // Initialize the total label to $0.00
        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutPanel.add(totalLabel);

        // Add the table panel and checkout panel to the main panel
        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(checkoutPanel, BorderLayout.SOUTH);
    }

    private JButton createButton() {
        JButton button = new JButton("Checkout");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    private void checkout() {
        // Update the cart table and total when the Checkout button is pressed
        updateCartTable();
        JOptionPane.showMessageDialog(panel, "Checkout completed! Total: $" + String.format("%.2f", cartController.getTotal()));
    }

    public void updateCartTable() {
        // Clear the table
        tableModel.setRowCount(0);

        // Get the cart items from the controller
        List<ProductData> cartItems = cartController.getCart();

        // Add each item to the table
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

        // Update the total label
        double total = cartController.getTotal();
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
}