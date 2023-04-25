package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentDao {

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, String createdBy, String lastUpdateBy, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO client_schedule.appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, now(), ?, now(), ?, ?, ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setObject(5, startTime);
        ps.setObject(6, endTime);
        ps.setString(7, createdBy);
        ps.setString(8, lastUpdateBy);
        ps.setInt(9, customerId);
        ps.setInt(10, userId);
        ps.setInt(11, contactId);

        ps.executeUpdate();
    }

    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, String lastUpdateBy, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = now(), Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setObject(5, startTime);
        ps.setObject(6, endTime);
        ps.setString(7, lastUpdateBy);
        ps.setInt(8, customerId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);
        ps.setInt(11, appointmentId);

        ps.executeUpdate();
    }

    public static void deleteAppointment(int appointmentId) throws SQLException {
        String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);

        ps.executeUpdate();
    }

    public static void selectAppointments() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID " +
                "FROM client_schedule.appointments, client_schedule.contacts " +
                "WHERE appointments.Contact_ID = contacts.Contact_ID";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String contactName = rs.getString("Contact_Name");
            String type = rs.getString("Type");
            LocalDateTime startTime = (LocalDateTime) rs.getObject("Start");
            LocalDateTime endTime = (LocalDateTime) rs.getObject("End");
            int customerId = rs.getInt("Customer_ID");
            int userId = rs.getInt("User_ID");
        }
    }

        public static void selectAppointments(int customerID) throws SQLException {
            String sql = "SELECT Appointment_ID, Title, Description, Location, Contact_Name, Type, Start, End, Customer_ID, User_ID " +
                    "FROM client_schedule.appointments INNER JOIN client_schedule.contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE Customer_ID = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String contactName = rs.getString("Contact_Name");
                String type = rs.getString("Type");
                LocalDateTime startTime = (LocalDateTime) rs.getObject("Start");
                LocalDateTime endTime = (LocalDateTime) rs.getObject("End");
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                System.out.println(appointmentId + " | " + title  + " | " + description  + " | " + location  + " | " + contactName  + " | " + type  + " | " + startTime  + " | " + endTime  + " | " + customerId  + " | " + userId);
            }
        }

}
