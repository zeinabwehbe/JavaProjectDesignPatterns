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

/**
 * Controller responsible for managing products, including adding products,
 * applying pricing strategies, and adding products to the shopping cart.
 */
public class ProductController {
    private final Cart cart;  // The cart where products are added
    private final ProductView productView;  // View for displaying products
    private final AdminView adminView;  // Admin view for managing product data
    private final List<ProductData> products;  // A List (products) is used to store the product data.
    private Consumer<List<ProductData>> productUpdateListener;  // Listener to update product view

    /**
     * Constructs a ProductController with the specified Cart, ProductView, and AdminView.
     * Initializes the product list and attaches event listeners for user interactions.
     *
     * @param cart        The cart where products can be added.
     * @param productView The view that displays the products.
     * @param adminView   The admin view for managing products.
     */
    public ProductController(Cart cart, ProductView productView, AdminView adminView) {
        this.cart = cart;
        this.productView = productView;
        this.adminView = adminView;
        this.products = new ArrayList<>();
        attachListeners();  // Attach event listeners to handle product-related actions
    }

    /**
     * Attaches listeners for product addition and cart actions.
     * Uses lambda functions to define actions for button clicks.
     */
    private void attachListeners() {
        //  lambda expression calls handleProductAddition() when a product is added from the admin view.
        adminView.addProductListener(e -> handleProductAddition());

        // lambda expression calls handleAddToCart() when a product is added to the cart.
        productView.addAddToCartListener(e -> handleAddToCart());
    }

    /**
     * Handles the process of adding a new product.
     * Collects product data from the admin view, applies pricing strategy,
     * and then adds the product to the list.
     */
    private void handleProductAddition() {
        String productId = adminView.getProductId();
        String category = adminView.getProductCategory();
        String name = adminView.getProductName();
        String status = adminView.getProductStatus();
        double price = adminView.getProductPrice();

        // Ensure the price is valid
        if (price < 0) {
            return;
        }

        // Choose pricing strategy based on whether a discount is applied
        PricingStrategy pricingStrategy = adminView.isDiscountApplied()
                ? new DiscountPriceStrategy(0.1)  // 10% discount
                : new RegularPriceStrategy();

        // Add the product to the list
        addProduct(productId, category, name, status, price, pricingStrategy);
    }

    /**
     * Adds a new product to the product list and updates the view if necessary.
     *
     * @param productId      The product's ID.
     * @param category       The product's category.
     * @param name           The product's name.
     * @param status         The product's status.
     * @param price          The product's price.
     * @param pricingStrategy The pricing strategy applied to the product.
     */
    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        // Create a new ProductData object using the factory method
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        products.add(product);  // Add product to the list

        // Notify the listener to update the product view
        if (productUpdateListener != null) {
            productUpdateListener.accept(products);  // Update the product view with the new list of products
        }
    }

    /**
     * Handles the process of adding a selected product to the shopping cart.
     * Displays a message when the product is successfully added.
     */
    private void handleAddToCart() {
        ProductData selectedProduct = productView.getSelectedProduct();
        if (selectedProduct != null) {
            cart.addProduct(selectedProduct);  // Add the product to the cart
            // Show a confirmation message
            JOptionPane.showMessageDialog(null, selectedProduct.getName() + " added to cart!");
        }
    }

    /**
     * Sets a listener to update the product view whenever the product list is updated.
     *
     * @param listener The listener to be called when the product list changes.
     */
    public void setProductUpdateListener(Consumer<List<ProductData>> listener) {
        this.productUpdateListener = listener;
    }
}
