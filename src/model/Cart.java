package model;

import java.util.ArrayList;
import java.util.List;

/*
* public class Cart {
    private List<ProductData> cartItems;

    public Cart() {
        // ........................
    }

    public void addToCart(ProductData product) {
        // ..................................
    }

    public ....................... getCartItems() {
        return cartItems;
    }
}
*/
/**
 * Cart class that represents a shopping cart for products.
 */
public class Cart {
    private final List<ProductData> items;

    /**
     * Initializes an empty shopping cart.
     */
    public Cart() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a product to the cart.
     *
     * @param product The product to be added.
     */
    public void addProduct(ProductData product) {
        items.add(product);
    }

    /**
     * Returns the list of products currently in the cart.
     *
     * @return A list of ProductData objects representing the products in the cart.
     */
    public List<ProductData> getCartItems() {
        return items;
    }

    /**
     * Calculates the total price of the products in the cart.
     * Uses a lambda function to calculate the sum of product prices.
     *
     * @return The total price of the products in the cart.
     */
    public double calculateTotal() {
        return items.stream()
                .mapToDouble(ProductData::getPrice)
                .sum();
    }
}