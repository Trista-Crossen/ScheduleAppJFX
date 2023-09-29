package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.dao.AppointmentDao;
import sample.dao.UserDao;
import sample.helper.TimeInterface;
import sample.model.Appointment;
import sample.model.User;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
    private String userName;
    private String password;
    private boolean loginSuccessful;
    private LocalDate currentDate = LocalDate.now();
    private LocalTime currentTime = LocalTime.now();
    private Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(currentDate + " " + currentTime));
    private LocalTime startTime;
    private long timeDifference;
    private String loginDocumentation;
    ResourceBundle rb = ResourceBundle.getBundle("sample/view/Nat", Locale.getDefault());


    /**
     * This method overrides initialize for the screen to perform language based functions
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            String localZoneString = localZoneId.toString();
            zoneIdLabel.setText("ZoneId is: " + localZoneString);
            userNameLabel.setText(rb.getString("Username"));
            passwordLabel.setText(rb.getString("Password"));
            titleLabel.setText(rb.getString("Log-in"));
            enterButton.setText(rb.getString("Enter"));
            zoneIdLabel.setText(rb.getString("zoneId") + " " + localZoneString);
        } catch (Exception localeNotFound) {

        }
    }

    /**
     * This method controls the Enter button
     *
     * @param actionEvent Enter button
     *  I used a lambda in this method for time difference checks, which get the current time and check it against the next upcoming appointment based on userId.
     *  This lambda helped to streamline my code by using a functional interface named TimeInterface to compare the current time with the next appointment's
     *  starting time and returns a long that represents the number of minutes between the two values.
     *  The lambda expression can be found on line 116.
     */
    public void enterSchedulingAppOnClick(ActionEvent actionEvent) throws IOException, SQLException {
        userName = userNameTextField.getText();
        password = passwordTextField.getText();
        ObservableList<User> userLogin;
        userLogin = UserDao.getUserInfo();
        for (int i = 0; i < userLogin.size(); i++) {
            User userLoginInfo = userLogin.get(i);

            //Checks time of next upcoming appointment and gives appropriate message before opening app to main records screen if user enters correct login information
            if (userNameTextField.getText().equals(userLoginInfo.getUserName()) && passwordTextField.getText().equals(userLoginInfo.getPassword())) {
                loginSuccessful = true;
                ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();

                //Goes through appointments by userId to see upcoming appointments for that user
                for (int j = 0; j < AppointmentDao.getAllAppointments().size(); j++) {
                    Appointment appointment = AppointmentDao.getAllAppointments().get(j);
                    if (appointment.getUserId() == userLoginInfo.getUserId()) {
                        if (appointment.getStartTime().isAfter(LocalDateTime.now())) {
                            if (appointment.getStartTime().toLocalDate().isEqual(LocalDate.now())) {
                                upcomingAppointments.add(appointment);
                            }
                        }
                    }
                }

                //Checks if upcomingAppointments is empty
                if (upcomingAppointments.isEmpty()) {
                    try {
                        Alert noUpcomingAppointments = new Alert(Alert.AlertType.INFORMATION);
                        noUpcomingAppointments.setTitle(rb.getString("NoUpcomingApptstitle"));
                        noUpcomingAppointments.setHeaderText(null);
                        noUpcomingAppointments.setContentText(rb.getString("NoUpcomingApptsmessage"));
                        noUpcomingAppointments.showAndWait();
                    }
                    catch (Exception localeNotFound){

                    }
                }

                //Checks if first upcoming appointment is within 15 minutes of now
                else {
                    Appointment nextAppointment = upcomingAppointments.get(0);
                    startTime = nextAppointment.getStartTime().toLocalTime();

                    TimeInterface timeDiffCheck = (currentTime, startTime) -> timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime);
                    timeDiffCheck.timeCalculation(currentTime, startTime);
                    if (timeDifference <= 15) {
                        try {
                            Alert upcomingAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);
                            upcomingAppointmentAlert.setTitle(rb.getString("UpcomingAppttitle") + " " + timeDifference);
                            upcomingAppointmentAlert.setHeaderText(null);
                            upcomingAppointmentAlert.setContentText(rb.getString("UpcomingApptId") + " " + nextAppointment.getAppointmentId() + " " + rb.getString("UpcomingApptTime") + " " + nextAppointment.getStartTime().toString() + rb.getString("minutes") + ".");
                            upcomingAppointmentAlert.showAndWait();
                        }
                        catch (Exception localeNotFound){

                        }
                    } else {
                        try {
                            Alert noUpcomingCurrentAppointments = new Alert(Alert.AlertType.INFORMATION);
                            noUpcomingCurrentAppointments.setTitle(rb.getString("NoApptsIn15title"));
                            noUpcomingCurrentAppointments.setHeaderText(null);
                            noUpcomingCurrentAppointments.setContentText(rb.getString("NoApptsin15message"));
                            noUpcomingCurrentAppointments.showAndWait();
                        }
                        catch (Exception localeNotFound){

                        }
                    }
                }
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
                loginSuccessful = false;
                Alert incorrectLoginInfoAlert = new Alert(Alert.AlertType.ERROR);
                incorrectLoginInfoAlert.setTitle(rb.getString("Errortitle"));
                incorrectLoginInfoAlert.setContentText(rb.getString("Errormessage"));
                incorrectLoginInfoAlert.showAndWait();

                break;
            }
        }

        //Writes login documentation to a text file
        String fileName = "login_activity.txt";

        //Create and open file
        FileWriter outputFile = new FileWriter(fileName, true);
        loginDocumentation = "User name entered: " + userName + " | Date and time of attempt:" + currentTimeStamp + " | login successful: " + loginSuccessful;
        PrintWriter printWriter = new PrintWriter(outputFile);
        printWriter.println(loginDocumentation);

        //Close file
        outputFile.close();
    }
}
