package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Divisions;
import utilities.CustomerQuery;
import utilities.ListManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerScreenController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TextField customerIdText;
    @FXML
    private TextField customerNameText;
    @FXML
    private TextField customerAddressText;
    @FXML
    private TextField postalCodeText;
    @FXML
    private TextField phoneText;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private ComboBox<Divisions> divisionComboBox;

    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        //TODO add ComboBox functionality
        String customerName = customerNameText.getText();
        String address = customerAddressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        //Divisions divisionId = divisionComboBox.getValue();

//        try {
//            CustomerQuery.insert(customerName, address, postalCode, phone, divisionId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        System.out.println(divisionComboBox.getValue());

        try{
            CustomerQuery.selectLast();
        } catch (SQLException e){
            e.printStackTrace();
        }


        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Discard changes?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdText.setText("Auto-Gen on Creation");


        countryComboBox.setItems(ListManager.getAllCountries());
        countryComboBox.setPromptText("Select Country");
        divisionComboBox.setItems(ListManager.getAllDivisions());
        divisionComboBox.setPromptText("Select Division");



    }
}
