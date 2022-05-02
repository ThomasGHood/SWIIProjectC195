package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.*;
import utilities.JDBC;
import utilities.ListManager;
import utilities.UtilityFunctions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * The MainScreenController class.
 * This class is the user's "dashboard" where reports can be viewed.
 */
public class MainScreenController implements Initializable {


    private static int selectedContactID;
    @FXML
    private TextArea userNotificationsField;
    @FXML
    private Button manageCustomerAppointments;
    @FXML
    private Button weekSelection;
    @FXML
    private Button monthSelection;
    @FXML
    private Button logout;
    @FXML
    private Button exitButton;
    @FXML
    private TableView<Appointment> reportTableView;
    @FXML
    private TableColumn<Appointment, Integer> appointmentIdCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, Integer> contactCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> startDateTimeCol;
    @FXML
    private TableColumn<Appointment, LocalDateTime> endDateTimeCol;
    @FXML
    private TableColumn<Appointment, Integer> customerIdCol;
    @FXML
    private TableColumn<Appointment, Integer> userIdCol;


    /**
     * Get selected contact id.
     *
     * @param contactID the contact id
     */
    public static void getSelectedContactID(int contactID){
        selectedContactID = contactID;
    }

    /**
     * onActionExit method
     * the application is closed.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void onActionExit(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit Application?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    /**
     * OnActionOpenManagerScreen method
     * opens the manager screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionOpenManagerScreen(ActionEvent actionEvent) throws IOException{

        UtilityFunctions.navigateMenu(actionEvent, "/view/ManagerScreen.fxml", "Customer Appointment Manager");

    }

    /**
     * onActionReturnToLogin method
     * exits the dashboard and returns to the Login screen.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    @FXML
    public void onActionReturnToLogin(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout?");

        Optional<ButtonType> result = alert.showAndWait();

        if(result.isPresent() && result.get() == ButtonType.OK){
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.close();
            UtilityFunctions.navigateMenu(actionEvent, "/view/LoginScreen.fxml", "Login");
        }

    }


    /**
     * reportWeek method
     * displays appointments of the week.
     *
     * @param mouseEvent the mouse event
     * @throws SQLException the sql exception
     */
    public void reportWeek(MouseEvent mouseEvent) throws SQLException {
        //TODO needs to update when time changes or appointment is added
        UtilityFunctions.refreshQuery();
        reportTableView.setItems(ListManager.getAllWeekReport());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

    }

    /**
     * reportMonth method
     * displays appointments of the month.
     *
     * @param mouseEvent the mouse event
     * @throws SQLException the sql exception
     */
    public void reportMonth(MouseEvent mouseEvent) throws SQLException {
        //TODO This needs to update when time changes or appointment is added
        UtilityFunctions.refreshQuery();
        reportTableView.setItems(ListManager.getAllMonthReport());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));

    }

    /**
     * onActionResetAppointmentsTableView method
     * resets the TableView to display all appointments.
     *
     * @param event the event
     */
    public void onActionResetAppointmentsTableView(ActionEvent event){
        reportTableView.setItems(ListManager.getALLCustomerAppointments());

        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    /**
     * onActionDisplayReport method
     * displays the report selected from the ReportMenu.
     *
     * @param event the event
     */
    @FXML
    public void onActionDisplayReport(ActionEvent event){
        StringBuilder userNotificationsReport = new StringBuilder("");

        //TODO need to fix report two
        if (!ListManager.getReport1().isEmpty()){

            userNotificationsReport.append("Displaying report one...\n\n");
            for (ReportOne report : ListManager.getReport1()) {
                userNotificationsReport.append(report.toString()).append("\n");
            }
            userNotificationsField.setText(userNotificationsReport.toString());
            ListManager.getReport1().removeAll(ListManager.getReport1());

        } else if(!ListManager.getReport2().isEmpty()){

            userNotificationsReport.append("Displaying report two...\n\n");
            for(Contact contacts : ListManager.getAllContacts()){
                if (contacts.getContactId() == selectedContactID){
                    userNotificationsReport.append(contacts.getContactName()).append("----\n");
                }
            }
            for(ReportTwo report : ListManager.getReport2()){
                userNotificationsReport.append(report.toString()).append("\n");
            }
            userNotificationsField.setText(userNotificationsReport.toString());
            ListManager.getReport2().removeAll(ListManager.getReport2());

        } else if(!ListManager.getReport3().isEmpty()){

            userNotificationsReport.append("Displaying report three...\n\n");
            for (ReportThree report : ListManager.getReport3()) {
                userNotificationsReport.append(report.toString()).append("\n");
            }
            userNotificationsField.setText(userNotificationsReport.toString());
            ListManager.getReport3().removeAll(ListManager.getReport3());

        }

    }

    /**
     * onActionOpenReportMenu method
     * opens the ReportMenu.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void onActionOpenReportMenu(ActionEvent event) throws IOException {
        UtilityFunctions.navigateMenu(event, "/view/ReportMenuScreen.fxml", "Report Menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userNotificationsField.setText("Welcome!");



        reportTableView.setItems(ListManager.getALLCustomerAppointments());
        appointmentIdCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endDateTimeCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));



    }

}
