package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This class pulls country information out of the database to be used within the application*/
public abstract class CountryDao {
    /**This method returns an observable list of countries
     * @return countries*/
    public static ObservableList<Country> getAllCountries(){
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT Country_ID, Country FROM client_schedule.countries;";

        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");

                Country country = new Country(countryID, countryName);
                countries.add(country);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return countries;
    }
}
