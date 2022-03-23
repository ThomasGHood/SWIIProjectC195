package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.Main;
import utilities.JDBC;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    private Button manageCustomerAppointments;
    @FXML
    private Button logout;
    @FXML
    private Button exitButton;


    @FXML
    public void onActionExit(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit Application?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    @FXML
    public void onActionOpenManagerScreen(ActionEvent actionEvent) throws IOException{

        Main.navigateMenu(actionEvent, "/view/ManagerScreen.fxml");

    }
    @FXML
    public void onActionReturnToLogin(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
            Main.navigateMenu(actionEvent, "/view/LoginScreen.fxml");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
