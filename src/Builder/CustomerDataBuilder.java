package Builder;

import model.CustomerData;

import java.util.Date;

// Builder class
public class CustomerDataBuilder {
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