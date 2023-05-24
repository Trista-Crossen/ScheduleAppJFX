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
import sample.helper.MonthList;
import sample.helper.ReportInterface;
import sample.model.Appointment;
import sample.model.Contact;
import sample.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ResourceBundle;

/**This class controls the Reports screen*/
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
    public ComboBox<Customer> customerComboBox;
    public ComboBox<Month> monthComboBox;
    public ComboBox<Appointment> typeComboBox;
    public ComboBox<Contact> contactComboBox;
    public Label firstReportLabel;
    public Label thirdReportLabel;
    private String type;
    private Month month;
    private String reportText;

    /**Overridden initialize method
     * @param resourceBundle
     * @param url */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        //Sets the items in their combo boxes
        monthComboBox.setItems(MonthList.getMonths());
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

    /**This method controls the button to take user back to the Records screen
     * @param actionEvent Back button*/
    public void backToMainScreenOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Scene scene = new Scene(root, 896, 674);

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }

    /**This method controls the button to print the 1st report given a month and appointment type
     * @param actionEvent View Report button*/
    public void printFirstReportOnClick(ActionEvent actionEvent) {
        int numberOfAppointments = 0;
        Month selectedMonth = monthComboBox.getSelectionModel().getSelectedItem();
        Appointment selectedType = typeComboBox.getSelectionModel().getSelectedItem();
        type = selectedType.toString();
        month = selectedMonth;
        for(int i = 0; i < AppointmentDao.getAllAppointments().size(); i++) {
            Appointment appointment = AppointmentDao.getAllAppointments().get(i);
            Month month = appointment.getStartTime().getMonth();
            String type = appointment.getType();
            if (selectedMonth.equals(month) && selectedType.toString().equals(type)) {
                numberOfAppointments++;
            }
        }
        //FIXME: GET THE STRING TO PRINT OUT PROPERLY WITH HELP DURING LIS SUNDAY
        ReportInterface printReport = (month, type) -> "The number of appointments by month: " + month + " and type: " + type + " is ";
        printReport.monthTypeReport(month, type);
        firstReportLabel.setText(printReport.monthTypeReport(month, type) + " " + numberOfAppointments);
    }

    /**This method controls the button to pull data into the table view for the 2nd report given a contact
     * @param actionEvent View Report button*/
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

    /**This method controls the button to print the 3rd report given a customer
     * @param actionEvent View Report button*/
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
        thirdReportLabel.setText("Customer: " + selectedCustomer.getCustomerName() + " has " + appointmentCount + " appointments scheduled.");
    }
}
