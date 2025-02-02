package controller;

import model.CustomerData;
import view.CustomerView;

import java.util.function.Consumer;

public class CustomerController {
    private final CustomerData customerModel;
    private final CustomerView customerView;
    private Consumer<Boolean> formSubmissionListener;

    public CustomerController(CustomerData customerModel, CustomerView customerView) {
        this.customerModel = customerModel;
        this.customerView = customerView;
        attachListeners();
    }

    private void attachListeners() {
        customerView.addSubmitListener(_ -> handleCustomerSubmission());
    }

    private void handleCustomerSubmission() {
        String name = customerView.getCustomerName();
        String password = customerView.getCustomerPassword();
        String gender = customerView.getCustomerGender();
        String email = customerView.getEmail();
        String phone = customerView.getPhone();

        boolean success = submitCustomerData(name, password, gender, email, phone);
        if (success) {
            customerView.disableInputs();
        } else {
            customerView.showMessage("All fields must be filled out!");
        }
    }

    public boolean submitCustomerData(String name, String password, String gender, String email, String phone) {
        if (name.isEmpty() || password.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            if (formSubmissionListener != null) {
                formSubmissionListener.accept(false);
            }
            return false;
        }

        // Using the builder pattern to create CustomerData instance
        CustomerData customerData = new CustomerData.CustomerDataBuilder()
                .setCustomerUsername(name)
                .setCustomerPassword(password)
                .setCustomerGender(gender)
                .setEmail(email)
                .setPhoneNumber(phone)
                .setDate(new java.util.Date()) // Set the current date
                .build();

        // Set the built CustomerData in the model
        customerModel.setCustomerData(customerData);

        if (formSubmissionListener != null) {
            formSubmissionListener.accept(true);
        }
        return true;
    }
}
