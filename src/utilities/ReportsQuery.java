package utilities;

import model.ReportOne;
import model.ReportThree;
import model.ReportTwo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * The ReportsQuery class.
 *
 * @author Thomas Hood
 */
public abstract class ReportsQuery {

    /**
     * Select type.
     *
     * @throws SQLException the sql exception
     */
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


    /**
     * report1Count method
     * queries the database to populate ObservableList for ReportOne.
     *
     * @param intMonth         the int month
     * @param appointmentMonth the appointment month
     * @param apptmentType     the apptment type
     * @throws SQLException the sql exception
     */
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

    /**
     * report2Select method
     * Queries the database to populate ObservableList, Contact schedule for ReportTwo
     *
     * @param contactID the contact id
     * @throws SQLException the sql exception
     */
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

    /**
     * report3count method
     * Queries the database to populate ReportThree ObservableList.
     *
     * @throws SQLException the sql exception
     */
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

}
