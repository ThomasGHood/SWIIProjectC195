package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.Divisions;
import utilities.CustomerQuery;
import utilities.ListManager;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The EditCustomerScreenController class.
 * This class is used to update existing Customer and saves changes to the database.
 *
 * @author Thomas Hood
 */
public class EditCustomerScreenController implements Initializable {

    private static Customer selectedCustomer;
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
    private ComboBox<Divisions> divisionsComboBox;

    /**
     * Get selected customer.
     *
     * @param customer the customer
     */
    public static void getSelectedCustomer(Customer customer){
        selectedCustomer = customer;
    }

    /**
     * onActionSaveChanges method
     * validates input, then saves changes to Customer object in the database.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){

        String customerName = customerNameText.getText();
        String address = customerAddressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();
        String customerCountry = (String) countryComboBox.getSelectionModel().getSelectedItem().getCountryName();
        Divisions selectedDivision = divisionsComboBox.getValue();
        int divisionId = selectedDivision.getDivisionId();
        int customerId = selectedCustomer.getCustomerID();

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
            CustomerQuery.update(customerId, customerName, address, postalCode, phone, divisionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        selectedCustomer.setCustomerName(customerName);
        selectedCustomer.setAddress(address);
        selectedCustomer.setPostalCode(postalCode);
        selectedCustomer.setPhone(phone);
        selectedCustomer.setDivisionID(divisionId);
        selectedCustomer.setCountry(customerCountry);

        ListManager.updateCustomer(ListManager.getAllCustomers().indexOf(selectedCustomer), selectedCustomer);

        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * onActionCloseScreen method
     * closes the screen without making any changes.
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
                divisionsComboBox.setItems(ListManager.getAllDivisionsUS());
                divisionsComboBox.setPromptText("Select Division");
                break;
            case "UK":
                divisionsComboBox.setItems(ListManager.getAllDivisionsUK());
                divisionsComboBox.setPromptText("Select Division");
                break;
            case "Canada":
                divisionsComboBox.setItems(ListManager.getAllDivisionsCanada());
                divisionsComboBox.setPromptText("Select Division");
                break;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdText.setText(String.valueOf(selectedCustomer.getCustomerID()));
        customerNameText.setText(selectedCustomer.getCustomerName());
        customerAddressText.setText(selectedCustomer.getAddress());
        postalCodeText.setText(selectedCustomer.getPostalCode());
        phoneText.setText(selectedCustomer.getPhone());
        countryComboBox.setItems(ListManager.getAllCountries());

        for (Divisions divisions : ListManager.getAllDivisions()) {
            if (divisions.getDivisionId() == selectedCustomer.getDivisionID()) {
                divisionsComboBox.setPromptText(divisions.getDivisionName());
            }
        }


        switch (selectedCustomer.getCountry()){
            case "U.S":
                countryComboBox.getSelectionModel().select(0);
                divisionsComboBox.setItems(ListManager.getAllDivisionsUS());
                divisionsComboBox.getSelectionModel().select(selectedCustomer.getDivisionID() - 1);
                break;
            case "UK":
                countryComboBox.getSelectionModel().select(1);
                divisionsComboBox.setItems(ListManager.getAllDivisionsUK());
                divisionsComboBox.getSelectionModel().select(selectedCustomer.getDivisionID() - 101);
                break;
            case "Canada":
                countryComboBox.getSelectionModel().select(2);
                divisionsComboBox.setItems(ListManager.getAllDivisionsCanada());
                divisionsComboBox.getSelectionModel().select(selectedCustomer.getDivisionID() - 60);
                break;
        }

    }
}
