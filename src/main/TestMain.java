package main;

import utilities.CustomerQuery;
import utilities.JDBC;
import utilities.ListManager;

import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();
        CustomerQuery.selectCountry();

        System.out.println(ListManager.getAllCountries().size());

        JDBC.closeConnection();
    }
}
