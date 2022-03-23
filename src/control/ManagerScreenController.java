package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Customer;
import utilities.Managers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagerScreenController implements Initializable {

    private Customer selectedCustomer; //will need this.

    @FXML
    private Button removeCustomerButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn<Customer, Integer> customerIdCol;
    @FXML
    private TableColumn<Customer, String> customerNameCol;
    @FXML
    private TableColumn<Customer, String> customerAddressCol;
    @FXML
    private TableColumn<Customer, String> postalCodeCol;
    @FXML
    private TableColumn<Customer, String> customerPhoneCol;
    @FXML
    private TableColumn<Customer, String> customerCountryCol;
    @FXML
    private TableColumn<Customer, Integer> customerDivisionCol;


    @FXML
    public void onActionOpenAddCustomerScreen(ActionEvent actionEvent) throws IOException {

        Main.navigateMenu(actionEvent, "/view/AddCustomerScreen.fxml");

    }

    @FXML
    public void onActionOpenEditCustomerScreen(ActionEvent actionEvent) throws IOException {

        Main.navigateMenu(actionEvent, "/view/EditCustomerScreen.fxml");

    }

    @FXML
    public void onActionDeleteSelectedCustomer(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected customer?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Managers.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem());
        }

    }

    @FXML
    public void onActionOpenAddAppointmentScreen(ActionEvent actionEvent) throws IOException {

        Main.navigateMenu(actionEvent, "/view/AddAppointmentScreen.fxml");

    }

    @FXML
    public void onActionOpenEditAppointmentScreen(ActionEvent actionEvent) throws IOException {

        Main.navigateMenu(actionEvent, "/view/EditAppointmentScreen.fxml");

    }

    @FXML
    public void onActionDeleteSelectedAppointment(ActionEvent actionEvent) {
        //TODO
    }

    @FXML
    public void onActionSaveChangesCloseScreen(ActionEvent actionEvent){
        //TODO add save function and confirmation prompt
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    public void onActionCloseScreen(ActionEvent actionEvent){
        //TODO add warning prompt
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));

        customerTableView.setItems(Managers.getAllCustomers());

    }

}
