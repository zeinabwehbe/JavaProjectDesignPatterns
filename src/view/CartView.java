package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import model.Cart;
import model.ProductData;
import controller.CartController;

public class CartView {
    private JPanel panel;
    private DefaultTableModel tableModel;
    private JTable cartTable;
    private JLabel totalLabel;
    private CartController cartController;

    // Constructor
    public CartView() {
        initializeUI();
    }

    // Setter to inject CartController, ONLY TO BE ABLE TO PASS TOTAL
    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }

    public JPanel getPanel() {
        return panel;
    }

    // Initialize UI components
    private void initializeUI() {
        panel = new JPanel(new BorderLayout());
        tableModel = createTableModel();
        cartTable = createCartTable();

        JScrollPane scrollPane = new JScrollPane(cartTable);
        scrollPane.setPreferredSize(new Dimension(780, 200));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel checkoutPanel = createCheckoutPanel();

        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(checkoutPanel, BorderLayout.SOUTH);
    }

    // Creates the table model for the cart
    private DefaultTableModel createTableModel() {
        String[] columnNames = {"Product ID", "Category", "Name", "Status", "Price"};
        return new DefaultTableModel(columnNames, 0);
    }

    // Creates and configures the JTable for displaying cart items
    private JTable createCartTable() {
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(100, 149, 237));
        table.getTableHeader().setForeground(Color.WHITE);
        return table;
    }

    private JPanel createCheckoutPanel() {
        JPanel checkoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton checkoutButton = createButton("Checkout", e -> checkout());
        checkoutPanel.add(checkoutButton);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        checkoutPanel.add(totalLabel);

        return checkoutPanel;
    }

    private void checkout() {
        // Ensure the cartController is not null before using it
        if (cartController != null) {
            cartController.updateCartTable(this);
        } else {
            System.out.println("Error: CartController is null");
        }
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(actionListener);
        return button;
    }


    public void updateCartTable(List<ProductData> cartItems) {
        tableModel.setRowCount(0); // Clear the table

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
    }

    // Method to update the total label in the CartView
    public void updateTotalLabel(double total) {
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }

}
