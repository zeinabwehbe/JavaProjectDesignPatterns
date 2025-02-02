package controller;

import factory.ProductFactory;
import model.ProductData;
import model.Cart;
import strategy.PricingStrategy;
import strategy.DiscountPriceStrategy;
import strategy.RegularPriceStrategy;
import view.ProductView;
import view.CartView;

import javax.swing.*;
import java.util.List;
import java.util.function.Consumer;

public class ProductController {
    private final Cart cart;
    private final ProductView productView;
    private Consumer<List<ProductData>> cartUpdateListener;

    public ProductController(Cart cart, ProductView productView, CartView cartView) {
        this.cart = cart;
        this.productView = productView;
        // Added CartView reference
        attachListeners();

        // Ensure cartView updates when the cart changes
        setCartUpdateListener(cartView::updateCartTable);
    }

    private void attachListeners() {
        productView.addProductListener(_ -> handleProductAddition());
    }

    private void handleProductAddition() {
        String productId = productView.getProductId();
        String category = productView.getProductCategory();
        String name = productView.getProductName();
        String status = productView.getProductStatus();
        double price = productView.getProductPrice();

        if (price < 0) {
            JOptionPane.showMessageDialog(null, "Invalid product price!");
            return;
        }

        PricingStrategy pricingStrategy = productView.isDiscountApplied()
                ? new DiscountPriceStrategy(0.1)
                : new RegularPriceStrategy();

        addProduct(productId, category, name, status, price, pricingStrategy);
    }

    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        cart.addProduct(product);

        // Notify the UI that the cart has changed
        if (cartUpdateListener != null) {
            cartUpdateListener.accept(cart.getCartItems());
        }
    }

    public void setCartUpdateListener(Consumer<List<ProductData>> listener) {
        this.cartUpdateListener = listener;
    }
}
