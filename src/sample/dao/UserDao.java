package sample.dao;

import javafx.collections.ObservableList;
import sample.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {
    public static void selectLoginInfo() throws SQLException {
        String sql = "SELECT User_Name, Password FROM client_schedule.users;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()) {
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");
        }
    }

    public static void selectUserIdAndName() throws SQLException {
        String sql = "SELECT User_ID, User_Name FROM client_schedule.users;";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while(rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");

            System.out.println(userId + " | " + userName);
        }
    }
}
