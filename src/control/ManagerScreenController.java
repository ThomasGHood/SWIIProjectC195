package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import model.Customer;
import utilities.CustomerQuery;
import utilities.ListManager;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagerScreenController implements Initializable {

    private Customer selectedCustomer; //will need this.

    @FXML
    private Button backButton;
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
        //TODO need to add functionality to block deletion of customer if the customer has appointments
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected customer?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            try {
                CustomerQuery.delete(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ListManager.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem());
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
    public void onActionCloseScreen(ActionEvent actionEvent){
        Stage stage = (Stage) backButton.getScene().getWindow();
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

        customerTableView.setItems(ListManager.getAllCustomers());

    }

}
