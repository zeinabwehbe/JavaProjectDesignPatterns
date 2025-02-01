package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView {
    private JPanel panel;
    private JTextField productIdField;
    private JTextField productNameField;
    private JTextField productStatusField;
    private JTextField productPriceField;
    private JCheckBox discountCheckBox;
    private JComboBox<String> categoryDropdown;
    private JButton addButton;

    public ProductView() {
        panel = createProductFormPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createProductFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        productIdField = new JTextField();
        productNameField = new JTextField();
        productStatusField = new JTextField();
        productPriceField = new JTextField();
        discountCheckBox = new JCheckBox("Apply Discount");

        panel.add(createLabel("Product ID:"));
        panel.add(productIdField);

        panel.add(createLabel("Product Category:"));
        categoryDropdown = new JComboBox<>();
        panel.add(categoryDropdown);

        panel.add(createLabel("Product Name:"));
        panel.add(productNameField);

        panel.add(createLabel("Product Status:"));
        panel.add(productStatusField);

        panel.add(createLabel("Product Price:"));
        panel.add(productPriceField);

        panel.add(new JLabel(""));  // Empty space for layout
        panel.add(discountCheckBox);

        addButton = createButton("Add Product");
        panel.add(addButton);

        // Populate the dropdown with static data
        updateCategoryDropdown();

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    // Method to populate the category dropdown with static data
    public void updateCategoryDropdown() {
        categoryDropdown.removeAllItems();

        // Static category data
        String[] categories = {"Electronics", "Books", "Clothing", "Home Appliances"};

        for (String category : categories) {
            categoryDropdown.addItem(category);
        }
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
