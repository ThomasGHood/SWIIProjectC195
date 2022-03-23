package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class EditCustomerScreenController implements Initializable {

    @FXML
    private Button backButton;

    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        //TODO
    }
    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
