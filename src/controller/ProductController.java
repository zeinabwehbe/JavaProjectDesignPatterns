package controller;

import factory.ProductFactory;
import model.ProductData;
import model.Cart;
import strategy.PricingStrategy;
import view.ProductView;

import java.util.List;

public class ProductController {
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final Cart cart;
    private final ProductView productView;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------

    public ProductController(Cart cart, ProductView productView) {
        this.cart = cart;
        this.productView = productView;
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public void addProduct(String productId, String category, String name, String status, double price, PricingStrategy pricingStrategy) {
        ProductData product = ProductFactory.createProduct(productId, category, name, status, null, price, pricingStrategy);
        cart.addProduct(product); // Add the product to the cart
        productView.updateCartTable(); // Refresh the cart view to display the added product
    }

    public List<ProductData> getCart() {
        return cart.getCartItems();
    }
}
