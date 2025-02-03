import controller.CustomerController;
import controller.ProductController;
import controller.CartController;
import model.Cart;
import model.CustomerData;
import view.CartView;
import view.CategoryView;
import view.CustomerView;
import view.ProductView;
import view.AdminView;

import javax.swing.*;
import java.awt.*;

public class ShoppingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize Models
            Cart cart = new Cart();
            CustomerData customerModel = new CustomerData();

            // Initialize Views
            CustomerView customerView = new CustomerView();
            ProductView productView = new ProductView();
            CategoryView categoryView = new CategoryView();
            CartView cartView = new CartView();

            // Initialize Controllers
            CartController cartController = new CartController(cart);
            cartView.setCartController(cartController);
            CustomerController customerController = new CustomerController(customerModel, customerView);

            // Create Admin View (Separate Window)
            AdminView adminView = new AdminView();
            ProductController productController = new ProductController(cart, productView, adminView);
            productController.setProductUpdateListener(productView::updateProductList);

            // Set up the main frame
            JFrame frame = new JFrame("Shopping Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            // Button to open Admin Panel in a new window
            JButton adminButton = new JButton("Go to Admin Panel");
            adminButton.addActionListener(_ -> openAdminWindow(adminView));

            frame.add(adminButton);
            frame.add(customerView.getPanel());
            frame.add(categoryView.getPanel());
            frame.add(productView.getPanel());
            frame.add(cartView.getPanel());

            frame.setVisible(true);
        });
    }

    private static void openAdminWindow(AdminView adminView) {
        JFrame adminFrame = new JFrame("Admin Panel");
        adminFrame.setSize(600, 400);
        adminFrame.setLayout(new BorderLayout());
        adminFrame.add(adminView.getPanel(), BorderLayout.CENTER);
        adminFrame.setVisible(true);
    }
}
