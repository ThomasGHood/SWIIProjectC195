package control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import model.Contact;
import utilities.ListManager;
import utilities.ReportsQuery;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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




    @FXML
    public void onActionLaunchReport(ActionEvent event){

        if (reportOne.isSelected()) {

            try {
                ReportsQuery.report1Count(ReportsQuery.getMonthInt(report1MonthCombo.getSelectionModel().getSelectedItem()),
                        report1MonthCombo.getSelectionModel().getSelectedItem(),
                        report1TypeCombo.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else if (reportTwo.isSelected()){

            System.out.println("Report Two is selected!");

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

            System.out.println("Report Three is selected!");

            try {
                ReportsQuery.report3Count();
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        Stage stage = (Stage) reportLaunch.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onActionCancelSelection(ActionEvent event){
        Stage stage = (Stage) cancelReport.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Main.refreshQuery();
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
