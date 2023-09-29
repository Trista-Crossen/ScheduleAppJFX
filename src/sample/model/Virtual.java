package sample.model;

import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Virtual extends Appointment{
    URL meetingRoomLink;
    String meetingRoomName;

    public Virtual(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int contactId, int userId, int customerId, URL meetingRoomLink, String meetingRoomName) {
        super(appointmentId, title, description, location, type, startTime, endTime, contactId, userId, customerId);
        this.meetingRoomLink = meetingRoomLink;
        this.meetingRoomName = meetingRoomName;
    }

    public URL getMeetingRoomLink() {
        return meetingRoomLink;
    }

    public void setMeetingRoomLink(URL meetingRoomLink) {
        this.meetingRoomLink = meetingRoomLink;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public static String appointmentLocation(URL meetingRoomLink, String meetingRoomName) throws IOException {
        String location = meetingRoomLink.toString() + "\n" + meetingRoomName;
        return location;
    }
}
