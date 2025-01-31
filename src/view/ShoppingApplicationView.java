package view;

import javax.swing.*;
import java.awt.*;

public class ShoppingApplicationView extends JFrame {

    public static JButton continueButton;

    public ShoppingApplicationView() {
        super("Shopping Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
    }

    public void initializeCustomerView(JPanel customerPanel) {
        setContentPane(customerPanel);
        continueButton = createContinueButton();
        continueButton.setEnabled(false);
        add(continueButton, BorderLayout.SOUTH);
    }

    public void updateToProductAndCartView(JPanel productPanel, JPanel cartPanel) {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0));
        bottomPanel.add(productPanel, BorderLayout.NORTH);
        bottomPanel.add(cartPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private JButton createContinueButton() {
        JButton button = new JButton("Continue");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    public JButton getContinueButton() {
        return continueButton;
    }
}
