package main;
/*
Thomas Hood
S. ID # 001465347
 */

// Username and password must be "test" per PA requirements
// Business hours are defined as 8:00am to 10:00pm EST, including weekends
// all time must be displayed in the system's local time


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import utilities.*;

import java.io.IOException;
import java.sql.SQLException;

public class Main  extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        //Test:

        User thood = new User(666, "thood", "admin0ne");

        ListManager.addUser(thood);

//        Customer tempTestCustomer = new Customer(777, "Bob Dole",
//                "Last star on the left of the lone star", "88041-1231", "777-7771",
//                "U.S", 13);
//
//        ListManager.addCustomer(tempTestCustomer);



        //End test

        JDBC.openConnection();

        //Another Test:

        //DBQuery.insert("Barbra Dole", "Wouldn't wanna go there", "666", "999-1984", 69);
        //CustomerQuery.delete(6);

        //End Test

        //This populates the Customers TableView with current DB records at application start.

//        CustomerQuery.insert("Bob Dole",
//                "Last star on the left of the lone star", "88041-1231", "777-7771",
//                13);
//        AppointmentQuery.insert("Test", "Test", "Test", "Test", LocalDateTime.now().plusDays(3),
//                LocalDateTime.now().plusDays(3).plusMinutes(45), 42, 1, 1);
//        AppointmentQuery.insert("Test", "Test", "Test", "Test", LocalDateTime.now().plusDays(3),
//                LocalDateTime.now().plusDays(3).plusMinutes(45), 42, 1, 1);
//        AppointmentQuery.insert("Test", "Test", "Test", "Test", LocalDateTime.now().plusDays(3),
//                LocalDateTime.now().plusDays(3).plusMinutes(45), 42, 1, 1);
//        AppointmentQuery.insert("Test", "Test", "Test", "Test", LocalDateTime.now().plusDays(3),
//                LocalDateTime.now().plusDays(3).plusMinutes(45), 42, 1, 1);

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
        ListManager.setAllStartHours();
        ListManager.setAllEndHours();

        //End test

        //TODO French test:
        //Locale.setDefault(new Locale("fr"));


        launch(args);

    }

    public static void navigateMenu(ActionEvent event, String navMenu, String stageName) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(navMenu));
        Stage stage = new Stage();
        stage.setTitle(stageName);
        stage.setScene(new Scene(root));
        stage.show();
    }

    //TODO this refreshes the lists for the main screen's tableview
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
}
