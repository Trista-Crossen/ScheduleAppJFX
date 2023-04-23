package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CountryDao {
    public static void selectCountries() throws SQLException {
        String sql = "SELECT Country_ID, Country FROM client_schedule.countries;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            System.out.println(countryID + " | " + countryName);
        }
    }
}
