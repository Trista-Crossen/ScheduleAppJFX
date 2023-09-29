package sample.model;

import java.time.LocalDateTime;

public class InPerson extends Appointment{
    String address;
    String suite;
    public InPerson(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int contactId, int userId, int customerId, String address, String suite) {
        super(appointmentId, title, description, location, type, startTime, endTime, contactId, userId, customerId);
        this.address = address;
        this.suite = suite;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public static String appointmentLocation(String address, String suite){
        String location = address + "\n" + suite;
        return location;
    }
}
