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
import model.User;
import utilities.JDBC;
import utilities.Managers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static utilities.JDBC.connection;

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

        User tempTestUser = new User(666, "thood", "test9456");
        Customer tempTestCustomer = new Customer(777, "Bob Dole", "Last star on the left of the lone star", "88041-1231", "777-7771", "USA", 13);

        Managers.addCustomer(tempTestCustomer);

        //End test

        JDBC.openConnection();
        //Another Test:
        Statement statement = connection.createStatement();
        ResultSet customerSet = statement.executeQuery("SELECT * FROM customers");
        while (customerSet.next()){

            System.out.println(customerSet.getString("Customer_Name"));

            int customerId = customerSet.getInt("Customer_ID");
            String customerName = customerSet.getString("Customer_Name");
            String address = customerSet.getString("Address");
            String postalCode = customerSet.getString("Postal_Code");
            String phone = customerSet.getString("Phone");
            int divisionId = customerSet.getInt("Division_ID");
            String country = "USA";

            Managers.addCustomer(new Customer(customerId, customerName, address, postalCode, phone, country, divisionId));

        }
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
