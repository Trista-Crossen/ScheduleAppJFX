package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalTime;

public abstract class OfficeHoursOfOperation {
    private static ObservableList<LocalTime> startTime = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endTime = FXCollections.observableArrayList();

    public static ObservableList<LocalTime> getStartTime() {
        LocalTime officeOpen = LocalTime.of(6,00);
        LocalTime officeClose = LocalTime.of(20, 00);
        while(officeOpen.isBefore(officeClose.minusSeconds(1))) {
            startTime.add(officeOpen);
            officeOpen = officeOpen.plusMinutes(30);
        }
        return startTime;
    }

    public static ObservableList<LocalTime> getEndTime() {
        LocalTime officeOpen = LocalTime.of(6, 30);
        LocalTime officeClose = LocalTime.of(20, 00);
        while(officeOpen.isBefore(officeClose.plusSeconds(1))){
            endTime.add(officeOpen);
            officeOpen = officeOpen.plusMinutes(30);
        }
        return endTime;
    }
}
