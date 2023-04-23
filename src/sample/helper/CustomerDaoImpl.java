package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.CustomerDao;
import sample.model.Customer;

import java.sql.SQLException;

public class CustomerDaoImpl {
    private static ObservableList<Customer> customers = FXCollections.observableArrayList();

    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        CustomerDao.selectCustomers();

        return customers;
    }
}
