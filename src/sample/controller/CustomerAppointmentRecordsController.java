package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dao.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAppointmentRecordsController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
