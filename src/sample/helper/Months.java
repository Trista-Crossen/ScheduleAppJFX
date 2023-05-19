package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.Month;
import java.util.Arrays;

/**This class holds an Observable List of Months*/
public abstract class Months {
    /**This method is called when a list of Months is needed
     * @return months*/
    public static ObservableList<Month> getMonths() {
        ObservableList<Month> months = FXCollections.observableArrayList();
        months.add(Month.JANUARY);
        months.add(Month.FEBRUARY);
        months.add(Month.MARCH);
        months.add(Month.APRIL);
        months.add(Month.MAY);
        months.add(Month.JUNE);
        months.add(Month.JULY);
        months.add(Month.AUGUST);
        months.add(Month.SEPTEMBER);
        months.add(Month.OCTOBER);
        months.add(Month.NOVEMBER);
        months.add(Month.DECEMBER);
        return months;
    }
}
