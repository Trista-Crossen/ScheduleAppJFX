package sample.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerDao {
    public static void addCustomer(String customerName, String address, String postalCode, String phoneNumber, String createdBy, String lastUpdatedBy, int divisionId) throws SQLException {
        String sql = "INSERT INTO client_schedule.customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?, ?, ?, ?, now(), ?, now(), ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setString(5, createdBy);
        ps.setString(6, lastUpdatedBy);
        ps.setInt(7, divisionId);

        ps.executeUpdate();
    }

    public static void updateCustomer(int customerId, String customerName,String address, String postalCode, String phoneNumber, String lastUpdatedBy, int divisionId ) throws SQLException {
        String sql = "UPDATE client_schedule.customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = now(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phoneNumber);
        ps.setString(5, lastUpdatedBy);
        ps.setInt(6, divisionId);
        ps.setInt(7, customerId);

        ps.executeUpdate();
    }

    public static void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM client_schedule.customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, customerId);

        ps.executeUpdate();
    }

    public static void selectCustomers() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Division, Country, Postal_Code, Phone " +
                "FROM client_schedule.customers, client_schedule.first_level_divisions, client_schedule.countries " +
                "WHERE customers.Division_ID = first_level_divisions.Division_ID AND first_level_divisions.Country_ID = countries.Country_ID;";
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
