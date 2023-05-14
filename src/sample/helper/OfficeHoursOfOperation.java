package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.*;
import java.util.TimeZone;

public abstract class OfficeHoursOfOperation {
    private static ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTime = FXCollections.observableArrayList();
    private static ObservableList<Month> months = FXCollections.observableArrayList();
    private static ZoneId officeZoneId = ZoneId.of("EST5EDT");

    public static ObservableList<LocalTime> getStartTime(){
        ZonedDateTime officeOpen = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), officeZoneId);
        ZonedDateTime officeClose = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 00), officeZoneId);

        while (officeOpen.isBefore(officeClose.minusSeconds(1))){
            startTime.add(officeOpen.toLocalTime());
            officeOpen = officeOpen.plusMinutes(30);
        }
        return startTime;
    }

    public static ObservableList<LocalTime> getEndTime() {
        ZonedDateTime officeOpen = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 30), officeZoneId);
        ZonedDateTime offClose = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 00), officeZoneId);

        while(officeOpen.isBefore(offClose.plusSeconds(1))){
            endTime.add(officeOpen.toLocalTime());
            officeOpen = officeOpen.plusMinutes(30);
        }
        return endTime;
    }
}
