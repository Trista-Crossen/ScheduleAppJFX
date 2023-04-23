package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.UserDao;
import sample.model.User;

import java.sql.SQLException;

public class UserDaoImpl {
    private static ObservableList<User> users = FXCollections.observableArrayList();

    public static ObservableList<User> getAllUsers() throws SQLException {
        UserDao.selectUserIdAndName();

        return users;
    }
}
