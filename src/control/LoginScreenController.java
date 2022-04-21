package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import utilities.JDBC;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// TODO login screen needs to determine the user's location (ZoneID) displayed as a
// label on the login screen
// Needs to display the login screen in English or French based on the user's computer
// Language settings to translate all text, labels, buttons, and errors on the form
// automatically translates error messages to English or French

public class LoginScreenController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label zoneID;
    @FXML
    private Button loginButton;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    @FXML
    public void onActionLoadMainMenu(ActionEvent actionEvent) throws IOException {

        Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.close();
                Main.navigateMenu(actionEvent, "/view/MainScreen.fxml", "Dashboard");

        //TODO uncomment to add username/password --> must be updated to allow for username/password pairs to be correct
//        for (User user : ListManager.getAllUsers()){
//            if(user.getUserName().equals(userName.getText()) && user.getUserPassword().equals(password.getText())){
//                Stage stage = (Stage) loginButton.getScene().getWindow();
//                stage.close();
//                Main.navigateMenu(actionEvent, "/view/MainScreen.fxml");
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "Username or Password incorrect\nContact your system administrator");
//                Optional<ButtonType> result = alert.showAndWait();
//            }
//        }
    }

    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit Application?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK)
        {
            JDBC.closeConnection();
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        zoneID.setText("USA");
        userName.setPromptText("Username");
        password.setPromptText("Password");


    }
}
