package controller;

import Builder.CustomerDataBuilder;
import model.CustomerData;
import view.CustomerView;
import java.util.function.Consumer;

/**
 * Controller for handling customer-related actions such as submitting customer data
 * and managing form interactions between the Customer model and Customer view.
 */
public class CustomerController {
    private final CustomerData customerModel;  // The model storing customer data
    private final CustomerView customerView;    // The view for displaying customer form
    private Consumer<Boolean> formSubmissionListener;  // Listener to handle form submission result

    /**
     * Constructs a CustomerController with a given CustomerModel and CustomerView.
     *
     * @param customerModel The model that contains customer data.
     * @param customerView  The view for displaying and interacting with customer data.
     */
    public CustomerController(CustomerData customerModel, CustomerView customerView) {
        this.customerModel = customerModel;
        this.customerView = customerView;
        attachListeners();  // Attach listeners to handle form interactions
    }

    /**
     *  a lambda expression is used to define the behavior when the submit button is clicked.
     *  This lambda simply calls handleCustomerSubmission() whenever the submit button is triggered.
     * Attaches the listener for form submission action on the view.
     * The listener triggers the handleCustomerSubmission method.
     */
    private void attachListeners() {
        customerView.addSubmitListener(_ -> handleCustomerSubmission());  // Lambda listener for submit action
    }

    /**
     * Handles the customer form submission. Gathers input data and submits it for processing.
     * If the submission is successful, disables form inputs, otherwise shows an error message.
     */
    private void handleCustomerSubmission() {
        String name = customerView.getCustomerName();
        String password = customerView.getCustomerPassword();
        String gender = customerView.getCustomerGender();
        String email = customerView.getEmail();
        String phone = customerView.getPhone();

        boolean success = submitCustomerData(name, password, gender, email, phone);
        if (success) {
            customerView.disableInputs();  // Disable the input fields on successful submission
        } else {
            customerView.showMessage("All fields must be filled out!");  // Show error message
        }
    }

    /**
     * Submits the customer data after validating the fields. If any field is empty, returns false.
     * If all fields are filled, creates a CustomerData object and stores it in the model.
     *
     * @param name     The customer name.
     * @param password The customer password.
     * @param gender   The customer gender.
     * @param email    The customer email.
     * @param phone    The customer phone number.
     * @return A boolean indicating whether the submission was successful.
     */
    public boolean submitCustomerData(String name, String password, String gender, String email, String phone) {
        // Check if any of the required fields are empty
        if (name.isEmpty() || password.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            if (formSubmissionListener != null) {
                formSubmissionListener.accept(false);  // Notify listener with failure
            }
            return false;  // Return false if any field is empty
        }

        // Use the builder pattern to create a CustomerData instance
        CustomerData customerData = new CustomerDataBuilder()
                .setCustomerUsername(name)
                .setCustomerPassword(password)
                .setCustomerGender(gender)
                .setEmail(email)
                .setPhoneNumber(phone)
                .setDate(new java.util.Date())  // Set the current date for the customer
                .build();

        // Set the customer data in the model
        customerModel.setCustomerData(customerData);

        // Notify the listener with success
        if (formSubmissionListener != null) {
            formSubmissionListener.accept(true);  // Notify listener with success
        }
        return true;  // Return true if data submission is successful
    }
}
