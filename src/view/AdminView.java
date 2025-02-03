package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminView {
    private JPanel panel;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField productStatusField;
    private JTextField productPriceField;
    private JCheckBox discountCheckBox;
    private JComboBox<String> categoryDropdown;
    private JButton addButton;

    public AdminView() {
        panel = createAdminPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createAdminPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Admin - Add Product"));

        productIdField = new JTextField();
        productNameField = new JTextField();
        productStatusField = new JTextField();
        productPriceField = new JTextField();
        discountCheckBox = new JCheckBox("Apply Discount");

        categoryDropdown = new JComboBox<>(new String[]{"Electronics", "Books", "Clothing", "Home Appliances"});

        addButton = new JButton("Add Product");

        panel.add(new JLabel("Product ID:"));
        panel.add(productIdField);

        panel.add(new JLabel("Category:"));
        panel.add(categoryDropdown);

        panel.add(new JLabel("Product Name:"));
        panel.add(productNameField);

        panel.add(new JLabel("Product Status:"));
        panel.add(productStatusField);

        panel.add(new JLabel("Price:"));
        panel.add(productPriceField);

        panel.add(discountCheckBox);
        panel.add(addButton);

        return panel;
    }

    public void addProductListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public String getProductId() {
        return productIdField.getText();
    }

    public String getProductCategory() {
        return (String) categoryDropdown.getSelectedItem();
    }

    public String getProductName() {
        return productNameField.getText();
    }

    public String getProductStatus() {
        return productStatusField.getText();
    }

    public double getProductPrice() {
        try {
            return Double.parseDouble(productPriceField.getText());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public boolean isDiscountApplied() {
        return discountCheckBox.isSelected();
    }
}
