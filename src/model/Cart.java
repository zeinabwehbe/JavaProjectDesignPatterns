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
    private final List<ProductData> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(ProductData product) {
        items.add(product);
    }

    public List<ProductData> getCartItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (ProductData product : items) {
            total += product.getPrice();
        }
        return total;
    }
}
