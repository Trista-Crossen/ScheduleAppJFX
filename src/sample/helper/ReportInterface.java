package sample.helper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public interface ReportInterface {
    String monthTypeReport(Month month, String type);
}
