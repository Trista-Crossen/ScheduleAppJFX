package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Country;

public abstract class CountryDao {
    private static ObservableList<Country> countries;

    public static ObservableList<Country> getCountries() {
        return countries;
    }
}
