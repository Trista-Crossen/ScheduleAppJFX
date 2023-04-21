package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Appointment;

public abstract class AppointmentDao {
    private static ObservableList<Appointment> appointments;

    public static ObservableList<Appointment> getAllAppointments() {
        return appointments;
    }

    public static void addAppointment(Appointment appointment) {

    }

    public static void updateAppointment(int index, Appointment selectedAppointment) {

    }

    public static boolean deleteAppointment(Appointment selectedAppointment) {
        return false;
    }
}
