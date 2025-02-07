package controller;

import model.Cart;
import model.ProductData;
import view.CartView;

import java.util.List;

/**
 * Controller for managing the Cart operations such as getting cart items,
 * calculating the total price, and updating the CartView with current cart data.
 */
public class CartController {
    private final Cart cart;

    /**
     * Constructor for CartController that initializes the Cart object.
     *
     * @param cart The Cart object that holds all the products added by the user.
     */
    public CartController(Cart cart) {
        this.cart = cart;
    }

    /**
     * Returns the list of items in the cart; The class uses List<ProductData> to represent the cart items.
     * This collection is returned by the getCart() method and passed to the CartView for display.
     *
     * @return A list of ProductData objects representing the products in the cart.
     */
    public List<ProductData> getCart() {
        return cart.getCartItems();  // Retrieves the list of cart items from the Cart model.
    }

    /**
     * Calculates the total price of all the products currently in the cart.
     *
     * @return The total price of the products in the cart.
     */
    public double getTotal() {
        double total = 0.0;

        // Loop through each product in the cart and add its price to the total
        for (ProductData product : cart.getCartItems()) {
            total += product.getPrice();  // Add product price to total
        }

        return total;  // Return the total price of the cart
    }

    /**
     * Updates the CartView with the current list of cart items and the total price.
     *
     * @param cartView The CartView object that displays cart details to the user.
     */
    public void updateCartTable(CartView cartView) {
        cartView.updateCartTable(getCart());  // Pass the cart items to the view
        cartView.updateTotalLabel(getTotal()); // Update total price in the view
    }
}
