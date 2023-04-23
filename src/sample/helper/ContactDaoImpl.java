package sample.helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.dao.ContactDao;
import sample.model.Contact;

import java.sql.SQLException;

public class ContactDaoImpl {
    private static ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ContactDao.selectContacts();

        return contacts;
    }
}
