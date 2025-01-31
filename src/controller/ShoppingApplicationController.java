package controller;

import model.CustomerData;
import model.Cart;
import view.CartView;
import view.CustomerView;
import view.ProductView;
import view.CategoryView;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ShoppingApplicationController {
    private final JFrame frame;
    private CartController cartController;
    private ProductController productController;
    private final CustomerController customerController;
    private final CategoryController categoryController;
    private boolean isCustomerInfoSubmitted = false;

    public ShoppingApplicationController() {
        // Initialize the main frame
        frame = new JFrame("Shopping Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);

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
        frame.setContentPane(customerView.getPanel());

        // Initialize the "Continue" button and add it to the frame
        initializeCustomerInfoUI();
        frame.setVisible(true);
    }

    private void initializeCustomerInfoUI() {
        JButton continueButton = createContinueButton();
        frame.add(continueButton, BorderLayout.SOUTH); // Add the button to the bottom of the frame
    }

    private JButton createContinueButton() {
        JButton button = new JButton("Continue");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));

        // Use lambda function for ActionListener
        button.addActionListener(e -> {
            if (!isCustomerInfoSubmitted) {
                submitCustomerData();
            }
        });

        return button;
    }

    private void submitCustomerData() {
        // Validate customer data
        CustomerData customer = customerController.getCustomer();
        if (customer.getCustomer_username() == null || customer.getCustomer_password() == null ||
                customer.getCustomer_gender() == null || customer.getEmail() == null || customer.getPhoneNumber() == null) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled out!");
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
        frame.getContentPane().removeAll(); // Clear the frame
        addProductAndCartViews(productView, cartView); // Add the product and cart views
    }

    private void addProductAndCartViews(ProductView productView, CartView cartView) {
        // Set the layout for the frame
        frame.setLayout(new BorderLayout());

        // Create a panel to hold both ProductView and CartView
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // Add an upper margin to the bottomPanel
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0)); // Top margin: 20px, others: 0

        // Add ProductView to the top of the bottomPanel
        bottomPanel.add(productView.getPanel(), BorderLayout.NORTH);

        // Add CartView to the center of the bottomPanel
        bottomPanel.add(cartView.getPanel(), BorderLayout.CENTER);

        // Add the combined panel to the frame
        frame.add(bottomPanel, BorderLayout.CENTER);

        // Refresh the frame
        frame.revalidate();
        frame.repaint();
    }
}