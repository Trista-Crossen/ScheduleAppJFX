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
import sample.helper.ReportInterface;
import sample.helper.TimeInterface;
import sample.model.Appointment;
import sample.model.User;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private String userName;
    private boolean loginSuccessful;
    private LocalDate currentDate = LocalDate.now();
    private LocalTime currentTime = LocalTime.now();
    private Timestamp currentTimeStamp = Timestamp.valueOf(String.valueOf(currentDate + " " + currentTime));
    private LocalTime startTime;
    private long timeDifference;
    private String loginDocumentation;

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
        userName = userNameTextField.getText();
        ObservableList<User> userLogin;
        userLogin = UserDao.getUserInfo();
        for(int i = 0; i < userLogin.size(); i++) {
            User userLoginInfo = userLogin.get(i);

            //Checks time of next upcoming appointment and gives appropriate message before opening app to main records screen if user enters correct login information
            if (userNameTextField.getText().equals(userLoginInfo.getUserName()) && passwordTextField.getText().equals(userLoginInfo.getPassword())) {
                loginSuccessful = true;
                ObservableList<Appointment> upcomingAppointments = FXCollections.observableArrayList();

                //Goes through appointments by userId to see upcoming appointments for that user
                for(int j = 0; j < AppointmentDao.getAllAppointments().size(); j++){
                    Appointment appointment = AppointmentDao.getAllAppointments().get(j);
                    if(appointment.getUserId() == userLoginInfo.getUserId()){
                        if(appointment.getStartTime().isAfter(LocalDateTime.now())){
                            if(appointment.getStartTime().toLocalDate().isEqual(LocalDate.now())){
                                upcomingAppointments.add(appointment);
                            }
                        }
                    }
                }

                //Checks if upcomingAppointments is empty
                if(upcomingAppointments.isEmpty()){
                    Alert noUpcomingAppointments = new Alert(Alert.AlertType.INFORMATION);
                    noUpcomingAppointments.setTitle("No upcoming appointments.");
                    noUpcomingAppointments.setHeaderText(null);
                    noUpcomingAppointments.setContentText("You do not have any appointments scheduled for the future.");
                    noUpcomingAppointments.showAndWait();
                }
                //Checks if first upcoming appointment is within 15 minutes of now
                else{
                    Appointment nextAppointment = upcomingAppointments.get(0);
                    startTime = nextAppointment.getStartTime().toLocalTime();

                    TimeInterface timeDiffCheck = (currentTime, startTime) -> timeDifference = currentTime.compareTo(startTime);
                    timeDiffCheck.timeCalculation(currentTime, startTime);
                    if(timeDifference <= 15){
                        Alert upcomingAppointmentAlert = new Alert(Alert.AlertType.INFORMATION);
                        upcomingAppointmentAlert.setTitle("You have an appointment in" + timeDifference);
                        upcomingAppointmentAlert.setHeaderText(null);
                        upcomingAppointmentAlert.setContentText("Upcoming appointment id: " + nextAppointment.getAppointmentId() + " date and time: " + nextAppointment.getStartTime().toString() + ".");
                        upcomingAppointmentAlert.showAndWait();
                    }
                    else{
                        Alert noUpcomingCurrentAppointments = new Alert(Alert.AlertType.INFORMATION);
                        noUpcomingCurrentAppointments.setTitle("No upcoming appointments.");
                        noUpcomingCurrentAppointments.setHeaderText(null);
                        noUpcomingCurrentAppointments.setContentText("You do not have any appointments starting within the next 15 minutes.");
                        noUpcomingCurrentAppointments.showAndWait();
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
                incorrectLoginInfoAlert.setTitle("Incorrect log-in information was entered");
                incorrectLoginInfoAlert.setContentText("Please make sure to enter a correct username and password to enter the program.");
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
