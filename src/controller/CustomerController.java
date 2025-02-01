package controller;

import model.CustomerData;

import java.util.function.Consumer;

public class CustomerController {
    private final CustomerData model;
    private Consumer<Boolean> formSubmissionListener;

    public CustomerController(CustomerData model) {
        this.model = model;
    }

    public void setFormSubmissionListener(Consumer<Boolean> listener) {
        this.formSubmissionListener = listener;
    }

    public void submitCustomerData(String name, String password, String gender, String email, String phone) {
        if (name.isEmpty() || password.isEmpty() || gender.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            if (formSubmissionListener != null) {
                formSubmissionListener.accept(false);
            }
            return;
        }

        // Using the builder pattern to set customer data
        CustomerData customerData = new CustomerData.CustomerDataBuilder()
                .setCustomerUsername(name)
                .setCustomerPassword(password)
                .setCustomerGender(gender)
                .setEmail(email)
                .setPhoneNumber(phone)
                .setDate(new java.util.Date()) // Set the current date
                .build();

        // Set the built CustomerData in the model
        model.setCustomerData(customerData);

        if (formSubmissionListener != null) {
            formSubmissionListener.accept(true);
        }
    }
}
