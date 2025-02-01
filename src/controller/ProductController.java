package controller;

import factory.ProductFactory;
import model.ProductData;
import model.Cart;
import strategy.PricingStrategy;

import java.util.List;
import java.util.function.Consumer;

public class ProductController {
    private final Cart cart;
    private Consumer<List<ProductData>> cartUpdateListener;

    public ProductController(Cart cart) {
        this.cart = cart;
    }

    // Attach a listener that the view can use to get updates when the cart changes
    public void setCartUpdateListener(Consumer<List<ProductData>> listener) {
        this.cartUpdateListener = listener;
    }

    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        cart.addProduct(product); // Add the product to the cart

        // Notify the view about the updated cart
        if (cartUpdateListener != null) {
            cartUpdateListener.accept(cart.getCartItems());
        }
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }
}
