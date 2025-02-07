package model;

import java.util.Date;

public class CustomerData {
    private String customer_username;
    private String customer_password;
    private String customer_gender;
    private Date date;
    private String email;
    private String phoneNumber;

    // Private constructor to enforce the use of the builder
    public CustomerData() {}

    // Getters and Setters
    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getCustomer_password() {
        return customer_password;
    }

    public void setCustomer_password(String customer_password) {
        this.customer_password = customer_password;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerData{" +
                "customer_username='" + customer_username + '\'' +
                ", customer_password='" + customer_password + '\'' +
                ", customer_gender='" + customer_gender + '\'' +
                ", date=" + date +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    // Set customer data using the builder pattern
    public void setCustomerData(CustomerData customerData) {
        this.customer_username = customerData.getCustomer_username();
        this.customer_password = customerData.getCustomer_password();
        this.customer_gender = customerData.getCustomer_gender();
        this.date = customerData.getDate();
        this.email = customerData.getEmail();
        this.phoneNumber = customerData.getPhoneNumber();
    }


}
