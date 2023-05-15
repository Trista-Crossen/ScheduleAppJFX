package sample.model;

import java.time.LocalDateTime;

/**This class creates Appointment objects*/
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int contactId;
    private int userId;
    private int customerId;

    /**Constructor for Appointment objects*/
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime, LocalDateTime endTime, int contactId, int userId, int customerId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.contactId = contactId;
        this.userId = userId;
        this.customerId = customerId;
    }

    /**Constructor for Type objects*/
    public Appointment(String type) {
        this.type = type;
    }

    /**This method returns the appointmentId from an Appointment object
     * @return  appointmentId*/
    public int getAppointmentId() {
        return appointmentId;
    }

    /**This method sets the appointmentId
     * @param appointmentId sets appointmentId*/
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**This method returns the title
     * @return title*/
    public String getTitle() {
        return title;
    }

    /**This method sets the title
     * @param title sets title*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**This method returns the description
     * @return description*/
    public String getDescription() {
        return description;
    }

    /**This method sets the description
     * @param description sets description*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**This method returns the location
     * @return location*/
    public String getLocation() {
        return location;
    }

    /**This method sets the location
     *@param location sets the location */
    public void setLocation(String location) {
        this.location = location;
    }

    /**This method returns the type
     * @return type*/
    public String getType() {
        return type;
    }

    /**This method sets the type
     * @param type sets the type*/
    public void setType(String type) {
        this.type = type;
    }

    /**This method returns startTime
     * @return startTime*/
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**This method sets the startTime
     * @param startTime sets startTime*/
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**This method returns endTime
     * @return endTime*/
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**This method sets endTime
     * @param endTime sets endTime*/
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**This method returns contactId
     * @return contactId*/
    public int getContactId() {
        return contactId;
    }

    /**This method sets the contactId
     * @param contactId sets contactId*/
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**This method returns userId
     * @return userId*/
    public int getUserId() {
        return userId;
    }

    /**This method sets userId
     * @param userId sets userId*/
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**This method returns customerId
     * @return customerId*/
    public int getCustomerId() {
        return customerId;
    }

    /**This method sets customerId
     * @param customerId sets customerId*/
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**This method returns type objects to strings for use in Combo boxes
     * @return type as string*/
    @Override
    public String toString(){
        return (type);
    }
}
