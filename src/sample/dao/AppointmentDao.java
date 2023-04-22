package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Appointment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class AppointmentDao {

    public static int addAppointment(String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, Timestamp dateCreated, String createdBy, Timestamp lastUpdate, String lastUpdateBy, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO client_schedule.appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setObject(5, startTime);
        ps.setObject(6, endTime);
        ps.setTimestamp(7, dateCreated);
        ps.setString(8, createdBy);
        ps.setTimestamp(9, lastUpdate);
        ps.setString(10, lastUpdateBy);
        ps.setInt(11, customerId);
        ps.setInt(12, userId);
        ps.setInt(13, contactId);

        int rowsAffected = ps.executeUpdate();

        return rowsAffected;
    }

    public static void updateAppointment(int index, Appointment selectedAppointment) {

    }

    public static boolean deleteAppointment(Appointment selectedAppointment) {
        return false;
    }
}
