package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.Month;

/**This enum class holds a list of months to be used on the reports screen in a combo box*/
public enum MonthList {
    JANUARY,
    FEBRUARY,
    MARCH,
    APRIL,
    MAY,
    JUNE,
    JULY,
    AUGUST,
    SEPTEMBER,
    NOVEMBER,
    DECEMBER;

    /**This method returns an Observable list of months.
     * @return  months*/
    public static ObservableList<Month> getMonths(){
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
