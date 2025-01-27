package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<ProductData> cartItems;

    public Cart() {
        cartItems = new ArrayList<>();
    }

    public void addProduct(ProductData product) {
        cartItems.add(product);
    }

    public List<ProductData> getCartItems() {
        return cartItems;
    }
}
