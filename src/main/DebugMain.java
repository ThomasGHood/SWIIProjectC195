package main;

import model.Country;
import utilities.CustomerQuery;
import utilities.JDBC;
import utilities.ListManager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DebugMain {
    private static int customerIndex;

    public static void main(String[] args) throws SQLException {



        JDBC.openConnection();
        CustomerQuery.selectCountry();
        CustomerQuery.select();
        Country t = new Country(3, "poopjelly");
        Country c = new Country(7, "dodoland");
        ListManager.addCountry(t);

        int customerId = 25;
        int hour = 12;
        int minutes = 15;

        LocalDateTime timeTest = LocalDateTime.now();

//        LocalTime tempTime = LocalTime.parse(String.valueOf(hour));
//        tempTime.plusMinutes(minutes);
        LocalTime tTime = LocalTime.of(hour, minutes);

        System.out.println(LocalDateTime.of(timeTest.toLocalDate(), tTime));

//        System.out.println(timeTest);
//        System.out.println(Timestamp.valueOf(timeTest));
//        System.out.println(timeTest.toLocalTime());
//        System.out.println(timeTest.toLocalDate());
//        System.out.println(LocalDateTime.of(timeTest.toLocalDate(), timeTest.toLocalTime()));


//        for (Customer customer : ListManager.getAllCustomers()){
//            if(customerId == customer.getCustomerID()){
//                customerIndex = ListManager.getAllCustomers().indexOf(customer.getCustomerID());
//            }
//        }

        //customerIndex = ListManager.getAllCustomers().indexOf();


//        System.out.println(ListManager.getAllCountries().get(1).getCountryName());

        JDBC.closeConnection();
    }
}
