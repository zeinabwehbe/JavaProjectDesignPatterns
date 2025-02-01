package controller;

import model.Cart;
import model.ProductData;
import view.CartView; // Make sure CartView is imported

import java.util.List;

public class CartController {
    private final Cart cart;

    // Constructor doesn't require CartView parameter now
    public CartController(Cart cart) {
        this.cart = cart;
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }

    public double getTotal() {

            double total = 0.0;
            for (ProductData product : cart.getCartItems()) {
                total += product.getPrice();
            }
            return total;
    }

    // Method to update CartView's table and total label
    public void updateCartTable(CartView cartView) {
        cartView.updateCartTable(getCart());  // Pass the cart items to the view
        cartView.updateTotalLabel(getTotal()); // Update total in the view
    }
}
