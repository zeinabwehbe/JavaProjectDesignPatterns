
import controller.CustomerController;
import controller.ProductController;
import controller.CartController;
import model.Cart;
import model.CustomerData;
import view.CartView;
import view.CategoryView;
import view.CustomerView;
import view.ProductView;

import javax.swing.*;

public class ShoppingApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize Models
            Cart cart = new Cart(); // Cart is managed by CartController
            CustomerData customerModel = new CustomerData();


            // Initialize Views
            CustomerView customerView = new CustomerView();
            ProductView productView = new ProductView();
            CategoryView categoryView = new CategoryView();

            // Initialize Controllers
            CartController cartController = new CartController(cart); // No need to pass CartView here

            // Initialize CartView
            CartView cartView = new CartView();  // Now CartView is created here
            cartView.setCartController(cartController); // Set the controller

            CustomerController customerController = new CustomerController(customerModel, customerView);
            ProductController productController = new ProductController(cart,productView, cartView);


            // -------------------- Set up the main frame --------------------
            JFrame frame = new JFrame("Shopping Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            frame.add(customerView.getPanel());
            frame.add(productView.getPanel());
            frame.add(categoryView.getPanel());
            frame.add(cartView.getPanel());

            frame.setVisible(true);
        });
    }
}
