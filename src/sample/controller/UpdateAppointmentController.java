package sample.controller;

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
import sample.dao.ContactDao;
import sample.dao.CustomerDao;
import sample.dao.UserDao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable {

    public ComboBox contactComboBox;
    public ComboBox userComboBox;
    public ComboBox customerComboBox;
    public DatePicker datePicker;
    public TextField appointmentIdTxtField;
    public TextField titleTxtField;
    public TextField discriptionTxtField;
    public TextField locationTxtField;
    public TextField typeTxtField;
    public ComboBox startTimeComboBox;
    public ComboBox endTimeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Getting Contact items into combo box
        contactComboBox.setItems(ContactDao.getAllContacts());

        //Getting User items into combo box
        userComboBox.setItems(UserDao.getUserInfo());

        //Getting Customer items into combo box
        customerComboBox.setItems(CustomerDao.getAllCustomers());

        //Getting the time set up for the time combo boxes
        LocalTime officeOpen = LocalTime.of(6, 0);
        LocalTime officeClose = LocalTime.of(20, 0);

        while (officeOpen.isBefore(officeClose.plusSeconds(1))) {
            startTimeComboBox.getItems().add(officeOpen);
            officeOpen = officeOpen.plusHours(1);

            endTimeComboBox.getItems().add(officeOpen.minusHours(1));
        }

    }

    public void saveUpdateOnClick(ActionEvent actionEvent) throws IOException {
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
