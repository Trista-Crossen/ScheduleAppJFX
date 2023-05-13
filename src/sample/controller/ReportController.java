package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.dao.ContactDao;
import sample.dao.CustomerDao;
import sample.model.Appointment;
import sample.model.Contact;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportController {
    public Button firstReportButton;
    public Button secondReportButton;
    public Button thirdReportButton;
    public TableView<Appointment> contactAppointmentTableView;
    public TableColumn<Appointment, Integer> appointmentIdColumn;
    public TableColumn<Appointment, String> titleColumn;
    public TableColumn<Appointment, String> typeColumn;
    public TableColumn<Appointment, String> descriptionColumn;
    public TableColumn<Appointment, LocalDateTime> startTimeColumn;
    public TableColumn<Appointment, LocalDateTime> endTimeColumn;
    public TableColumn<Appointment, Integer> customerIdColumn;
    public TextArea firstReportTxtBox;
    public TextArea thirdReportTextBox;
    public ComboBox<Customer> customerComboBox;
    public ComboBox<Month> monthComboBox;
    public ComboBox<Appointment> typeComboBox;
    public ComboBox<Contact> contactComboBox;


    public void initialize(URL url, ResourceBundle resourceBundle){
        //Sets the items in their combo boxes
        contactComboBox.setItems(ContactDao.getAllContacts());
        customerComboBox.setItems(CustomerDao.getAllCustomers());
    }

    public void backToMainScreenOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Scene scene = new Scene(root, 896, 674);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }

    public void printFirstReportOnClick(ActionEvent actionEvent) {
    }

    public void printSecondReportOnClick(ActionEvent actionEvent) {
    }

    public void printThirdReportOnClick(ActionEvent actionEvent) {
    }
}
