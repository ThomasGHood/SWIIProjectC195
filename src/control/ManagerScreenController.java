package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;
import model.Customer;
import utilities.AppointmentQuery;
import utilities.CustomerQuery;
import utilities.ListManager;
import utilities.UtilityFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The ManagerScreenController class.
 * This screen displays a preview of Customer objects and Appointment objects as well as
 * access to add/update/delete Customers and Appointments.
 *
 * @author Thomas Hood
 */
public class ManagerScreenController implements Initializable {

    private Customer selectedCustomer;
    private Appointment selectedAppointment; // this will be needed

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
    private TableView<Appointment> appointmentsTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsAppointmentIdCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsCustomerIdCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsUserIdCol;
    @FXML
    private TableColumn<Appointment, String> appointmentsTitleCol;
    @FXML
    private TableColumn<Appointment, String> appointmentsDescriptionCol;
    @FXML
    private TableColumn<Appointment, String> appointmentsLocationCol;
    @FXML
    private TableColumn<Appointment, Integer> appointmentsContactCol;
    @FXML
    private TableColumn<Appointment, String> appointmentsTypeCol;
    @FXML
    private TableColumn<Appointment, Time> appointmentsStartTimeCol;
    @FXML
    private TableColumn<Appointment, Time> appointmentsEndTimeCol;


    /**
     * OnActionOpenAddCustomerScreen method
     * opens the AddCustomerScreenController.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionOpenAddCustomerScreen(ActionEvent actionEvent) throws IOException {

        UtilityFunctions.navigateMenu(actionEvent, "/view/AddCustomerScreen.fxml", "Customer Adder");

    }

    /**
     * onActionOpenEditCustomerScreen method
     * opens the EditCustomerScreenController.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionOpenEditCustomerScreen(ActionEvent actionEvent) throws IOException {

        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null){
            EditCustomerScreenController.getSelectedCustomer(customerTableView.getSelectionModel().getSelectedItem());

            UtilityFunctions.navigateMenu(actionEvent, "/view/EditCustomerScreen.fxml", "Customer Editor");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No customer selected!");
            alert.showAndWait();

        }

    }

    /**
     * onActionDeleteSelectedCustomer method
     * after confirmation, the selected customer is removed along with all associated appointments.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionDeleteSelectedCustomer(ActionEvent actionEvent) {

        String deletedName = customerTableView.getSelectionModel().getSelectedItem().getCustomerName();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected customer and all associated appointments?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK) {
            ListManager.getALLCustomerAppointments().removeAll(ListManager.getALLCustomerAppointments());

            try {
                CustomerQuery.delete(customerTableView.getSelectionModel().getSelectedItem().getCustomerID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                AppointmentQuery.select();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            appointmentsTableView.setItems(ListManager.getALLCustomerAppointments());

            ListManager.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem());

        }

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Customer, " + deletedName + " has been deleted!");

        Optional<ButtonType> result2 = alert2.showAndWait();

        if(result2.isPresent() && result2.get() == ButtonType.OK){

        }

        try {
            UtilityFunctions.refreshQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * onActionOpenAddAppointmentScreen method
     * opens AddAppointmentScreen
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionOpenAddAppointmentScreen(ActionEvent actionEvent) throws IOException {

        UtilityFunctions.navigateMenu(actionEvent, "/view/AddAppointmentScreen.fxml", "Appointment Adder");

    }

    /**
     * onActionOpenEditAppointmentScreen method
     * opens the EditAppointmentScreen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionOpenEditAppointmentScreen(ActionEvent actionEvent) throws IOException {

        try {
            UtilityFunctions.refreshQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        selectedAppointment = appointmentsTableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment != null){
            EditAppointmentScreenController.getSelectedAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());

            UtilityFunctions.navigateMenu(actionEvent, "/view/EditAppointmentScreen.fxml", "Appointment Editor");
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No appointment selected!");
            alert.showAndWait();

        }

    }

    /**
     * onActionDeleteSelectedAppointment method
     * selected appointment is deleted after confirmation prompt.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionDeleteSelectedAppointment(ActionEvent actionEvent) {

        int apptId = appointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentID();
        String apptType = appointmentsTableView.getSelectionModel().getSelectedItem().getType();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected appointment?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            try {
                AppointmentQuery.delete(appointmentsTableView.getSelectionModel().getSelectedItem().getAppointmentID());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ListManager.deleteCustomerAppointment(appointmentsTableView.getSelectionModel().getSelectedItem());
            Alert note = new Alert(Alert.AlertType.INFORMATION, "Appointment ID " + apptId + " of type " + apptType +
                    " has been removed!");
            note.showAndWait();
        }
        try {
            UtilityFunctions.refreshQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * onActionCloseScreen method
     * the ManagerScreen is closed.
     *
     * @param actionEvent the action event
     */
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

        appointmentsAppointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointmentsCustomerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        appointmentsUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        appointmentsTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentsDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentsLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentsContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        appointmentsTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentsStartTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentsEndTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        appointmentsTableView.setItems(ListManager.getALLCustomerAppointments());

    }

}
