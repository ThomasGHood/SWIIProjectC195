package main;
/*
Thomas Hood
S. ID # 001465347
 */

// Username and password must be "test" per PA requirements
// Business hours are defined as 8:00am to 10:00pm EST, including weekends
// all time must be displayed in the system's local time


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.*;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

        CustomerQuery.select();
        CustomerQuery.selectCountry();
        CustomerQuery.selectDivisionUS();
        CustomerQuery.selectDivisionCanada();
        CustomerQuery.selectDivisionUK();
        CustomerQuery.selectAllDivisions();
        UserQuery.select();
        ContactsQuery.select();
        AppointmentQuery.select();
        AppointmentQuery.selectWeek();
        AppointmentQuery.selectMonth();

        ListManager.setAllMinutes();
        UtilityFunctions.timeZoneHandling();

        ZoneId.systemDefault();

        Locale.getDefault();


        launch(args);

    }


}