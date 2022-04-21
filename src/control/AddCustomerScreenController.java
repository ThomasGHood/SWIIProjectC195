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
    private Button saveButton;
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
        String customerName = customerNameText.getText();
        String address = customerAddressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        Divisions selectedDivision = divisionComboBox.getValue();
        int divisionId = selectedDivision.getDivisionId();

        try {
            CustomerQuery.insert(customerName, address, postalCode, phone, divisionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            CustomerQuery.selectLast();
        } catch (SQLException e){
            e.printStackTrace();
        }


        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }


    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes will not be saved.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }
    }

    // Minor improvement would be to make it so that when the country is changed, it returns the divsion combobox
    // back to "Select Divsion"..
    @FXML
    public void setDivisionComboBox(ActionEvent actionEvent){

        //small bug that causes division prompt to go blank if the country is changed

        String selectCountry = (String) countryComboBox.getSelectionModel().getSelectedItem().getCountryName();

        switch(selectCountry){
            case "U.S":
                divisionComboBox.setItems(ListManager.getAllDivisionsUS());
                divisionComboBox.setPromptText("Select Division");
                break;
            case "UK":
                divisionComboBox.setItems(ListManager.getAllDivisionsUK());
                divisionComboBox.setPromptText("Select Division");
                break;
            case "Canada":
                divisionComboBox.setItems(ListManager.getAllDivisionsCanada());
                divisionComboBox.setPromptText("Select Division");
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdText.setText("Auto-Gen on Creation");

        countryComboBox.setItems(ListManager.getAllCountries());
        countryComboBox.setPromptText("Select Country");


    }
}
