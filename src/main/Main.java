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
import model.Customer;
import utilities.CustomerQuery;
import utilities.JDBC;
import utilities.ListManager;

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

        Customer tempTestCustomer = new Customer(777, "Bob Dole",
                "Last star on the left of the lone star", "88041-1231", "777-7771",
                "USA", 13);

        ListManager.addCustomer(tempTestCustomer);

        //End test

        JDBC.openConnection();

        //Another Test:

        //DBQuery.insert("Barbra Dole", "Wouldn't wanna go there", "666", "999-1984", 69);
        //CustomerQuery.delete(6);

        //End Test

        //This populates the Customers TableView with current DB records at application start.

        CustomerQuery.select();
        CustomerQuery.selectCountry();
        CustomerQuery.selectDivision();

        //End test



        launch(args);

    }

    public static void navigateMenu(ActionEvent event, String navMenu) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(navMenu));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}
