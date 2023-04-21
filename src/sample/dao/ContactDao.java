package sample.dao;

import javafx.collections.ObservableList;
import sample.model.Contact;

public abstract class ContactDao {
    private static ObservableList<Contact> contacts;

    public static ObservableList<Contact> getContacts() {
        return contacts;
    }
}
