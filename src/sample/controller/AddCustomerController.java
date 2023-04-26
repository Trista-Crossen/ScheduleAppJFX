package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dao.CountryDao;
import sample.dao.CustomerDao;
import sample.dao.FirstLevelDivisionDao;
import sample.model.Country;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    public ComboBox countryComboBox;
    public ComboBox firstLevelDivisionComboBox;
    public TextField customerIdTxtField;
    public TextField customerNameTxtField;
    public TextField addressTxtField;
    public TextField postalCodeTxtField;
    public TextField phoneNumberTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting Country items into a combo box
        countryComboBox.setItems(CountryDao.getAllCountries());

        //Setting FirstLevelDivision items into a combo box
        firstLevelDivisionComboBox.setItems(FirstLevelDivisionDao.getFirstLevelDivisions());
    }

    public void saveNewCustomerOnClick(ActionEvent actionEvent) throws IOException {
        //Adds new customer to records


        //Sets scene for Main screen
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

}
