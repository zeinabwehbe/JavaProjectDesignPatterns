package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.ProductController;
import strategy.DiscountPriceStrategy;
import strategy.PricingStrategy;
import strategy.RegularPriceStrategy;

public class ProductView {
    private JPanel panel;
    private ProductController productController;
    private final CartView cartView;

    public ProductView(ProductController productController, CartView cartView) {
        this.productController = productController;
        this.cartView = cartView;
        panel = createProductFormPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createProductFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField productIdField = new JTextField();
        JTextField productCategoryField = new JTextField();
        JTextField productNameField = new JTextField();
        JTextField productStatusField = new JTextField();
        JTextField productPriceField = new JTextField();
        JCheckBox discountCheckBox = new JCheckBox("Apply Discount");

        panel.add(createLabel("Product ID:"));
        panel.add(productIdField);

        panel.add(createLabel("Product Category:"));
        panel.add(productCategoryField);

        panel.add(createLabel("Product Name:"));
        panel.add(productNameField);

        panel.add(createLabel("Product Status:"));
        panel.add(productStatusField);

        panel.add(createLabel("Product Price:"));
        panel.add(productPriceField);

        panel.add(new JLabel(""));
        panel.add(discountCheckBox);

        JButton addButton = createButton();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = productIdField.getText();
                String productCategory = productCategoryField.getText();
                String productName = productNameField.getText();
                String productStatus = productStatusField.getText();

                try {
                    double productPrice = Double.parseDouble(productPriceField.getText());
                    PricingStrategy pricingStrategy = discountCheckBox.isSelected()
                            ? new DiscountPriceStrategy(0.1)
                            : new RegularPriceStrategy();

                    productController.addProduct(
                            productId,
                            productCategory,
                            productName,
                            productStatus,
                            productPrice,
                            pricingStrategy
                    );

                    cartView.updateCartTable(); // Refresh the cart view to display the added product
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(panel, "Invalid product price!");
                }
            }
        });
        panel.add(addButton);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createButton() {
        JButton button = new JButton("Add Product");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public void updateCartTable() {
        cartView.updateCartTable();
    }
}
