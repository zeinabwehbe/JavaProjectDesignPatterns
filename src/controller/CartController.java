package controller;

import model.Cart;
import model.CustomerData;
import model.ProductData;

import java.util.List;

public class CartController {
    private final Cart cart;

    public CartController(CustomerData customer) {
        this.cart = new Cart();
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }

    public double getTotal() {
        return cart.getCartItems().stream().mapToDouble(ProductData::getPrice).sum();
    }
}
