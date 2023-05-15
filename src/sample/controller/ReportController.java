package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.dao.AppointmentDao;
import sample.dao.ContactDao;
import sample.dao.CustomerDao;
import sample.helper.Months;
import sample.model.Appointment;
import sample.model.Contact;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //Sets the items in their combo boxes
        monthComboBox.setItems(Months.getMonths());//FIXME: NEED TO GET MONTHS IN COMBO BOX SO I CAN FINISH THIS REPORT
        typeComboBox.setItems(AppointmentDao.getTypes());
        contactComboBox.setItems(ContactDao.getAllContacts());
        customerComboBox.setItems(CustomerDao.getAllCustomers());

        //Sets columns for table view on second report
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
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
        ObservableList<Appointment> appointmentsByTypeAndMonth = FXCollections.observableArrayList();
        int numberOfAppointments = 0;
        Month selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();
        Appointment selectedType = typeComboBox.getSelectionModel().getSelectedItem();
        for(int i = 0; i < AppointmentDao.getAllAppointments().size(); i++){
            Appointment appointment = AppointmentDao.getAllAppointments().get(i);
            if(selectedMonth.equals(appointment.getStartTime().getMonth()) && selectedType.equals(appointment.getType())){
                appointmentsByTypeAndMonth.add(appointment);
                numberOfAppointments++;
            }
        }
        firstReportTxtBox.setText("The number of appointments by month:" + selectedMonth.toString() + " and type: " + selectedType.toString() + " is " + numberOfAppointments);
    }

    public void printSecondReportOnClick(ActionEvent actionEvent) {
        ObservableList<Appointment> appointmentByContact = FXCollections.observableArrayList();
        Contact selectedContact = contactComboBox.getSelectionModel().getSelectedItem();
        int contactId = selectedContact.getContactId();
        for(int i = 0; i < AppointmentDao.getAllAppointments().size(); i++){
            Appointment appointment = AppointmentDao.getAllAppointments().get(i);
            if(appointment.getContactId() == contactId){
                appointmentByContact.add(appointment);
            }
        }
        contactAppointmentTableView.setItems(appointmentByContact);
    }

    public void printThirdReportOnClick(ActionEvent actionEvent) {
        ObservableList<Appointment> appointmentByCustomer = FXCollections.observableArrayList();
        Customer selectedCustomer = customerComboBox.getSelectionModel().getSelectedItem();
        int customerId = selectedCustomer.getCustomerId();
        int appointmentCount = 0;
        for(int i = 0; i < AppointmentDao.getAllAppointments().size(); i++){
            Appointment appointment = AppointmentDao.getAllAppointments().get(i);
            if(appointment.getCustomerId() == customerId){
                appointmentCount += 1;
                appointmentByCustomer.add(appointment);
            }
        }
        thirdReportTextBox.setText("Customer: " + selectedCustomer.getCustomerName() + " has " + appointmentCount + " appointments scheduled.");
    }
}
