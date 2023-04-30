package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void enterSchedulingAppOnClick(ActionEvent actionEvent) throws IOException {
        
       /* Parent root = FXMLLoader.load(getClass().getResource("/sample/view/customer-appointment-records.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 896, 674);
        stage.setTitle("Customer and Appointment Records");
        stage.setScene(scene);
        stage.show();*/

    }
}
