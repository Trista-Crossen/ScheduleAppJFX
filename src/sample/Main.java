package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dao.DBConnection;

import java.sql.SQLException;

import static sample.dao.CustomerDaoImpl.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/log-in-Screen.fxml"));
        primaryStage.setTitle("Log-in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        DBConnection.openConnection();
        launch(args);
        //CustomerDaoImpl.addCustomer("Nicole Crossen", "3250 A Lillian Cv", "72034", "501-305-8127", 3);
        DBConnection.closeConnection();
    }
}
