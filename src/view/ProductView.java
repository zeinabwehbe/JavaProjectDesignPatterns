package view;

import model.ProductData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView {
    private final JPanel panel;
    private final DefaultListModel<String> productListModel;
    private final JList<String> productList;
    private final JButton addToCartButton;
    private List<ProductData> products; // Store actual product data

    public ProductView() {
        panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Available Products"));

        productListModel = new DefaultListModel<>();
        productList = new JList<>(productListModel);
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(productList);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.setEnabled(false); // Initially disabled

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(addToCartButton, BorderLayout.SOUTH);

        // Enable button when a product is selected
        productList.addListSelectionListener(e -> addToCartButton.setEnabled(!productList.isSelectionEmpty()));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void updateProductList(List<ProductData> products) {
        this.products = products; // Store product objects
        productListModel.clear();
        for (ProductData product : products) {
            productListModel.addElement(product.getName() + " - $" + product.getPrice());
        }
    }

    public ProductData getSelectedProduct() {
        int selectedIndex = productList.getSelectedIndex();
        return (selectedIndex != -1) ? products.get(selectedIndex) : null;
    }

    public void addAddToCartListener(ActionListener listener) {
        addToCartButton.addActionListener(listener);
    }
}
