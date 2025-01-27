package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import controller.CustomerController;
import model.CustomerData;

public class CustomerView {
    private JPanel panel;
    private JTextField customerNameField;
    private JTextField customerPasswordField;
    private JTextField customerGenderField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton submitButton;
    private CustomerController customerController;

    public CustomerView() {
        panel = createCustomerFormPanel();
    }

    public void setController(CustomerController customerController) {
        this.customerController = customerController;
    }

    public JPanel getPanel() {
        return panel;
    }

    private JPanel createCustomerFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(28, 2, 0, 0));
        panel.setBackground(new Color(240, 240, 240));

        // Add margins to the left and right
        panel.setBorder(new EmptyBorder(0, 20, 0, 20));


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

        submitButton = createButton();
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitCustomerData();
            }
        });
        panel.add(submitButton);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createButton() {
        JButton button = new JButton("Submit");
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    private void submitCustomerData() {
        String name = customerNameField.getText();
        String password = customerPasswordField.getText();
        String gender = customerGenderField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (name.isEmpty() || password.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "All fields must be filled out!");
            return;
        }

        Date currentDate = new Date(System.currentTimeMillis());
        customerController.setCustomerData(name, password, gender, email, phone, currentDate);

        // Disable fields after submission
        customerNameField.setEnabled(false);
        customerPasswordField.setEnabled(false);
        customerGenderField.setEnabled(false);
        emailField.setEnabled(false);
        phoneField.setEnabled(false);
        submitButton.setEnabled(false);
    }
}
