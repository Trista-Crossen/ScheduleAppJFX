package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Customer;

public abstract class CustomerDao {
    private static ObservableList<Customer> customers;

    public static ObservableList<Customer> getAllCustomers(){
        return customers;
    }

    public static void addCustomer(Customer customer) {

    }

    public static void updateCustomer(int index, Customer selectedCustomer) {

    }

    public static boolean deleteCustomer(Customer selectedCustomer){
        return false;
    }
}
