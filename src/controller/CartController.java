package controller;

import model.Cart;
import model.CustomerData;
import view.CartView;
import model.ProductData;

import java.util.List;

public class CartController {
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final Cart cart;
    private CartView cartView;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public CartController(CustomerData customer, Cart cart, CartView cartView) {
        this.cart = cart;
        this.cartView = cartView;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------


    public void addProduct(ProductData product) {
        cart.addProduct(product);
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }

    public double getTotal() {
        return cart.calculateTotal();
    }

    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }

    public void updateCart() {
        // This method can be used to notify the view of changes (if needed)
        if (cartView != null) {
            cartView.updateCartTable();
        }
    }
}
