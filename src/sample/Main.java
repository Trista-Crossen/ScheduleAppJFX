package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.dao.*;
import java.sql.SQLException;


/**This is the main class for the application. It is responsible for launching the app.
 * My first lambda can be found in the LoginController class under the enterSchedulingAppOnClick method.
 * My second lambda can be found in the ReportController class under the printFirstReportOnClick method*/
public class Main extends Application {

    /**This method overrides start so the app opens on the login screen
     * @param primaryStage */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/log-in-Screen.fxml"));
        primaryStage.setTitle("Log-in");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    /**This is the main method that connects to the database, launches the app, and closes the database connection when the app is closed.
     * @param args */
    public static void main(String[] args) throws SQLException {
        DBConnection.openConnection();

        launch(args);

        DBConnection.closeConnection();

    }
}
