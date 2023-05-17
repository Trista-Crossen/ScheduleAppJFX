package sample.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.dao.UserDao;
import sample.model.User;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**This class controls the components of the Login screen of the application*/
public class LoginController implements Initializable {
    public PasswordField passwordTextField;
    public TextField userNameTextField;
    public Label userNameLabel;
    public Label passwordLabel;
    public Label titleLabel;
    public Button enterButton;
    public Label zoneIdLabel;
    ZoneId localZoneId = ZoneId.of(TimeZone.getDefault() .getID());
    String localZoneString = localZoneId.toString();

    /**This method overrides initialize for the screen to perform language based functions*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            zoneIdLabel.setText("ZoneId is: " + localZoneString);
            ResourceBundle rb = ResourceBundle.getBundle("sample/view/Nat", Locale.getDefault());

            if(Locale.getDefault().getLanguage().equals("fr")){
                userNameLabel.setText(rb.getString("Username"));
                passwordLabel.setText(rb.getString("Password"));
                titleLabel.setText(rb.getString("Log-in"));
                enterButton.setText(rb.getString("Enter"));
                zoneIdLabel.setText(rb.getString("zoneId"));
            }
        }catch(Exception localeNotFound){

        }
    }

    /**This method controls the Enter button
     * @param actionEvent Enter button*/
    public void enterSchedulingAppOnClick(ActionEvent actionEvent) throws IOException {
        ObservableList<User> userLogin;
        userLogin = UserDao.getUserInfo();
        for(int i = 0; i < userLogin.size(); i++) {
            User userLoginInfo = userLogin.get(i);

            //Opens app to main records screen if user enters correct login information
            if (userNameTextField.getText().equals(userLoginInfo.getUserName()) && passwordTextField.getText().equals(userLoginInfo.getPassword())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/customer-appointment-records.fxml"));

                Parent root = loader.load();

                Scene scene = new Scene(root, 896, 674);

                //Sets the scene
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.setTitle("Customer and Appointment Records");
                stage.setScene(scene);
                stage.show();
                break;
            }
            //Lets user know they entered incorrect login information
            else if (!userNameTextField.getText().equals(userLoginInfo.getUserName()) || !passwordTextField.getText().equals(userLoginInfo.getPassword())) {
                Alert incorrectLoginInfoAlert = new Alert(Alert.AlertType.ERROR);
                incorrectLoginInfoAlert.setTitle("Incorrect log-in information was entered");
                incorrectLoginInfoAlert.setContentText("Please make sure to enter a correct username and password to enter the program.");
                incorrectLoginInfoAlert.showAndWait();
                break;
            }
        }
    }
}
