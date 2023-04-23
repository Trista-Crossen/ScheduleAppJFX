package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.CountryDao;
import sample.model.Country;

import java.sql.SQLException;

public class CountryDaoImpl {
    private static ObservableList<Country> countries = FXCollections.observableArrayList();

    public static ObservableList<Country> getAllCountries() throws SQLException {
        CountryDao.selectCountries();

        return countries;
    }
}
