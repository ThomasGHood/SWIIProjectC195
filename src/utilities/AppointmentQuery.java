package utilities;

import model.Appointment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//TODO this class is intended to query the database to populate the Appointment tableview
public abstract class AppointmentQuery {

    //TODO verify Timestamp / LocalDateTime is functioning as expected.
    public static int insert(String title, String description, String location, String type, LocalDateTime startTime,
                             LocalDateTime endTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startTime));
        ps.setTimestamp(6, Timestamp.valueOf(endTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    //TODO verify works after conversion
    public static int update(int appointmentId, String title, String description, String location, String type, LocalDateTime startTime,
                             LocalDateTime endTime, int customerId, int userId, int contactId) throws SQLException {
        String sql = "UPDATE APPOINTMENTS SET Title = ?, Description = ?, Location = ?, Type = ?, " +
                "Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(startTime));
        ps.setTimestamp(6, Timestamp.valueOf(endTime));
        ps.setInt(7, customerId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    //This method will populate the Appointments from the database.
    //TODO figure out the time/date stuff.
    public static void select() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                "Contact_ID FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int appointmentContactId = rs.getInt("Contact_ID");


            ListManager.addCustomerAppointment(new Appointment(appointmentId, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, appointmentStartTime, appointmentEndTime,
                    appointmentCustomerId, appointmentUserId, appointmentContactId));

        }
    }


    //TODO verify working as expected
    // This method gets the last (most recently added to the DB) and adds it to the customers observablelist.
    public static void selectLast() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                "Contact_ID FROM APPOINTMENTS\n" + "ORDER BY Appointment_ID DESC LIMIT 1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int appointmentContactId = rs.getInt("Contact_ID");


            ListManager.addCustomerAppointment(new Appointment(appointmentId, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, appointmentStartTime, appointmentEndTime,
                    appointmentCustomerId, appointmentUserId, appointmentContactId));
        }
    }

    //This method will populate the Appointments from the database.
    //TODO figure out the time/date stuff.
    public static void selectWeek() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                "Contact_ID FROM APPOINTMENTS\n WHERE DATE(Start) BETWEEN NOW() AND (NOW() + INTERVAL 1 WEEK)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int appointmentContactId = rs.getInt("Contact_ID");


            ListManager.addWeekReport(new Appointment(appointmentId, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, appointmentStartTime, appointmentEndTime,
                    appointmentCustomerId, appointmentUserId, appointmentContactId));

        }
    }

    //This method will populate the Appointments from the database.
    //TODO figure out the time/date stuff.
    public static void selectMonth() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                "Contact_ID FROM APPOINTMENTS\n WHERE DATE(Start) BETWEEN NOW() AND (NOW() + INTERVAL 1 MONTH)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int appointmentContactId = rs.getInt("Contact_ID");

            ListManager.addMonthReport(new Appointment(appointmentId, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, appointmentStartTime, appointmentEndTime,
                    appointmentCustomerId, appointmentUserId, appointmentContactId));
        }
    }

    //This method will populate the Appointments from the database.
    //TODO figure out the time/date stuff.
    public static void selectContactSchedule() throws SQLException {
        String sql = "SELECT Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID," +
                "Contact_ID FROM APPOINTMENTS\n ORDER BY Contact_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int appointmentId = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String appointmentDescription = rs.getString("Description");
            String appointmentLocation = rs.getString("Location");
            String appointmentType = rs.getString("Type");
            LocalDateTime appointmentStartTime = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime appointmentEndTime = rs.getTimestamp("End").toLocalDateTime();
            int appointmentCustomerId = rs.getInt("Customer_ID");
            int appointmentUserId = rs.getInt("User_ID");
            int appointmentContactId = rs.getInt("Contact_ID");

            //TODO change this to correct list
            ListManager.addMonthReport(new Appointment(appointmentId, appointmentTitle, appointmentDescription,
                    appointmentLocation, appointmentType, appointmentStartTime, appointmentEndTime,
                    appointmentCustomerId, appointmentUserId, appointmentContactId));
        }
    }


    public static int delete(int appointmentId) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, appointmentId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
