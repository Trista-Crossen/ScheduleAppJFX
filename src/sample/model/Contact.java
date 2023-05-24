package sample.model;

import javafx.scene.control.SingleSelectionModel;

/**This class makes Contact objects for the application*/
public class Contact {
    private int contactId;
    private String contactName;

    /**Constructor for Contact objects
     * @param contactId
     * @param contactName */
    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /**This method returns the contactId
     * @return contactId*/
    public int getContactId() {
        return contactId;
    }

    /**This method sets the contactId
     * @param contactId sets the contactId*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**This method returns the contactName
     * @return contactName*/
    public String getContactName() {
        return contactName;
    }

    /**This method sets the contactName
     * @param contactName sets the contactName*/
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**This method overrides Contact objects to string objects and returns the string
     * @return contactName as a string*/
    @Override
    public String toString() {
        return (contactName);
    }
}
