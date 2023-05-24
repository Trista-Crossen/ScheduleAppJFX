package sample.helper;

import java.time.LocalTime;

/**This interface does time calculations from LocalTime objects and is used for the time alert lambda*/
public interface TimeInterface {
    /**This abstract method is used to find the difference between the current time on the machine and the start time of the next upcoming appointment for the user
     * @param currentTime the time of log-in
     * @param startTime the time of the next upcoming appointment
     * @return long the time difference between currentTime and startTime*/
    long timeCalculation(LocalTime startTime, LocalTime currentTime);
}
