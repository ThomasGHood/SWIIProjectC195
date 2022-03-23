package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import utilities.Managers;

import java.net.URL;
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
    private ComboBox<Customer> countryComboBox;
    @FXML
    private ComboBox<Customer> divisionComboBox;

    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        //TODO change ID collection, and ComboBox functionality
        int customerId = Managers.getCustomerID();
        String customerName = customerNameText.getText();
        String address = customerAddressText.getText();
        String postalCode = postalCodeText.getText();
        String phone = phoneText.getText();

        Managers.addCustomer(new Customer(customerId, customerName, address, postalCode, phone, "USA", 13));
    }

    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){

        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdText.setText("Auto Gen- Disabled");

    }
}
