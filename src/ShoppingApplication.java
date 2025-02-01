
import controller.CategoryController;
import controller.CustomerController;
import controller.ProductController;
import controller.CartController;
import model.Cart;
import model.CustomerData;
import strategy.DiscountPriceStrategy;
import strategy.PricingStrategy;
import strategy.RegularPriceStrategy;
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

            CustomerController customerController = new CustomerController(customerModel);
            ProductController productController = new ProductController(cart);

            // -------------------- Customer View Event Handling --------------------
            customerView.addSubmitListener(e -> {
                String name = customerView.getCustomerName();
                String password = customerView.getCustomerPassword();
                String gender = customerView.getCustomerGender();
                String email = customerView.getEmail();
                String phone = customerView.getPhone();

                customerController.submitCustomerData(name, password, gender, email, phone);
            });

            customerController.setFormSubmissionListener(success -> {
                if (success) {
                    customerView.disableInputs();
                } else {
                    customerView.showMessage("All fields must be filled out!");
                }
            });

            // -------------------- Product View Event Handling --------------------
            productView.addProductListener(e -> {
                String productId = productView.getProductId();
                String category = productView.getProductCategory();
                String name = productView.getProductName();
                String status = productView.getProductStatus();
                double price = productView.getProductPrice();

                if (price < 0) {
                    JOptionPane.showMessageDialog(null, "Invalid product price!");
                    return;
                }

                PricingStrategy pricingStrategy = productView.isDiscountApplied()
                        ? new DiscountPriceStrategy(0.1)
                        : new RegularPriceStrategy();

                productController.addProduct(productId, category, name, status, price, pricingStrategy);
            });

                //this will update the product in the table
            productController.setCartUpdateListener(cartView::updateCartTable);

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
