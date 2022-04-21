package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import model.Contact;
import model.Customer;
import model.User;
import utilities.AppointmentQuery;
import utilities.ListManager;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

//TODO *Add time restrictions (end time can't be less than start time)
//TODO *Appointment can't overlap with other attendees appointments -- need to trigger a warning for conflict

public class AddAppointmentScreenController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private ComboBox<Customer> customerIdCombobox;
    @FXML
    private ComboBox<User> userIdCombobox;
    @FXML
    private TextField appointmentTitleText;
    @FXML
    private TextField appointmentDescriptionText;
    @FXML
    private TextField appointmentLocationText;
    @FXML
    private ComboBox<Contact> contactCombobox;
    @FXML
    private TextField appointmentTypeText;
    @FXML
    private DatePicker startEndDatePicker;
    @FXML
    private ComboBox<Integer> startTimeHourChoice;
    @FXML
    private ComboBox<Integer> startTimeMinutesChoice;
    @FXML
    private ComboBox<Integer> endTimeHourChoice;
    @FXML
    private ComboBox<Integer> endTimeMinutesChoice;


    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        //TODO timezones
        String appointmentTitle = appointmentTitleText.getText();
        String appointmentDescription = appointmentDescriptionText.getText();
        String appointmentLocation = appointmentLocationText.getText();
        String appointmentType = appointmentTypeText.getText();
        Customer selectedCustomer = customerIdCombobox.getValue();
        int customerId = selectedCustomer.getCustomerID();
        User selectedUser = userIdCombobox.getValue();
        int userId = selectedUser.getUserID();
        Contact selectedContact = contactCombobox.getValue();
        int contactId = selectedContact.getContactId();
        int startTimeMinutes = startTimeMinutesChoice.getValue();
        int startTimeHours = startTimeHourChoice.getValue();
        int endTimeMinutes = endTimeMinutesChoice.getValue();
        int endTimeHours = endTimeHourChoice.getValue();

        if (startTimeHours > endTimeHours || (startTimeHours == endTimeHours && startTimeMinutes > endTimeMinutes)){
            Alert timeConflict = new Alert(Alert.AlertType.ERROR, "Start time cannot be greater than the end time.");
            timeConflict.showAndWait();
            return;
        }
        if (appointmentTitle.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Appointment Title cannot be blank.");
            emptyField.showAndWait();
            return;
        }
        if (appointmentDescription.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Appointment Description cannot be blank.");
            emptyField.showAndWait();
            return;
        }
        if (appointmentLocation.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Appointment Location cannot be blank.");
            emptyField.showAndWait();
            return;
        }
        if (appointmentType.isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Appointment Type cannot be blank.");
            emptyField.showAndWait();
            return;
        }
        if (contactId == 0){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Contact not selected.");
            emptyField.showAndWait();
            return;
        }
        if (customerId == 0){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Customer not selected.");
            emptyField.showAndWait();
            return;
        }
        if (userId == 0){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "User not selected.");
            emptyField.showAndWait();
            return;
        }


        LocalDateTime startTime = LocalDateTime.of(startEndDatePicker.getValue(),
                LocalTime.of(startTimeHourChoice.getValue(), startTimeMinutesChoice.getValue()));
        LocalDateTime endTime = LocalDateTime.of(startEndDatePicker.getValue(),
                LocalTime.of(endTimeHourChoice.getValue(), endTimeMinutesChoice.getValue()));

        System.out.println(startEndDatePicker.getValue());

        try {
            AppointmentQuery.insert(appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime,
                    endTime, customerId, userId, contactId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            AppointmentQuery.selectLast();
        } catch (SQLException e){
            e.printStackTrace();
        }

        try {
            Main.refreshQuery();
        } catch (SQLException e) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customerIdCombobox.setItems(ListManager.getAllCustomers());
        customerIdCombobox.setPromptText("ID | Name");
        userIdCombobox.setItems(ListManager.getAllUsers());
        userIdCombobox.setPromptText("ID | Name");
        contactCombobox.setItems(ListManager.getAllContacts());
        contactCombobox.setPromptText("ID | Name");


        startTimeHourChoice.setItems(ListManager.getAllStartHours());
        startTimeHourChoice.setValue(8);
        startTimeHourChoice.setVisibleRowCount(5);
        startTimeMinutesChoice.setItems(ListManager.getAllMinutes());
        startTimeMinutesChoice.setValue(0);
        startTimeMinutesChoice.setVisibleRowCount(5);
        endTimeHourChoice.setItems(ListManager.getAllEndHours());
        endTimeHourChoice.setValue(8);
        endTimeHourChoice.setVisibleRowCount(5);
        endTimeMinutesChoice.setItems(ListManager.getAllMinutes());
        endTimeMinutesChoice.setValue(0);
        endTimeMinutesChoice.setVisibleRowCount(5);


    }
}
