package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import utilities.JDBC;
import utilities.ListManager;
import utilities.UtilityFunctions;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The LoginScreenController class.
 * This class is the first screen where user login credentials stored in the database are used.
 */
public class LoginScreenController implements Initializable {

    private ResourceBundle translatedText = ResourceBundle.getBundle("LoginLanguage", Locale.getDefault());
    private StringBuilder activityOutput = new StringBuilder();
    @FXML
    private Button cancelButton;
    @FXML
    private Label zoneID;
    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Label locationLabel;


    /**
     * onActionLoadMainMenu method
     * this method closes the login screen and opens the main screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionLoadMainMenu(ActionEvent actionEvent) throws IOException {

        PrintWriter userActivity = new PrintWriter(new FileWriter("login_activity.txt", true));

        LocalDateTime loginDateTime = LocalDateTime.now();
        String loginTimestamp =  Timestamp.valueOf(loginDateTime).toString();
        String validateUserName = userName.getText();
        String validatePassword = password.getText();

        for (User user : ListManager.getAllUsers()){
            if(user.getUserName().equals(validateUserName)){
                if(user.getUserPassword().equals(validatePassword)){
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();
                    UtilityFunctions.navigateMenu(actionEvent, "/view/MainScreen.fxml", "Dashboard");
                    UtilityFunctions.meetingNotification();
                    activityOutput.append(validateUserName).append(" ").append(validatePassword).append(" Successful")
                            .append(" ").append(loginTimestamp).append("\n");
                    userActivity.append(activityOutput);
                    activityOutput.setLength(0);
                    userActivity.close();
                    return;
                } else {
                    // else next username/Password combination will be checked.
                }
            } else {
                //else next userName/Password combination will be checked.
            }
        }
        Alert failedLogin = new Alert(Alert.AlertType.ERROR, translatedText.getString("Username") + " " +
                translatedText.getString("or") + " " + translatedText.getString("password") + " " +
                translatedText.getString("incorrect"));
        failedLogin.showAndWait();

        activityOutput.append(validateUserName).append(" ").append(validatePassword).append(" Unsuccessful")
                .append(" ").append(loginTimestamp).append("\n");
        userActivity.append(activityOutput);
        userActivity.close();
        activityOutput.setLength(0);
    }

    /**
     * onActionCloseScreen method
     * the application is closed.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, translatedText.getString("Exit") + "?");


        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            JDBC.closeConnection();
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loginButton.setText(translatedText.getString("Login"));
        cancelButton.setText(translatedText.getString("Cancel"));
        locationLabel.setText(translatedText.getString("ZoneID") + ":");
        zoneID.setText(ZoneId.systemDefault().toString());
        userNameLabel.setText(translatedText.getString("Username") + ":");
        passwordLabel.setText(translatedText.getString("Password") + ":");
        userName.setPromptText(translatedText.getString("Username"));
        password.setPromptText(translatedText.getString("Password"));

    }
}
