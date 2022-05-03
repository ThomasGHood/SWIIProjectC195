package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Contact;
import utilities.ListManager;
import utilities.MonthToInt;
import utilities.ReportsQuery;
import utilities.UtilityFunctions;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The ReportMenuScreenController class.
 * This class allows the user to select a report to send to the user dashboard for viewing.
 *
 * @author Thomas Hood
 */
public class ReportMenuScreenController implements Initializable {

    private final StringBuilder previewFormat = new StringBuilder();
    @FXML
    private TextArea previewReportOne;
    @FXML
    private TextArea previewReportTwo;
    @FXML
    private TextArea previewReportThree;
    @FXML
    private Button reportLaunch;
    @FXML
    private Button cancelReport;
    @FXML
    private Tab reportOne;
    @FXML
    private Tab reportTwo;
    @FXML
    private Tab reportThree;
    @FXML
    private ComboBox<String> report1MonthCombo;
    @FXML
    private ComboBox<String> report1TypeCombo;
    @FXML
    private ComboBox<Contact> report2ContactCombo;


    /**
     * onActionLaunchReport method
     * sends the selected report to the user dashboard.
     *
     * Lambda Expression:
     *     -converts days of the month to Int to select corresponding month in database.
     *     -eliminates need to create entire method.
     *
     * @param event sends selected report to user dashboard.
     */
    @FXML
    public void onActionLaunchReport(ActionEvent event){

        MonthToInt monthInt = month -> {
            switch (month){
                case "Jan":
                    return 1;
                case "Feb":
                    return 2;
                case "Mar":
                    return 3;
                case "Apr":
                    return 4;
                case "May":
                    return 5;
                case "Jun":
                    return 6;
                case "Jul":
                    return 7;
                case "Aug":
                    return 8;
                case "Sep":
                    return 9;
                case "Oct":
                    return 10;
                case "Nov":
                    return 11;
                case "Dec":
                    return 12;
            }
            return -1;
        };

        if (reportOne.isSelected()) {

            if (report1MonthCombo.getSelectionModel().isEmpty() || report1TypeCombo.getSelectionModel().isEmpty()){
                Alert emptyField = new Alert(Alert.AlertType.ERROR, "Type and Month selections are required inputs!");
                emptyField.showAndWait();
                return;
            }

            try {
                ReportsQuery.report1Count(monthInt.returnMonth(report1MonthCombo.getSelectionModel().getSelectedItem()),
                        report1MonthCombo.getSelectionModel().getSelectedItem(),
                        report1TypeCombo.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else if (reportTwo.isSelected()){

            if (report2ContactCombo.getSelectionModel().isEmpty()){
                Alert emptyField = new Alert(Alert.AlertType.ERROR, "Contact must be selected!");
                emptyField.showAndWait();
                return;
            }

            for (Contact contacts : ListManager.getAllContacts()){
                if (contacts.getContactId() == report2ContactCombo.getSelectionModel().getSelectedItem().getContactId())
                    try {
                        ReportsQuery.report2Select(contacts.getContactId());
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
            }

            MainScreenController.getSelectedContactID(report2ContactCombo.getSelectionModel().getSelectedItem().getContactId());

        } else if (reportThree.isSelected()){

            try {
                ReportsQuery.report3Count();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        Stage stage = (Stage) reportLaunch.getScene().getWindow();
        stage.close();
    }

    /**
     * onActionCancelSelection method
     * closes the window without making a selection.
     *
     * @param event the event
     */
    @FXML
    public void onActionCancelSelection(ActionEvent event){
        Stage stage = (Stage) cancelReport.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            UtilityFunctions.refreshQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        report1MonthCombo.setItems(ListManager.getAppointmentMonth());
        report1MonthCombo.setVisibleRowCount(5);
        report1MonthCombo.setPromptText("Select Month");
        report1TypeCombo.setItems(ListManager.getAppointmentType());
        report1TypeCombo.setVisibleRowCount(5);
        report1TypeCombo.setPromptText("Select Type");

        report2ContactCombo.setItems(ListManager.getAllContacts());
        report2ContactCombo.setVisibleRowCount(5);
        report2ContactCombo.setPromptText("Select Contact");

        previewFormat.setLength(0);
        previewFormat.append("Report One:\n");
        previewFormat.append("Type | Month | Number of Customers\n");
        previewReportOne.setText(previewFormat.toString());

        previewFormat.setLength(0);
        previewFormat.append("Report Two:\n");
        previewFormat.append("[Contact_Name]\n");
        previewFormat.append("Appointment_ID | Title | Description | Type | Start Date/Time | " +
                "End Date/Time | Customer_ID\n");
        previewReportTwo.setText(previewFormat.toString());

        previewFormat.setLength(0);
        previewFormat.append("Report Three:\n");
        previewFormat.append("Contact_ID | Contact_Name | Number of Customers\n");
        previewReportThree.setText(previewFormat.toString());

    }
}
