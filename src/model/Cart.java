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
