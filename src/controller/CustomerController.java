package controller;

import model.CustomerData;
import view.CustomerView;

import java.util.Date;

public class CustomerController {
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields
    //~ ----------------------------------------------------------------------------------------------------------------

    private final CustomerData customer;
    private final CustomerView view;
    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors
    //~ ----------------------------------------------------------------------------------------------------------------


    public CustomerController(CustomerData customer, CustomerView view) {
        this.customer = customer;
        this.view = view;
        this.view.setController(this);
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods
    //~ ----------------------------------------------------------------------------------------------------------------

    public void setCustomerData(String name, String password, String gender, String email, String phone, Date date) {
        CustomerData customerData = new CustomerData.CustomerDataBuilder()
                .setCustomerUsername(name)
                .setCustomerPassword(password)
                .setCustomerGender(gender)
                .setEmail(email)
                .setPhoneNumber(phone)
                .setDate(date)
                .build();

        // Update the customer object
        this.customer.setCustomer_username(customerData.getCustomer_username());
        this.customer.setCustomer_password(customerData.getCustomer_password());
        this.customer.setCustomer_gender(customerData.getCustomer_gender());
        this.customer.setEmail(customerData.getEmail());
        this.customer.setPhoneNumber(customerData.getPhoneNumber());
        this.customer.setDate(customerData.getDate());
    }

    public CustomerData getCustomer() {
        return customer;
    }
}