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

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

//TODO *Add time restrictions (end time can't be less than start time)
//TODO *Appointment can't overlap with other attendees appointments -- need to trigger a warning for conflict

public class EditAppointmentScreenController implements Initializable {

    private static Appointment selectedAppointment;
    @FXML
    private Button backButton;
    @FXML
    private Button saveButton;
    @FXML
    private TextField appointmentIdText;
    @FXML
    private ComboBox<Contact> contactCombobox;
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

    public static void getSelectedAppointment(Appointment appointment){
        selectedAppointment = appointment;
    }

    @FXML
    public void onActionSaveChanges(ActionEvent actionEvent){
        //TODO
        String appointmentTitle = appointmentTitleText.getText();
        String appointmentDescription = appointmentDescriptionText.getText();
        String appointmentLocation = appointmentLocationText.getText();
        String appointmentType = appointmentTypeText.getText();
        int appointmentId = selectedAppointment.getAppointmentID();
        Customer selectedCustomer = customerIdCombobox.getValue();
        int customerId = selectedCustomer.getCustomerID();
        User selectedUser = userIdCombobox.getValue();
        int userId = selectedUser.getUserID();
        Contact selectedContact = contactCombobox.getValue();
        int contactId = selectedContact.getContactId();
        LocalDateTime startTime = LocalDateTime.of(startEndDatePicker.getValue(),
                LocalTime.of(startTimeHourChoice.getValue(), startTimeMinutesChoice.getValue()));
        LocalDateTime endTime = LocalDateTime.of(startEndDatePicker.getValue(),
                LocalTime.of(endTimeHourChoice.getValue(), endTimeMinutesChoice.getValue()));

        System.out.println(startEndDatePicker.getValue());

        try {
            AppointmentQuery.update(appointmentId, appointmentTitle, appointmentDescription, appointmentLocation, appointmentType, startTime,
                    endTime, customerId, userId, contactId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        selectedAppointment.setTitle(appointmentTitle);
        selectedAppointment.setDescription(appointmentDescription);
        selectedAppointment.setLocation(appointmentLocation);
        selectedAppointment.setType(appointmentType);
        selectedAppointment.setStartTime(startTime);
        selectedAppointment.setEndTime(endTime);
        selectedAppointment.setCustomerID(customerId);
        selectedAppointment.setUserID(userId);
        selectedAppointment.setContactID(contactId);

        ListManager.updateCustomerAppointment(ListManager.getALLCustomerAppointments().indexOf(selectedAppointment), selectedAppointment);

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


        //todo fix the below
        customerIdCombobox.setItems(ListManager.getAllCustomers());
        for (Customer customers : ListManager.getAllCustomers()) {
            if (customers.getCustomerID() == selectedAppointment.getCustomerID()) {
                customerIdCombobox.setValue(customers);
            }
        }

        userIdCombobox.setItems(ListManager.getAllUsers());
        for (User users : ListManager.getAllUsers()) {
            if (users.getUserID() == selectedAppointment.getUserID()) {
                userIdCombobox.setValue(users);
            }
        }

        contactCombobox.setItems(ListManager.getAllContacts());
        for (Contact contact : ListManager.getAllContacts()) {
            if (contact.getContactId() == selectedAppointment.getContactID()) {
                contactCombobox.setValue(contact);
            }
        }


        startEndDatePicker.setValue(selectedAppointment.getStartTime().toLocalDate());

        //Used to extract time to integers to set ComboBox
        Scanner getStartHourMinutes = new Scanner((selectedAppointment.getStartTime()).toLocalTime().toString()).useDelimiter("\\s*:\\s*");
        Scanner getEndHourMinutes = new Scanner((selectedAppointment.getEndTime()).toLocalTime().toString()).useDelimiter("\\s*:\\s*");

        startTimeHourChoice.setItems(ListManager.getAllStartHours());
        startTimeHourChoice.setValue(getStartHourMinutes.nextInt());
        startTimeHourChoice.setVisibleRowCount(5);
        startTimeMinutesChoice.setItems(ListManager.getAllMinutes());
        startTimeMinutesChoice.setValue(getStartHourMinutes.nextInt());
        startTimeMinutesChoice.setVisibleRowCount(5);
        endTimeHourChoice.setItems(ListManager.getAllEndHours());
        endTimeHourChoice.setValue(getEndHourMinutes.nextInt());
        endTimeHourChoice.setVisibleRowCount(5);
        endTimeMinutesChoice.setItems(ListManager.getAllMinutes());
        endTimeMinutesChoice.setValue(getEndHourMinutes.nextInt());
        endTimeMinutesChoice.setVisibleRowCount(5);

        appointmentIdText.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        appointmentTitleText.setText(selectedAppointment.getTitle());
        appointmentDescriptionText.setText(selectedAppointment.getDescription());
        appointmentLocationText.setText(selectedAppointment.getLocation());
        appointmentTypeText.setText(selectedAppointment.getType());

    }
}
