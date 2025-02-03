package controller;

import factory.ProductFactory;
import model.ProductData;
import model.Cart;
import strategy.PricingStrategy;
import strategy.DiscountPriceStrategy;
import strategy.RegularPriceStrategy;
import view.AdminView;
import view.ProductView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ProductController {
    private final Cart cart;
    private final ProductView productView;
    private final AdminView adminView;
    private final List<ProductData> products; // Store all products
    private Consumer<List<ProductData>> productUpdateListener;

    public ProductController(Cart cart, ProductView productView, AdminView adminView) {
        this.cart = cart;
        this.productView = productView;
        this.adminView = adminView;
        this.products = new ArrayList<>();
        attachListeners();
    }

    private void attachListeners() {
        // Listener for adding new products from AdminView
        adminView.addProductListener(e -> handleProductAddition());

        // Listener for adding a selected product to the cart
        productView.addAddToCartListener(e -> handleAddToCart());
    }

    private void handleProductAddition() {
        String productId = adminView.getProductId();
        String category = adminView.getProductCategory();
        String name = adminView.getProductName();
        String status = adminView.getProductStatus();
        double price = adminView.getProductPrice();

        if (price < 0) {
            return;
        }

        PricingStrategy pricingStrategy = adminView.isDiscountApplied()
                ? new DiscountPriceStrategy(0.1)
                : new RegularPriceStrategy();

        addProduct(productId, category, name, status, price, pricingStrategy);
    }

    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        products.add(product); // Add to the product list

        if (productUpdateListener != null) {
            productUpdateListener.accept(products); // Update ProductView
        }
    }

    private void handleAddToCart() {
        ProductData selectedProduct = productView.getSelectedProduct();
        if (selectedProduct != null) {
            cart.addProduct(selectedProduct);
            JOptionPane.showMessageDialog(null, selectedProduct.getName() + " added to cart!");
        }
    }

    public void setProductUpdateListener(Consumer<List<ProductData>> listener) {
        this.productUpdateListener = listener;
    }
}
