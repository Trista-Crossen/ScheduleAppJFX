package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.dao.DBConnection;
import sample.dao.UserDao;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class LoginController implements Initializable {

    public PasswordField passwordTextField;
    public TextField userNameTextField;
    public Label userNameLabel;
    public Label passwordLabel;
    public Label titleLabel;
    public Button enterButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void enterSchedulingAppOnClick(ActionEvent actionEvent) throws IOException {
        ObservableList<User> userLogin;
        userLogin = UserDao.getUserInfo();
        for(int i = 0; i < userLogin.size(); i++){
            User userLoginInfo = userLogin.get(i);

            if(userNameTextField.getText().equals(userLoginInfo.getUserName()) && passwordTextField.getText().equals(userLoginInfo.getPassword())){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/view/customer-appointment-records.fxml"));
                Parent root = loader.load();

                Scene scene = new Scene(root, 896, 674);

                //Sets the scene
                Stage stage = (Stage) enterButton.getScene().getWindow();
                stage.setTitle("Customer and Appointment Records");
                stage.setScene(scene);
                stage.show();
            }
            else if(userNameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()){
                //FIXME: MAKE ALERT THAT FIELDS ARE EMPTY
            }
            else{
                //FIXME: MAKE ALERT THAT A FIELD HAS INCORRECT LOGIN INFO
            }
        }
    }
}
