package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Appointment;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public abstract class AppointmentDao {

    public static ObservableList<Appointment> getAllAppointments() {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        String sql = "SELECT Appointment_ID, Title, Description, Location, Contact_ID, Type, Start, End, Customer_ID, User_ID " +
                "FROM client_schedule.appointments";
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                int contactId = rs.getInt("Contact_ID");
                String type = rs.getString("Type");
                Timestamp tsStart = rs.getTimestamp("Start");
                LocalDateTime startTime = tsStart.toLocalDateTime();
                Timestamp tsEnd = rs.getTimestamp("End");
                LocalDateTime endTime = tsEnd.toLocalDateTime();
                int customerId = rs.getInt("Customer_ID");
                int userId = rs.getInt("User_ID");

                Appointment appointment = new Appointment(appointmentId, title, description, location, type, startTime, endTime, contactId, userId, customerId);

                allAppointments.add(appointment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allAppointments;
    }

    public static void addAppointment(String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) {
        String sql = "INSERT INTO client_schedule.appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setObject(5, startTime);
            ps.setObject(6, endTime);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);

            ps.executeUpdate();
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int customerId, int userId, int contactId) {
        String sql = "UPDATE client_schedule.appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        try{
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setObject(5, startTime);
            ps.setObject(6, endTime);
            ps.setInt(7, customerId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
            ps.setInt(10, appointmentId);

            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteAppointment(int appointmentId){
        String sql = "DELETE FROM client_schedule.appointments WHERE Appointment_ID = ?";
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, appointmentId);
            ps.executeUpdate();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ObservableList<Appointment> getTypes() {
        ObservableList<Appointment> appointmentTypes = FXCollections.observableArrayList();
        String sql = "SELECT distinct Type from client_schedule.appointments";
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()){
                String type = rs.getString("Type");

                Appointment appointmentType = new Appointment(type);

                appointmentTypes.add(appointmentType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return appointmentTypes;
    }

    public static ObservableList<Appointment> getMonths() {
        ObservableList<Appointment> appointmentMonths = FXCollections.observableArrayList();
        for(int i = 0; i < getAllAppointments().size(); i++){
            Appointment appointment = getAllAppointments().get(i);
            Month appointmentMonth = appointment.getStartTime().getMonth();
            Appointment month = new Appointment(appointmentMonth);
            if(!month.equals(getAllAppointments().get(i).getStartTime().getMonth())){
                appointmentMonths.add(month);
            }
            month.toString();
        }
        return appointmentMonths;
    }
}


