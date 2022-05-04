package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utilities.*;

import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;

/**
 * Thomas Hood
 * S. ID # 001465347
 *
 * 2022 May 01
 *
 * Main Class is the entry point to the application.
 *
 * Javadocs folder: "Javadocs/"
 *
 * @author Thomas hood
 */
public class Main extends Application {


    /**
     * Start initializes the LoginScreen.
     *
     * @param stage the stage
     * @throws Exception the exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main method:
     *  -initializes database connection.
     *  -calls methods to populate observable lists used by the app to interface
     *   with the database.
     *  -default ZoneId is set.
     *  -default Locale is set.
     *  -launches the GUI
     *
     * @param args the input arguments
     * @throws SQLException the sql exception
     */
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