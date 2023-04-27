package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;
import sample.model.Customer;
import sample.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDao {
    public static ObservableList<Customer> getAllCustomers(){
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name, Address, Division, Country, Postal_Code, Phone\n" +
                "FROM client_schedule.customers, client_schedule.first_level_divisions, client_schedule.countries\n" +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID;";
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                int customerId = rs.getInt("Customer_ID");
                String customerName = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String division = rs.getString("Division");
                String country = rs.getString("Country");
                String postalCode = rs.getString("Postal_Code");
                String phoneNumber = rs.getString("Phone");

                Customer customer = new Customer(customerId, customerName, address, division, country, postalCode, phoneNumber);

                customers.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    public static void addCustomer(String customerName, String address, String postalCode, String phoneNumber, int divisionId) {
        String sql = "INSERT INTO client_schedule.customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void updateCustomer(int customerId, String customerName,String address, String postalCode, String phoneNumber, int divisionId ){
        String sql = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, customerName);
            ps.setString(2, address);
            ps.setString(3, postalCode);
            ps.setString(4, phoneNumber);
            ps.setInt(5, divisionId);
            ps.setInt(6, customerId);


            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void deleteCustomer(int customerId){
        String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, customerId);

            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void selectCustomerIdAndName() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name FROM client_schedule.customers";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
        }
    }
}
