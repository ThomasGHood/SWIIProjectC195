package utilities;

import model.Country;
import model.Customer;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The CustomerQuery class.
 */
public abstract class CustomerQuery {

    /**
     * Insert int.
     * Inserts new Customer object into the database.
     *
     * @param name       the name
     * @param address    the address
     * @param postalCode the postal code
     * @param phone      the phone
     * @param divisionId the division id
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int insert(String name, String address, String postalCode, String phone,
                             int divisionId) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) " +
                "VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Update int.
     * Updates a Customer object in the database.
     *
     * @param customerId the customer id
     * @param name       the name
     * @param address    the address
     * @param postalCode the postal code
     * @param phone      the phone
     * @param divisionId the division id
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int update(int customerId, String name, String address, String postalCode, String phone,
                             int divisionId) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, " +
                "Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionId);
        ps.setInt(6, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;

    }

    /**
     * Select method
     * Queries the database to populate a Customer ObservableList.
     *
     * @throws SQLException the sql exception
     */
//This method populates customer list from DB
    public static void select() throws SQLException {
        String sql = "SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, customers.Division_ID, Country FROM CUSTOMERS\n" +
                "INNER JOIN first_level_divisions ON first_level_divisions.Division_ID = customers.Division_ID\n" +
                "INNER JOIN countries ON countries.Country_ID = first_level_divisions.Country_ID";
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

    /**
     * selectLast method
     * Queries the database for the last inserted Customer object,
     * appends the Customer object to the Customer ObservableList.
     *
     * @throws SQLException the sql exception
     */
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

    /**
     * selectCountry method
     * Queries the database for Country objects and populates Country ObservableList.
     *
     * @throws SQLException the sql exception
     */
//This method populates an observablelist with Country objects.
    public static void selectCountry() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int countryId = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");

            ListManager.addCountry((new Country(countryId, countryName)));
        }
    }


    /**
     * selectDivisionUS method
     * Queries the database for all Divisions in the US and populates an ObservableList.
     *
     * @throws SQLException the sql exception
     */
//This method gets the Divisions observablelist.
    public static void selectDivisionUS() throws SQLException {
        String sql = "SELECT Division_ID, Division FROM first_level_divisions\n" +
                "WHERE Country_ID =1";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            ListManager.addDivisionUS(new Divisions(divisionId, divisionName));
        }
    }

    /**
     * selectDivisionCanada method
     * Queries the database for all Divisions in Canada and populates ObservableList.
     *
     * @throws SQLException the sql exception
     */
    public static void selectDivisionCanada() throws SQLException {
        String sql = "SELECT Division_ID, Division FROM first_level_divisions\n" + "WHERE Country_ID =3";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            ListManager.addDivisionCanada(new Divisions(divisionId, divisionName));
        }
    }

    /**
     * selectDivisionUK method
     * Queries the database for all Divisions in the UK and populates ObservableList.
     *
     * @throws SQLException the sql exception
     */
    public static void selectDivisionUK() throws SQLException {
        String sql = "SELECT Division_ID, Division FROM first_level_divisions\n" + "WHERE Country_ID =2";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            ListManager.addDivisionUK(new Divisions(divisionId, divisionName));
        }
    }

    /**
     * selectAllDivisions method
     * Queries the database for all Divisions and populates ObservableList.
     *
     * @throws SQLException the sql exception
     */
    public static void selectAllDivisions() throws SQLException {
        String sql = "SELECT Division_ID, Division FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divisionId = rs.getInt("Division_ID");
            String divisionName = rs.getString("Division");

            ListManager.addDivision(new Divisions(divisionId, divisionName));
        }
    }

    /**
     * delete method
     * Deletes specified Customer object from the database.
     *
     * @param customerId the customer id
     * @return the int
     * @throws SQLException the sql exception
     */
    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
