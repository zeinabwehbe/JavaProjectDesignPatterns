import controller.ShoppingApplicationController;

import javax.swing.*;

public class ShoppingApplicationUI {
    public static void main(String[] args) {
        // Launch the application on the Event Dispatch Thread using lambda
        SwingUtilities.invokeLater(() -> new ShoppingApplicationController());
    }
}