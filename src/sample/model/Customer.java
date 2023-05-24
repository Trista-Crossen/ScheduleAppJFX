package sample.model;

import javafx.scene.control.SingleSelectionModel;

/**This class creates Customer objects to be used within the application*/
public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String country;
    private String firstLevelDivision;

    /**Constructor for customer objects
     * @param customerId the customer's id
     * @param customerName the customer's name
     * @param address the customer's address
     * @param firstLevelDivision the customer's county/providence
     * @param country the customer's country
     * @param postalCode the customer's postal code
     * @param phoneNumber the customer's phone number*/
    public Customer(int customerId, String customerName, String address, String firstLevelDivision, String country, String postalCode, String phoneNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.firstLevelDivision = firstLevelDivision;
    }

    /**This method returns the customerId
     * @return customerId*/
    public int getCustomerId() {
        return customerId;
    }

    /**This method sets the customerId
     * @param customerId sets customerId*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**This method returns the customerName
     * @return customerName*/
    public String getCustomerName() {
        return customerName;
    }

    /**This method sets the customerName
     * @param customerName sets customerName*/
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**This method returns the address
     * @return address*/
    public String getAddress() {
        return address;
    }

    /**This method sets the address
     * @param address sets address*/
    public void setAddress(String address) {
        this.address = address;
    }

    /**This method returns the customer's county/providence
     * @return firstLevelDivision*/
    public String getFirstLevelDivision() {
        return firstLevelDivision;
    }

    /**This method sets the customer's county/providence
     * @param firstLevelDivision sets firstLevelDivision*/
    public void setFirstLevelDivision(String firstLevelDivision) {
        this.firstLevelDivision = firstLevelDivision;
    }

    /**This method returns the postalCode
     * @return postalCode*/
    public String getPostalCode() {
        return postalCode;
    }

    /**This method sets the postal code
     * @param postalCode sets postalCode*/
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**This method returns country
     * @return country*/
    public String getCountry() {
        return country;
    }

    /**This method sets country
     * @param country sets country*/
    public void setCountry(String country) {
        this.country = country;
    }

    /**This method returns phoneNumber
     * @return phoneNumber*/
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**This method sets phoneNumber
     * @param phoneNumber  sets phoneNumber*/
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**This method overrides Customer objects to print the customerName as a string in Combo boxes
     * @return Customer object represented as customerName in a string*/
    @Override
    public String toString(){
        return (customerName);
    }
}
