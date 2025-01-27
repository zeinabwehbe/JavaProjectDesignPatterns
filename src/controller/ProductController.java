package controller;

import factory.ProductFactory;
import model.ProductData;
import model.Cart;
import strategy.PricingStrategy;

import java.util.List;

public class ProductController {
    private final List<ProductData> cart;

    public ProductController(List<ProductData> cart) {
        this.cart = cart;
    }

    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        cart.addLast(product); // Add the product to the cart
    }
}
