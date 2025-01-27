import controller.CartController;
import controller.CustomerController;
import controller.ProductController;
import model.CustomerData;
import model.Cart;
import view.CartView;
import view.CustomerView;
import view.ProductView;

import javax.swing.*;
import java.awt.*;

public class ShoppingApplicationUI {
    private final JFrame frame;
    private CartController cartController;
    private ProductController productController;
    private final CustomerController customerController;
    private final CustomerView customerView;
    private JButton continueButton;
    private boolean isCustomerInfoSubmitted = false;

    public ShoppingApplicationUI() {
        frame = new JFrame("Shopping Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        CustomerData customerData = new CustomerData();
        customerView = new CustomerView();
        customerController = new CustomerController(customerData, customerView);

        frame.setContentPane(customerView.getPanel());

        initializeCustomerInfoUI();
        frame.setVisible(true);
    }

    private void initializeCustomerInfoUI() {
        continueButton = createContinueButton();
        frame.add(continueButton, BorderLayout.SOUTH);
    }

    private JButton createContinueButton() {
        JButton button = new JButton("Continue");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.addActionListener(e -> {
            if (!isCustomerInfoSubmitted) {
                submitCustomerData();
            }
        });
        return button;
    }

    private void submitCustomerData() {
        CustomerData customer = customerController.getCustomer();
        if (customer.getCustomer_username() == null || customer.getCustomer_password() == null || customer.getCustomer_gender() == null ||
                customer.getEmail() == null || customer.getPhoneNumber() == null) {
            JOptionPane.showMessageDialog(frame, "All fields must be filled out!");
            return;
        }

        Cart cart = new Cart();
        cartController = new CartController(customer, cart, null); // Initialize without CartView first
        CartView cartView = new CartView(cartController); // Create CartView with cartController
        cartController.setCartView(cartView); // Update cartController with cartView
        ProductView productView = new ProductView(null, cartView); // Temporary initialization, will be updated in ProductController
        productController = new ProductController(cart, productView); // Initialize ProductController with Cart and ProductView
        productView.setProductController(productController); // Update ProductView with ProductController
        isCustomerInfoSubmitted = true;

        // Remove customerView and continueButton from the frame
        frame.remove(customerView.getPanel());
        frame.remove(continueButton);

        addProductAndCartViews(productView, cartView);
    }

    private void addProductAndCartViews(ProductView productView, CartView cartView) {
        frame.add(productView.getPanel(), BorderLayout.NORTH);
        frame.add(cartView.getPanel(), BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShoppingApplicationUI());
    }
}
