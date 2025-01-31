package model;

import java.util.Date;
//Remove the constructor and add a private constructor to enforce the use of the builder.
public class CustomerData {
    private String customer_username;
    private String customer_password;
    private String customer_gender;
    private Date date;
    private String email;
    private String phoneNumber;

    // Private constructor to enforce the use of the builder
    private CustomerData() {}

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

    // Builder class
    public static class CustomerDataBuilder {
        private final CustomerData customerData;

        public CustomerDataBuilder() {
            this.customerData = new CustomerData();
        }

        public CustomerDataBuilder setCustomerUsername(String username) {
            customerData.setCustomer_username(username);
            return this;
        }

        public CustomerDataBuilder setCustomerPassword(String password) {
            customerData.setCustomer_password(password);
            return this;
        }

        public CustomerDataBuilder setCustomerGender(String gender) {
            customerData.setCustomer_gender(gender);
            return this;
        }

        public CustomerDataBuilder setDate(Date date) {
            customerData.setDate(date);
            return this;
        }

        public CustomerDataBuilder setEmail(String email) {
            customerData.setEmail(email);
            return this;
        }

        public CustomerDataBuilder setPhoneNumber(String phoneNumber) {
            customerData.setPhoneNumber(phoneNumber);
            return this;
        }

        public CustomerData build() {
            return customerData;
        }
    }
}