package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.FirstLevelDivisionDao;
import sample.model.FirstLevelDivision;

import java.sql.SQLException;

public class FirstLevelDivisionDaoImpl {
    private static ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();

    public static ObservableList<FirstLevelDivision> getAllFirstLevelDivisions() throws SQLException {
        FirstLevelDivisionDao.selectFirstLevelDivision();

        return firstLevelDivisions;
    }
}
