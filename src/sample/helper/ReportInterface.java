package sample.helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

/**This interface prints a string for reports*/
public interface ReportInterface {
    /**This abstract method is used to take in a month and appointment type and returns a string
     * @param month the selected month
     * @param type the selected type
     * @return String the string that is printed for the report*/
    String monthTypeReport(Month month, String type);
}
