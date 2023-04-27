package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionDao {
    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions(){
        ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Division_ID, Division FROM client_schedule.first_level_divisions";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int countryId = rs.getInt("Country_ID");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");


                FirstLevelDivision firstLevelDivision = new FirstLevelDivision(countryId, divisionId, divisionName);
                firstLevelDivisions.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisions;
    }

    public static ObservableList<FirstLevelDivision> getFirstLevelDivisions(int countryId){
        ObservableList<FirstLevelDivision> firstLevelDivisions = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Division_ID, Division FROM client_schedule.first_level_divisions WHERE Country_ID = ? ";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                countryId = rs.getInt("Country_ID");
                int divisionId = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");

                FirstLevelDivision firstLevelDivision = new FirstLevelDivision( countryId, divisionId, divisionName);
                firstLevelDivisions.add(firstLevelDivision);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return firstLevelDivisions;
    }
}
