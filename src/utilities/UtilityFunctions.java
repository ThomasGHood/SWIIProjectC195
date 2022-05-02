package utilities;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.Main;
import model.Appointment;

import java.io.IOException;
import java.sql.SQLException;
import java.time.*;

public class UtilityFunctions {


    public static void navigateMenu(ActionEvent event, String navMenu, String stageName) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(navMenu));
        Stage stage = new Stage();
        stage.setTitle(stageName);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void refreshQuery() throws SQLException {
        ListManager.getAllMonthReport().removeAll(ListManager.getAllMonthReport());
        AppointmentQuery.selectMonth();

        ListManager.getAllWeekReport().removeAll(ListManager.getAllWeekReport());
        AppointmentQuery.selectWeek();

        ListManager.getAppointmentType().removeAll(ListManager.getAppointmentType());
        ReportsQuery.selectType();

        ListManager.getAllContacts().removeAll(ListManager.getAllContacts());
        ContactsQuery.select();
    }

    public static void timeZoneHandling(){

        ZonedDateTime zdtESTStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York"));
        ZonedDateTime zdtLocalStart = zdtESTStart.withZoneSameInstant(ZoneId.systemDefault());

        ListManager.setAllStartHours(zdtLocalStart.getHour(), zdtLocalStart.getHour() + 13);
        ListManager.setAllEndHours(zdtLocalStart.getHour(), zdtLocalStart.getHour() + 14);
    }

    public static void meetingNotification(){
        LocalDateTime meetTime = LocalDateTime.now();
        LocalDateTime meetTimeRange = meetTime.plusMinutes(15);

        for (Appointment appointment : ListManager.getALLCustomerAppointments()){
            Alert meetingAlert = new Alert(Alert.AlertType.INFORMATION,  "Imminent Appointment:\n" +
                    "Appointment ID: " + appointment.getAppointmentID() + "\nDate: " +
                    appointment.getStartTime().toLocalDate() + "\nTime: " +
                    appointment.getStartTime().toLocalTime());
            if((appointment.getStartTime().isAfter(meetTime) || appointment.getStartTime().isEqual(meetTime))
                    && appointment.getStartTime().isBefore(meetTimeRange)){
                meetingAlert.show();
                return;
            }
        }
        Alert noMeeting = new Alert(Alert.AlertType.INFORMATION, "No pending appointments!");
        noMeeting.show();
        return;
    }

}
