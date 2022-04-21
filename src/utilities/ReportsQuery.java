package utilities;

import model.Customer;
import model.ReportOne;
import model.ReportThree;
import model.ReportTwo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

//TODO this class is intended to query the database and populate the Overview TableView
public abstract class ReportsQuery {


    //This method gets the last (most recently added to the DB) and adds it to the customers observablelist.
    public static void selectLast() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Country FROM CUSTOMERS\n" +
                "INNER JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID\n" +
                "INNER JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID\n" +
                "ORDER BY Customer_ID DESC LIMIT 1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionId = rs.getInt("Division_ID");
            String country = rs.getString("Country");


            ListManager.addCustomer(new Customer(customerId, customerName, address, postalCode, phone, country,
                    divisionId));
        }
    }

    public static void selectType() throws SQLException {
        String sql = "SELECT Type FROM APPOINTMENTS\n" +
                "Group by Type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String appointmentType = rs.getString("Type");

            ListManager.addAppointmentType(appointmentType);
        }
    }


    public static void report1Count(int intMonth, String appointmentMonth, String apptmentType) throws SQLException{
        String sql = "Select Type, Count(Customer_ID) FROM APPOINTMENTS\n WHERE Month(timestamp(Start)) = ?\n"
                + "AND Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, intMonth);
        ps.setString(2, apptmentType);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String appointmentType = rs.getString("Type");
            int customerCount = rs.getInt("Count(Customer_ID)");

            ListManager.addReport1(new ReportOne(appointmentType, appointmentMonth, customerCount));

        }

    }

//    //TODO finish this part
//    public static void report2Select() throws SQLException{
//        String sql = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM APPOINTMENTS\n" +
//                "ORDER BY Contact_ID;";
//        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            int appointmentID = rs.getInt("Appointment_ID");
//            String title = rs.getString("Title");
//            String description = rs.getString("Description");
//            String appointmentType = rs.getString("Type");
//            LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
//            LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
//            int customerID = rs.getInt("Customer_ID");
//
//            ListManager.addReport2(new ReportTwo(appointmentID, title, description, appointmentType, startTime,
//                    endTime, customerID));
//
//        }
//
//    }

    //TODO figure out the logic here...
    public static void report2Select(int contactID) throws SQLException{
        String sql = "SELECT Appointment_ID, Title, Type, Description, Start, End, Customer_ID FROM APPOINTMENTS\n" +
                "WHERE Contact_ID = ?;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentID = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String appointmentType = rs.getString("Type");
            LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");

            ListManager.addReport2(new ReportTwo(appointmentID, title, description, appointmentType, startTime,
                    endTime, customerID));

        }

    }

    public static void report3Count() throws SQLException{
        String sql = "SELECT Contact_Name, APPOINTMENTS.Contact_ID, COUNT(Customer_ID) FROM APPOINTMENTS\n" +
                "INNER JOIN CONTACTS ON CONTACTS.Contact_ID = APPOINTMENTS.Contact_ID\n" +
                "GROUP BY APPOINTMENTS.Contact_ID;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String contactName = rs.getString("Contact_Name");
            int contactId = rs.getInt("Contact_ID");
            int customerCount = rs.getInt("Count(Customer_ID)");

            ListManager.addReport3(new ReportThree(contactId, contactName, customerCount));

        }

    }


    //TODO need to set all comments for everything...


    public static int getMonthInt(String month){
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
    }

}
