package utilities;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The UserQuery class.
 *
 * @author Thomas Hood
 */
public class UserQuery {

    /**
     * select method
     * queries the database to populate User ObservableList.
     *
     * @throws SQLException the sql exception
     */
    public static void select() throws SQLException {
        String sql = "SELECT User_ID, User_Name, Password FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String password = rs.getString("Password");


            ListManager.addUser(new User(userId, userName, password));


        }
    }
}
