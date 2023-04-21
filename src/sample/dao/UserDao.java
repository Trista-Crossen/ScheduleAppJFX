package sample.dao;

import javafx.collections.ObservableList;
import sample.model.User;

public abstract class UserDao {
    private static ObservableList<User> users;

    public static ObservableList<User> getUsers(){
        return users;
    }
}
