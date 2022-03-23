package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utilities.JDBC;
import main.Main;

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
    public void onActionLoadMainMenu(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Username or Password incorrect\nContact your system administrator");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            Main.navigateMenu(actionEvent, "/view/MainScreen.fxml");
        }


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

    }
}
