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
import sample.dao.CustomerDao;
import sample.model.Appointment;
import sample.model.Customer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Optional;
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
    public TableColumn<Appointment, Integer> contactCol;
    public TableColumn<Appointment, String> typeCol;
    public TableColumn<Appointment, LocalDateTime> startCol;
    public TableColumn<Appointment, LocalDateTime> endCol;
    public TableColumn<Appointment, Integer> customerIdARCol;
    public TableColumn<Appointment, Integer> userIdCol;
    public Customer selectedCustomer;
    public Appointment selectedAppointment;
    public Button updateCustomerButton;
    public Button updateAppointmentButton;
    public ToggleGroup appointmentRecordsToggleGroup;
    public RadioButton thisWeekRBtn;
    public RadioButton thisMonthRBtn;
    public RadioButton allAppointmentsRBtn;

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
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
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
        if(allCustomersView.getSelectionModel().isEmpty()){
            Alert noCustomerSelectedError = new Alert(Alert.AlertType.ERROR);
            noCustomerSelectedError.setTitle("Error!");
            noCustomerSelectedError.setHeaderText(null);
            noCustomerSelectedError.setContentText("No customer was selected. Please select a customer and try again.");
            noCustomerSelectedError.showAndWait();
        }
        else{
            //Gets customer data from selected customer
            selectedCustomer = allCustomersView.getSelectionModel().getSelectedItem();
            //Loads widget hierarchy of next screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/update-customer.fxml"));
            Parent root = loader.load();

            //Gets access to selected customer data
            UpdateCustomerController updateCustomer = loader.getController();
            updateCustomer.setFields(selectedCustomer);

            Scene scene = new Scene(root, 751, 621);

            //Sets the scene
            Stage stage = (Stage) updateCustomerButton.getScene().getWindow();
            stage.setTitle("Update Customer");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void deleteCustomerOnClick(ActionEvent actionEvent) {
        //Gives error that no Customer is selected
        if(allCustomersView.getSelectionModel().isEmpty()){
            Alert noCustomerSelectedError = new Alert(Alert.AlertType.ERROR);
            noCustomerSelectedError.setTitle("Error!");
            noCustomerSelectedError.setHeaderText(null);
            noCustomerSelectedError.setContentText("No customer was selected! Please select a customer and try again.");

            noCustomerSelectedError.showAndWait();
        }
        else {
            Customer selectedCustomer = allCustomersView.getSelectionModel().getSelectedItem();
            //Checks if customer has an appointments before delete
            ObservableList<Appointment> associatedAppointments = FXCollections.observableArrayList();
            for(int i = 0; i < allAppointmentsView.getItems().size(); i++){
                Appointment appointment = allAppointmentsView.getItems().get(i);
                if(appointment.getCustomerId() == selectedCustomer.getCustomerId()){
                    associatedAppointments.add(appointment);
                }
            }
            if(associatedAppointments.isEmpty()){
                //Dialog box that asks user if they want to delete customer record
                Alert deleteCustomerAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteCustomerAlert.setTitle("Delete this customer?");
                deleteCustomerAlert.setContentText("Are you sure you want to delete this customer record?");

                Optional<ButtonType> result = deleteCustomerAlert.showAndWait();
                if(result.get() == ButtonType.OK){
                    CustomerDao.deleteCustomer(selectedCustomer.getCustomerId());

                    //Dialog box to let user know Customer record was deleted
                    Alert customerDeleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
                    customerDeleteSuccessful.setTitle("Customer record deleted");
                    customerDeleteSuccessful.setHeaderText(null);
                    customerDeleteSuccessful.setContentText("Customer record for " + selectedCustomer.getCustomerId() + " " + selectedCustomer.getCustomerName() + " was successfully deleted.");

                    customerDeleteSuccessful.showAndWait();
                }
                else{
                    //Dialog box that lets user know that they canceled the record deletion
                    Alert deleteCustomerCanceled = new Alert(Alert.AlertType.INFORMATION);
                    deleteCustomerCanceled.setTitle("Customer record not deleted");
                    deleteCustomerCanceled.setHeaderText(null);
                    deleteCustomerCanceled.setContentText("Customer record deletion canceled by user.");

                    deleteCustomerCanceled.showAndWait();
                }
            }
            else{
                //Dialog box that lets user know customerId has appointments tied to it
                Alert customerIdTiedToAppointmentError = new Alert(Alert.AlertType.ERROR);
                customerIdTiedToAppointmentError.setTitle("Unable to delete customer!");
                customerIdTiedToAppointmentError.setHeaderText(null);
                customerIdTiedToAppointmentError.setContentText("Unable to delete this customer from records due to customerId being tied to an appointment. Please delete or update appointments with the corresponding customerId before trying again.");
                customerIdTiedToAppointmentError.showAndWait();
            }
        }
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
        if(allAppointmentsView.getSelectionModel().isEmpty()) {
            Alert noAppointmentSelectedError = new Alert(Alert.AlertType.ERROR);
            noAppointmentSelectedError.setTitle("Error!");
            noAppointmentSelectedError.setHeaderText(null);
            noAppointmentSelectedError.setContentText("No appointment was selected. Please select an appointment and try again.");
            noAppointmentSelectedError.showAndWait();
        }
        else {
            //Gets appointment data from selected appointment
            selectedAppointment = allAppointmentsView.getSelectionModel().getSelectedItem();

            //Loads widget hierarchy of next screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/update-appointment.fxml"));
            Parent root = loader.load();

            //Gets access to selected appointment data
            UpdateAppointmentController updateAppointment = loader.getController();
            updateAppointment.setFields(selectedAppointment);
            Scene scene = new Scene(root, 804, 612);

            //Sets the scene
            Stage stage = (Stage) updateAppointmentButton.getScene().getWindow();
            stage.setTitle("Update Appointment");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void deleteAppointmentOnClick(ActionEvent actionEvent) throws SQLException {
        //Gives error that no appointment is selected
        if(allAppointmentsView.getSelectionModel().isEmpty()){
            Alert noAppointmentSelectedError = new Alert(Alert.AlertType.ERROR);
            noAppointmentSelectedError.setTitle("Error!");
            noAppointmentSelectedError.setHeaderText(null);
            noAppointmentSelectedError.setContentText("No appointment was selected! Please select an appointment and try again.");
            noAppointmentSelectedError.showAndWait();
        }
        else{
            selectedAppointment = allAppointmentsView.getSelectionModel().getSelectedItem();

            //Dialog box that asks user if they want to delete appointment
            Alert deleteAppointmentAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAppointmentAlert.setTitle("Delete this appointment?");
            deleteAppointmentAlert.setContentText("Are you sure you want to delete this appointment record?");
            Optional<ButtonType> result = deleteAppointmentAlert.showAndWait();
            if(result.get() == ButtonType.OK) {
                AppointmentDao.deleteAppointment(selectedAppointment.getAppointmentId());

                //Dialog box that lets user know appointment record was deleted
                Alert appointmentDeleteSuccessful = new Alert(Alert.AlertType.INFORMATION);
                appointmentDeleteSuccessful.setTitle("Appointment record deleted");
                appointmentDeleteSuccessful.setHeaderText(null);
                appointmentDeleteSuccessful.setContentText("Appointment record " + selectedAppointment.getAppointmentId() + " " + selectedAppointment.getType() + " was successfully deleted.");
                appointmentDeleteSuccessful.showAndWait();
            }else{
                //Dialog box that lets user know they canceled the record deletion
                Alert deleteAppointmentCancel = new Alert(Alert.AlertType.INFORMATION);
                deleteAppointmentCancel.setTitle("Appointment record not deleted");
                deleteAppointmentCancel.setHeaderText(null);
                deleteAppointmentCancel.setContentText("Appointment record deletion canceled by user.");
                deleteAppointmentCancel.showAndWait();
            }
        }
        //Clears item selection after delete is complete
        allAppointmentsView.getSelectionModel().clearSelection();
    }

    public void reportsOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/reports.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 872, 741);
        stage.setTitle("Reports");

        stage.setScene(scene);
        stage.show();
    }

    public void onThisWeekSelect(ActionEvent actionEvent) {
        allAppointmentsView.setItems(AppointmentDao.getAllAppointments());
        ObservableList<Appointment> thisWeeksAppointments = FXCollections.observableArrayList();
        for(int i = 0; i < allAppointmentsView.getItems().size(); i++){
            Appointment appointment = allAppointmentsView.getItems().get(i);
            LocalDateTime now = LocalDateTime.now();
            if(now.isBefore(appointment.getStartTime()) && appointment.getStartTime().isBefore(now.plusWeeks(1))){
                thisWeeksAppointments.add(appointment);
            }
        }
        allAppointmentsView.setItems(thisWeeksAppointments);
    }

    public void onThisMonthSelect(ActionEvent actionEvent) {
        allAppointmentsView.setItems(AppointmentDao.getAllAppointments());
        ObservableList<Appointment> thisMonthsAppointments = FXCollections.observableArrayList();
        for(int i = 0; i < allAppointmentsView.getItems().size(); i++){
            Appointment appointment = allAppointmentsView.getItems().get(i);
            LocalDateTime now = LocalDateTime.now();
            if(now.isBefore(appointment.getStartTime()) && appointment.getStartTime().isBefore(now.plusMonths(1))){
                thisMonthsAppointments.add(appointment);
            }
        }
        allAppointmentsView.setItems(thisMonthsAppointments);
    }

    public void onAllAppointmentsSelect(ActionEvent actionEvent) {
        allAppointmentsView.setItems(AppointmentDao.getAllAppointments());
    }
}
