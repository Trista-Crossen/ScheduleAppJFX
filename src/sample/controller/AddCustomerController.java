package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dao.CountryDao;
import sample.dao.CustomerDao;
import sample.dao.FirstLevelDivisionDao;
import sample.model.Country;
import sample.model.FirstLevelDivision;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**This class controls all the components on the Add Customer screen*/
public class AddCustomerController implements Initializable {
    public ComboBox<Country> countryComboBox;
    public ComboBox<FirstLevelDivision> firstLevelDivisionComboBox;
    public TextField customerNameTxtField;
    public TextField addressTxtField;
    public TextField postalCodeTxtField;
    public TextField phoneNumberTxtField;
    public Button countryOkButton;
    public Country selectedCountry;

    /**This method overrides initialize on the screen
     * @param url
     * @param resourceBundle */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setting Country items into a combo box
        countryComboBox.setItems(CountryDao.getAllCountries());
    }

    /**This method controls the save button
     * @param actionEvent Save button*/
    public void saveNewCustomerOnClick(ActionEvent actionEvent) throws IOException {
        //Adds new customer to records
        String customerName = customerNameTxtField.getText();
        String address = addressTxtField.getText();
        String postalCode = postalCodeTxtField.getText();
        String phoneNumber = phoneNumberTxtField.getText();
        int division = firstLevelDivisionComboBox.getSelectionModel().getSelectedItem().getDivisionId();

        if(customerName.isBlank() || address.isBlank() || postalCode.isBlank() || phoneNumber.isBlank() || firstLevelDivisionComboBox.getSelectionModel().isEmpty()){
            //Dialog box to let user know that fields were left empty
            Alert blankFieldsError = new Alert(Alert.AlertType.ERROR);
            blankFieldsError.setTitle("Error!");
            blankFieldsError.setHeaderText(null);
            blankFieldsError.setContentText("Please be sure that all required fields are filled before trying to save customer again.");

            blankFieldsError.showAndWait();
            return;
        }
        else{
            CustomerDao.addCustomer(customerName, address, postalCode, phoneNumber, division);

            //Sets scene for Main screen
            Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            Scene scene = new Scene(root, 896, 674);
            stage.setTitle("Customer and Appointment Records");

            stage.setScene(scene);
            stage.show();
        }
    }

    /**This method controls the cancel button
     * @param actionEvent Cancel button*/
    public void cancelOnClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 896, 674);
        stage.setTitle("Customer and Appointment Records");

        stage.setScene(scene);
        stage.show();
    }

    /**This method gets FirstLevelDivisions based on countryId
     * @param actionEvent Select button*/
    public void selectCountryOnClick(ActionEvent actionEvent) {
        //Gets the selected country id to fill the first level division box
       selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
       int countryId = selectedCountry.getCountryId();

       firstLevelDivisionComboBox.setItems(FirstLevelDivisionDao.getFirstLevelDivisions(countryId));
    }
}
