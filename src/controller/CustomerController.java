package controller;

import model.CustomerData;

import java.util.Date;

public class CustomerController {
    private final CustomerData customer;

    public CustomerController() {
        this.customer = new CustomerData();
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
