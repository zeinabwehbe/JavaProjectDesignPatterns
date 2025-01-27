package controller;

import model.Cart;
import model.CustomerData;
import view.CartView;
import model.ProductData;

import java.util.List;

public class CartController {
    private final Cart cart;
    private CartView cartView;

    public CartController(CustomerData customer, Cart cart, CartView cartView) {
        this.cart = cart;
        this.cartView = cartView;
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }

    public double getTotal() {
        return cart.getCartItems().stream().mapToDouble(ProductData::getPrice).sum();
    }

    public void updateCart() {
        if (cartView != null) {
            cartView.updateCartTable();
        }
    }

    public void setCartView(CartView cartView) {
        this.cartView = cartView;
    }
}
