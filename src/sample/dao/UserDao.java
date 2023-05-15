package sample.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**This class pulls user information out of the database for use within the application*/
public abstract class UserDao {
    /**This method returns an observable list of users
     * @return userInformation*/
    public static ObservableList<User> getUserInfo(){
        ObservableList<User> userInformation = FXCollections.observableArrayList();
        String sql = "SELECT * FROM client_schedule.users;";
        try {
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);

            while(rs.next()) {
                int userId = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                User userInfo = new User(userId, userName, password);

                userInformation.add(userInfo);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userInformation;
    }
}
