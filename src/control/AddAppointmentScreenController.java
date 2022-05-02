package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import model.Customer;
import model.User;
import utilities.AppointmentQuery;
import utilities.ListManager;
import utilities.UtilityFunctions;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

//TODO add Javadoc comments

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
        Contact selectedContact = contactCombobox.getSelectionModel().getSelectedItem();
        //TODO this has a bug, it isn't throwing an alert when this value is null
        if (contactCombobox.getSelectionModel().isEmpty()){
            Alert emptyField = new Alert(Alert.AlertType.ERROR, "Contact not selected.");
            emptyField.showAndWait();
            return;
        }
        int contactId = selectedContact.getContactId();

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

        if (!endTime.isAfter(startTime)){
            Alert endStart = new Alert(Alert.AlertType.ERROR, "End time must be after Start time");
            endStart.showAndWait();
            return;
        }

        //appointment overlap handling
        for (Appointment appointment : ListManager.getALLCustomerAppointments()){
            if(appointment.getCustomerID() == customerId){
                if ((startTime.isBefore(appointment.getStartTime()) || startTime.isEqual(appointment.getStartTime())) &&
                        (endTime.isAfter(appointment.getStartTime()) && (endTime.isBefore(appointment.getEndTime())
                                || endTime.isEqual(appointment.getEndTime()) ||
                                endTime.isAfter(appointment.getEndTime())))){
                    Alert conflictAlert = new Alert(Alert.AlertType.WARNING,
                            "There is a scheduling conflict with appointment ID [" + appointment.getAppointmentID() +
                                    "]" + "\nPlease verify client available time.");
                    conflictAlert.show();
                    return;
                }
                if (((startTime.isAfter(appointment.getStartTime()) || startTime.isEqual(appointment.getStartTime()))
                        && startTime.isBefore(appointment.getEndTime())) &&
                        (endTime.isAfter(appointment.getStartTime()) && (endTime.isBefore(appointment.getEndTime()) ||
                                endTime.isEqual(appointment.getEndTime()) ||
                                endTime.isAfter(appointment.getEndTime())))){
                    Alert conflictAlert = new Alert(Alert.AlertType.WARNING,
                            "There is a scheduling conflict with appointment ID [" + appointment.getAppointmentID() +
                                    "]" + "\nPlease verify client available time.");
                    conflictAlert.show();
                    return;
                }
            }
        }

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
            UtilityFunctions.refreshQuery();
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
