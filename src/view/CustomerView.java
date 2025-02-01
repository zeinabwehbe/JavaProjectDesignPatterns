package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerView {
    private final JPanel panel;
    private JTextField customerNameField;
    private JTextField customerPasswordField;
    private JTextField customerGenderField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton submitButton;

    public CustomerView() {
        panel = createCustomerFormPanel();
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createCustomerFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 5, 5));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        panel.add(createLabel("Name:"));
        customerNameField = new JTextField();
        panel.add(customerNameField);

        panel.add(createLabel("Password:"));
        customerPasswordField = new JPasswordField();
        panel.add(customerPasswordField);

        panel.add(createLabel("Gender:"));
        customerGenderField = new JTextField();
        panel.add(customerGenderField);

        panel.add(createLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(createLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);

        submitButton = createButton("Submit");
        panel.add(submitButton);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    // **Expose a method to attach an external action listener (Decoupling from controllers)**
    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public String getCustomerName() {
        return customerNameField.getText();
    }

    public String getCustomerPassword() {
        return customerPasswordField.getText();
    }

    public String getCustomerGender() {
        return customerGenderField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public void disableInputs() {
        customerNameField.setEnabled(false);
        customerPasswordField.setEnabled(false);
        customerGenderField.setEnabled(false);
        emailField.setEnabled(false);
        phoneField.setEnabled(false);
        submitButton.setEnabled(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(panel, message);
    }
}
