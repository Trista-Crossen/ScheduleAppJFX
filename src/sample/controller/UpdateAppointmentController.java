package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    public ComboBox<Contact> contactComboBox;
    public ComboBox<User> userComboBox;
    public ComboBox<Customer> customerComboBox;
    public DatePicker datePicker;
    public TextField appointmentIdTxtField;
    public TextField titleTxtField;
    public TextField descriptionTxtField;
    public TextField locationTxtField;
    public TextField typeTxtField;
    public ComboBox startTimeComboBox;
    public ComboBox endTimeComboBox;
    private Appointment selectedAppointment;
    private Contact contactOfSelectedAppointment;
    private User userOfSelectedAppointment;
    private Customer customerOfSelectedAppointment;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Getting Contact items into combo box
        contactComboBox.setItems(ContactDao.getAllContacts());

        //Getting User items into combo box
        userComboBox.setItems(UserDao.getUserInfo());

        //Getting Customer items into combo box
        customerComboBox.setItems(CustomerDao.getAllCustomers());

        //FIXME: Get help with timezones. Screens come up, but formatting is weird
        //Getting time combo boxes filled
        startTimeComboBox.setItems(OfficeHoursOfOperation.getStartTime());
        endTimeComboBox.setItems(OfficeHoursOfOperation.getEndTime());

    }

    public void setFields(Appointment selectedAppointment) {
        this.selectedAppointment = selectedAppointment;
        appointmentIdTxtField.setText(Integer.toString(selectedAppointment.getAppointmentId()));
        titleTxtField.setText(selectedAppointment.getTitle());
        descriptionTxtField.setText(selectedAppointment.getDescription());
        locationTxtField.setText(selectedAppointment.getLocation());
        typeTxtField.setText(selectedAppointment.getType());
        //FIXME: Need help getting combo boxes and data picker filled with appointment data
        /*contactComboBox.setSelectionModel(selectedAppointment.getContactId());
        userComboBox.setSelectionModel(selectedAppointment.getUserId());
        customerComboBox.setSelectionModel(selectedAppointment.getCustomerId());
        startTimeComboBox.setSelectionModel(selectedAppointment.getStartTime());
        endTimeComboBox.setSelectionModel(selectedAppointment.getEndTime());*/
    }

    public void saveUpdateOnClick(ActionEvent actionEvent) throws IOException, SQLException {
        String title = titleTxtField.getText();
        String description = descriptionTxtField.getText();
        String location = locationTxtField.getText();
        String type = typeTxtField.getText();
        contactComboBox.getSelectionModel().getSelectedItem();
        userComboBox.getSelectionModel().getSelectedItem();
        customerComboBox.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        LocalTime startTime = (LocalTime) startTimeComboBox.getSelectionModel().getSelectedItem();
        LocalTime endTime = (LocalTime) endTimeComboBox.getSelectionModel().getSelectedItem();
        LocalDateTime start = date.atTime(startTime);
        LocalDateTime end = date.atTime(endTime);

        AppointmentDao.updateAppointment(selectedAppointment.getAppointmentId(), title, description, location, type, start, end, customerComboBox.getSelectionModel().getSelectedItem().getCustomerId(), userComboBox.getSelectionModel().getSelectedItem().getUserId(), contactComboBox.getSelectionModel().getSelectedItem().getContactId());

        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 896, 674);
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }

    public void cancelOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 896, 674);
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }

    public void onStartTimeCombo(ActionEvent actionEvent) {
    }

    public void onEndTimeCombo(ActionEvent actionEvent) {
    }

    public void onContactCombo(ActionEvent actionEvent) {
    }

    public void onUserCombo(ActionEvent actionEvent) {
    }

    public void onCustomerCombo(ActionEvent actionEvent) {
    }

    public void onDatePicker(ActionEvent actionEvent) {
    }
}
