package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.AppointmentDao;
import sample.model.Appointment;

import java.sql.SQLException;

public class AppointmentDaoImpl {
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        return appointments;
    }
}
