package sample.dao;

import javafx.collections.ObservableList;
import sample.model.FirstLevelDivision;

public abstract class FirstLevelDivisionDao {
    private static ObservableList<FirstLevelDivision> firstLevelDivisions;

    public static ObservableList<FirstLevelDivision> getFirstLevelDivisionsForCountry(){
        return firstLevelDivisions;
    }
}
