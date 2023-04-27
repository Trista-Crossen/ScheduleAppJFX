package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dao.CountryDao;
import sample.dao.CustomerDao;
import sample.dao.FirstLevelDivisionDao;
import sample.model.Country;
import sample.model.Customer;
import sample.model.FirstLevelDivision;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCustomerController implements Initializable {

    public ComboBox<Country> countryComboBox;
    public ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;
    public TextField customerIdTxtField;
    public TextField customerNameTxtField;
    public TextField addressTxtField;
    public TextField postalCodeTxtField;
    public TextField phoneNumberTxtField;
    private Country selectedCountry;
    private Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting Country items into a combo box
        countryComboBox.setItems(CountryDao.getAllCountries());

        //Setting FirstLevelDivision items into a combo box
        firstLevelDivisionComboBox.setItems(FirstLevelDivisionDao.getFirstLevelDivisions());
    }

    public void setFields(Customer selectedCustomer){
        this.selectedCustomer = selectedCustomer;
        customerIdTxtField.setText(Integer.toString(selectedCustomer.getCustomerId()));
        customerNameTxtField.setText(selectedCustomer.getCustomerName());
        addressTxtField.setText(selectedCustomer.getAddress());
        postalCodeTxtField.setText(selectedCustomer.getPostalCode());
        phoneNumberTxtField.setText(selectedCustomer.getPhoneNumber());
        //FIXME: Need help figuring out how to populate the combo boxes with Customer information
        //firstLevelDivisionComboBox.setSelectionModel(selectedCustomer);
        //countryComboBox.setSelectionModel(selectedCustomer);

    }

    public void saveCustomerUpdateOnClick(ActionEvent actionEvent) throws IOException {
        //Gets data from input fields
        String customerName = customerNameTxtField.getText();
        String address = addressTxtField.getText();
        String postalCode = postalCodeTxtField.getText();
        String phoneNumber = phoneNumberTxtField.getText();
        countryComboBox.getSelectionModel().getSelectedItem();
        firstLevelDivisionComboBox.getSelectionModel().getSelectedItem();

        //Updates the Customer data
        CustomerDao.updateCustomer(selectedCustomer.getCustomerId(), customerName, address, postalCode, phoneNumber, firstLevelDivisionComboBox.getSelectionModel().getSelectedItem().getDivisionId());


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

    public void selectCountryOnClick(ActionEvent actionEvent) {
        //Gets the selected country id to fill the first level division box
        selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
        int countryId = selectedCountry.getCountryId();

        firstLevelDivisionComboBox.setItems(FirstLevelDivisionDao.getFirstLevelDivisions(countryId));
    }
}
