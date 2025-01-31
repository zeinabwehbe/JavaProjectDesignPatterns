package controller;

import model.CustomerData;
import model.Cart;
import view.CartView;
import view.CustomerView;
import view.ProductView;
import view.CategoryView;
import view.ShoppingApplicationView;

import javax.swing.*;
import java.util.Date;

public class ShoppingApplicationController {
    private final ShoppingApplicationView shoppingAppView;
    private CartController cartController;
    private ProductController productController;
    private final CustomerController customerController;
    private final CategoryController categoryController;
    private boolean isCustomerInfoSubmitted = false;

    public ShoppingApplicationController() {
        // Initialize the main view
        shoppingAppView = new ShoppingApplicationView();

        // Initialize CustomerData using the Builder Pattern
        CustomerData customerData = new CustomerData.CustomerDataBuilder()
                .setCustomerUsername("") // Initialize with empty or default values
                .setCustomerPassword("")
                .setCustomerGender("")
                .setEmail("")
                .setPhoneNumber("")
                .setDate(new Date()) // Set the current date as default
                .build();

        // Initialize CustomerView and CustomerController
        CustomerView customerView = new CustomerView();
        customerController = new CustomerController(customerData, customerView);

        // Initialize CategoryController with static categories
        categoryController = new CategoryController();
        CategoryView categoryView = new CategoryView(categoryController);
        categoryController.setCategoryView(categoryView);

        // Set the initial view to the customer form
        shoppingAppView.initializeCustomerView(customerView.getPanel());

        // Add action listener for the continue button
        shoppingAppView.getContinueButton().addActionListener(e -> {
            if (!isCustomerInfoSubmitted) {
                submitCustomerData();
            }
        });

        shoppingAppView.setVisible(true);
    }

    private void submitCustomerData() {
        // Validate customer data
        CustomerData customer = customerController.getCustomer();
        if (customer.getCustomer_username() == null || customer.getCustomer_password() == null ||
                customer.getCustomer_gender() == null || customer.getEmail() == null || customer.getPhoneNumber() == null) {
            JOptionPane.showMessageDialog(shoppingAppView, "All fields must be filled out!");
            return;
        }

        // Initialize Cart and related controllers/views
        Cart cart = new Cart();
        cartController = new CartController(customer, cart, null); // Initialize without CartView first
        CartView cartView = new CartView(cartController); // Create CartView with cartController
        cartController.setCartView(cartView); // Update cartController with cartView

        // Initialize ProductView and ProductController
        ProductView productView = new ProductView(null, cartView, categoryController); // Temporary initialization
        productController = new ProductController(cart, productView); // Initialize ProductController with Cart and ProductView
        productView.setProductController(productController); // Update ProductView with ProductController

        // Mark customer info as submitted
        isCustomerInfoSubmitted = true;

        // Remove the customer form and continue button
        shoppingAppView.updateToProductAndCartView(productView.getPanel(), cartView.getPanel());
    }
}
