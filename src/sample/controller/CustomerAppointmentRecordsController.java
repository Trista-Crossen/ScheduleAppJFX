package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.dao.AppointmentDao;
import sample.dao.CustomerDao;
import sample.model.Appointment;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CustomerAppointmentRecordsController implements Initializable {

    public TableView<Customer> allCustomersView;
    public TableColumn<Customer, Integer> customerIdCol;
    public TableColumn<Customer, String> customerNameCol;
    public TableColumn<Customer, String> addressCol;
    public TableColumn<Customer, String> firstLevelDivisionCol;
    public TableColumn<Customer, String> countryCol;
    public TableColumn<Customer, String> postalCodeCol;
    public TableColumn<Customer, String> phoneCol;
    public TableView<Appointment> allAppointmentsView;
    public TableColumn<Appointment, Integer> appointmentIdCol;
    public TableColumn<Appointment, String> titleCol;
    public TableColumn<Appointment, String> descriptionCol;
    public TableColumn<Appointment, String> locationCol;
    public TableColumn<Appointment, String> contactCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, LocalDateTime> startCol;
    public TableColumn<Appointment, LocalDateTime> endCol;
    public TableColumn<Appointment, Integer> customerIdARCol;
    public TableColumn<Appointment, Integer> userIdCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Sets up columns for Customer Table View
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        firstLevelDivisionCol.setCellValueFactory(new PropertyValueFactory<>("firstLevelDivision"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //Calls method to populate customer table view
        allCustomersView.setItems(CustomerDao.getAllCustomers());


        //Sets up columns for Appointment Table View
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdARCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        //Calls method to populate appointment table view
        allAppointmentsView.setItems(AppointmentDao.getAllAppointments());

    }

    public void addCustomerOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/add-customer.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 751, 621);
        stage.setTitle("Add New Customer");

        stage.setScene(scene);
        stage.show();
    }

    public void updateCustomerOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/update-customer.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 751, 621);
        stage.setTitle("Update Customer");

        stage.setScene(scene);
        stage.show();
    }

    public void deleteCustomerOnClick(ActionEvent actionEvent) {
    }

    public void addAppointmentOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/add-appointment.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 804, 612);
        stage.setTitle("Add New Appointment");

        stage.setScene(scene);
        stage.show();
    }

    public void updateAppointmentOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/update-appointment.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 804, 612);
        stage.setTitle("Update Appointment");

        stage.setScene(scene);
        stage.show();
    }

    public void deleteAppointmentOnClick(ActionEvent actionEvent) {
    }

    public void reportsOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/reports.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 872, 741);
        stage.setTitle("Reports");

        stage.setScene(scene);
        stage.show();
    }

    public void exitOnClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}
