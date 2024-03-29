package utilities;

import model.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The ContactsQuery class.
 *
 * @author Thomas Hood
 */
public class ContactsQuery {

    /**
     * Select method
     * populates a Contact ObservableList.
     *
     * @throws SQLException the sql exception
     */
    public static void select() throws SQLException {
        String sql = "SELECT Contact_ID, Contact_Name, Email FROM CONTACTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactId = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");


            ListManager.addContact(new Contact(contactId, contactName, email));


        }
    }
}
