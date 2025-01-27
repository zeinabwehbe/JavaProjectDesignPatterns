package controller;

import model.CustomerData;
import view.CustomerView;

import java.util.Date;

public class CustomerController {
    private final CustomerData customer;
    private final CustomerView view;

    public CustomerController(CustomerData customer, CustomerView view) {
        this.customer = customer;
        this.view = view;
        this.view.setController(this);
    }

    public void setCustomerData(String name, String password, String gender, String email, String phone, Date date) {
        customer.setCustomer_username(name);
        customer.setCustomer_password(password);
        customer.setCustomer_gender(gender);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
    }

    public CustomerData getCustomer() {
        return customer;
    }
}
