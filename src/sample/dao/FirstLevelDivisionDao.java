package sample.dao;

import javafx.collections.ObservableList;
import sample.model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class FirstLevelDivisionDao {
    public static void selectFirstLevelDivision() throws SQLException {
        String sql = "SELECT Division_ID, Division, Country_ID FROM client_schedule.first_level_divisions";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");
            int countryId = rs.getInt("Country_ID");

            System.out.println(divisionId + " | " + divisionName + " | " + countryId);
        }
    }

    public static void selectFirstLevelDivision(int countryId) throws SQLException {
        String sql = "SELECT Division_ID, Division FROM client_schedule.first_level_divisions WHERE Country_ID = ? ";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            System.out.println(divisionId + " | " + divisionName);
        }
    }
}
