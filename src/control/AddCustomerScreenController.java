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

/**
 * The AddCustomerScreenController Class.
 * Adds a new user specified Customer object to the database.
 *
 * @author Thomas Hood
 */
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

    /**
     * onActionSaveChanges method
     * input is validated before saving the new Customer object to the database.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        String customerName = customerNameText.getText();
        String address = customerAddressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        Divisions selectedDivision = divisionComboBox.getValue();
        int divisionId = selectedDivision.getDivisionId();

        if (customerName.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Invalid entry:\nCustomer Name cannot be blank.");
            emptyField.showAndWait();
            return;
        }
        if (address.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Invalid entry:\nAddress cannot be blank");
            emptyField.showAndWait();
            return;
        }
        if (postalCode.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Invalid entry:\nPostal Code cannot be blank");
            emptyField.showAndWait();
            return;
        }
        if (phone.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Invalid entry:\nPhone cannot be blank");
            emptyField.showAndWait();
            return;
        }
        if (divisionId == 0){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Contact not selected.");
            emptyField.showAndWait();
            return;
        }

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


    /**
     * onActionCloseScreen method
     * screen is closed without making any changes.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes will not be saved.");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * setDivisionComboBox method
     * this method is used to populate the divsionComboBox.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void setDivisionComboBox(ActionEvent actionEvent){

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
