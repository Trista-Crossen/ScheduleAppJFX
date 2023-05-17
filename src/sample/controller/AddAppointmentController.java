package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dao.AppointmentDao;
import sample.dao.ContactDao;
import sample.dao.CustomerDao;
import sample.dao.UserDao;
import sample.helper.OfficeHoursOfOperation;
import sample.model.Contact;
import sample.model.Customer;
import sample.model.User;
import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**This class controls all the components of the Add Appointment screen of the application*/
public class AddAppointmentController implements Initializable {
    public ComboBox<Contact> contactComboBox;
    public ComboBox<User> userComboBox;
    public ComboBox<Customer> customerComboBox;
    public DatePicker datePicker;
    public TextField titleTxtField;
    public TextField descriptionTxtField;
    public TextField locationTxtField;
    public TextField typeTxtField;
    public ComboBox<LocalTime> startTimeComboBox;
    public ComboBox<LocalTime> endTimeComboBox;

    /**This method overrides initialize for the screen
     * @param resourceBundle
     * @param url */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Getting Contact items into combo box
        contactComboBox.setItems(ContactDao.getAllContacts());

        //Getting User items into combo box
        userComboBox.setItems(UserDao.getUserInfo());

        //Getting Customer items into combo box
        customerComboBox.setItems(CustomerDao.getAllCustomers());

        //Getting the time set up for the time combo boxes
        startTimeComboBox.setItems(OfficeHoursOfOperation.getStartTime());
        endTimeComboBox.setItems(OfficeHoursOfOperation.getEndTime());


    }

    /**This method controls the save appointment button on the screen
     * @param actionEvent Save button*/
    public void saveNewAppointmentOnClick(ActionEvent actionEvent) throws IOException{
        //Adds new appointment to records
        String title = titleTxtField.getText();
        String description = descriptionTxtField.getText();
        String location = locationTxtField.getText();
        String type = typeTxtField.getText();
        int contactId = contactComboBox.getSelectionModel().getSelectedItem().getContactId();
        int userId = userComboBox.getSelectionModel().getSelectedItem().getUserId();
        int customerId = customerComboBox.getSelectionModel().getSelectedItem().getCustomerId();
        LocalDate date = datePicker.getValue();
        ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
        ZoneId gmtZoneId = ZoneId.of("GMT");
        LocalTime startTime = startTimeComboBox.getSelectionModel().getSelectedItem();
        ZonedDateTime localStartTime = ZonedDateTime.of(date, startTime, localZoneId);
        Instant localStartToGMTInstant = localStartTime.toInstant();
        ZonedDateTime gmtStartTimeZDT = localStartToGMTInstant.atZone(gmtZoneId);
        LocalDateTime gmtStartTime = gmtStartTimeZDT.toLocalDateTime();
        LocalTime endTime = endTimeComboBox.getSelectionModel().getSelectedItem();
        ZonedDateTime localEndTime = ZonedDateTime.of(date, endTime, localZoneId);
        Instant localEndToGMTInstant = localEndTime.toInstant();
        ZonedDateTime gmtEndTimeZDT = localEndToGMTInstant.atZone(gmtZoneId);
        LocalDateTime gmtEndTime = gmtEndTimeZDT.toLocalDateTime();

        //Checks if any fields are left empty by user
        if(title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank() || contactComboBox.getSelectionModel().isEmpty() || userComboBox.getSelectionModel().isEmpty() || customerComboBox.getSelectionModel().isEmpty() || startTimeComboBox.getSelectionModel().isEmpty() || endTimeComboBox.getSelectionModel().isEmpty() || datePicker.getValue() == null){
            Alert fieldLeftEmptyError = new Alert(Alert.AlertType.ERROR);
            fieldLeftEmptyError.setTitle("One or more fields left empty");
            fieldLeftEmptyError.setContentText("Please be sure that all text fields in the form have information entered into them before trying to save Appointment again.");
            fieldLeftEmptyError.showAndWait();
            return;
        }
        //Saves the new Appointment to records if it passes all exception handling
        else{
            AppointmentDao.addAppointment(title, description, location, type, gmtStartTime, gmtEndTime, contactId, userId, customerId);

            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 896, 674);
            stage.setTitle("Customer and Appointment Records");

            stage.setScene(scene);
            stage.show();
        }
    }

    /**This method controls the cancel button on the screen
     * @param actionEvent Cancel button*/
    public void cancelOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 896, 674);
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }
}
