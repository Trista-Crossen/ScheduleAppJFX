package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sample.model.Appointment;
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
        LocalDateTime startDT = LocalDateTime.of(date, startTime); //LocalDateTime var for time checks
        LocalDateTime endDT = LocalDateTime.of(date, endTime);    //LocalDateTime var for time checks

        //Gets appointments by CustomerId and date into an observable list for time overlap checks
        ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
        for(int i = 0; i < AppointmentDao.getAllAppointments().size(); i++){
            Appointment appointment = AppointmentDao.getAllAppointments().get(i);
            if(appointment.getCustomerId() == customerId && date.isEqual(appointment.getStartTime().toLocalDate())){
                customerAppointments.add(appointment);
            }
        }

        //Exception Handling for Add Appointment
        //Checks if any fields are left empty by user
        if(title.isBlank() || description.isBlank() || location.isBlank() || type.isBlank() || contactComboBox.getSelectionModel().isEmpty() || userComboBox.getSelectionModel().isEmpty() || customerComboBox.getSelectionModel().isEmpty() || startTimeComboBox.getSelectionModel().isEmpty() || endTimeComboBox.getSelectionModel().isEmpty() || datePicker.getValue() == null){
            Alert fieldLeftEmptyError = new Alert(Alert.AlertType.ERROR);
            fieldLeftEmptyError.setTitle("One or more fields left empty");
            fieldLeftEmptyError.setContentText("Please be sure that all text fields in the form have information entered into them before trying to save Appointment again.");
            fieldLeftEmptyError.showAndWait();
            return;
        }
        //Checks that start time is before end time
        else if(startDT.isEqual(endDT) || startDT.isAfter(endDT)){
            Alert startDTEndDTError = new Alert(Alert.AlertType.ERROR);
            startDTEndDTError.setTitle("Error!");
            startDTEndDTError.setHeaderText(null);
            startDTEndDTError.setContentText("Start time is set after end time. Please make sure that start time is set to before end time.");
            startDTEndDTError.showAndWait();
            return;
        }
        //Checks customerAppointment list to see if times overlap
        else if(!customerAppointments.isEmpty()){
            for(int i = 0; i < customerAppointments.size(); i++){
                Appointment timeCheck = customerAppointments.get(i);
                if (startDT.isEqual(timeCheck.getEndTime()) || endDT.isEqual(timeCheck.getStartTime())) {
                    continue;
                }
                else if((startDT.isAfter(timeCheck.getStartTime()) || startDT.isEqual(timeCheck.getStartTime())) && startDT.isBefore(timeCheck.getEndTime())){
                    Alert timeOverlapError = new Alert(Alert.AlertType.ERROR);
                    timeOverlapError.setTitle("Error! Time slot not available!");
                    timeOverlapError.setHeaderText(null);
                    timeOverlapError.setContentText("Time slot is not available for the selected customer. Please select a different time slot and try again.");
                    timeOverlapError.showAndWait();
                    return;
                }
                else if(endDT.isAfter(timeCheck.getStartTime()) && (endDT.isBefore(timeCheck.getEndTime()) || endDT.isEqual(timeCheck.getEndTime()))){
                    Alert timeOverlapError = new Alert(Alert.AlertType.ERROR);
                    timeOverlapError.setTitle("Error! Time slot not available!");
                    timeOverlapError.setHeaderText(null);
                    timeOverlapError.setContentText("Time slot is not available for the selected customer. Please select a different time slot and try again.");
                    timeOverlapError.showAndWait();
                    return;
                }
                else if((startDT.isBefore(timeCheck.getStartTime()) || startDT.isEqual(timeCheck.getStartTime())) && (endDT.isEqual(timeCheck.getEndTime()) || endDT.isAfter(timeCheck.getEndTime()))) {
                    Alert timeOverlapError = new Alert(Alert.AlertType.ERROR);
                    timeOverlapError.setTitle("Error! Time slot not available!");
                    timeOverlapError.setHeaderText(null);
                    timeOverlapError.setContentText("Time slot is not available for the selected customer. Please select a different time slot and try again.");
                    timeOverlapError.showAndWait();
                    return;
                }
            }
        }
        else {
            //Saves the new Appointment to records if it passes all exception handling
            AppointmentDao.addAppointment(title, description, location,type,gmtStartTime, gmtEndTime, customerId, userId, contactId);

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
