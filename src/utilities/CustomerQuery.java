package utilities;

import model.Country;
import model.Customer;
import model.Divisions;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class CustomerQuery {

    //TODO need to division_id from customers -> country_id from first_level_divisions -> countries
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

    public static int delete(int customerId) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
