package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

/**
 * The purpose of this class is to manage users and Customer and Appointment class functionalities.
 *
 * TODO add delete and search functionality; add password/user management
 */

public class ListManager {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Divisions> allDivisions = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();

    //Customer

    public static void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }

    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }

    public static void updateCustomer(int index, Customer selectedCustomer){
        getAllCustomers().set(index, selectedCustomer);
    }

    public static boolean deleteCustomer(Customer selectedCustomer){
        for (Customer customer : getAllCustomers()){
            if(customer == selectedCustomer)
                return getAllCustomers().remove(customer);
        }
        return false;
    }

    public static void addCustomerAppointment(Appointment newAppointment){
        customerAppointments.add(newAppointment);
    }

    public static ObservableList<Appointment> getALLCustomerAppointments() {
        return customerAppointments;
    }

    public static boolean deleteCustomerAppointment(Appointment selectedCustomerAppointment){
        for (Appointment appointment : getALLCustomerAppointments()){
            if (appointment == selectedCustomerAppointment)
                return getALLCustomerAppointments().remove(selectedCustomerAppointment);
        }
        return false;
    }

    //Country

    public static void addCountry(Country newCountry){
        allCountries.add(newCountry);
    }

    public static ObservableList<Country> getAllCountries(){
        return allCountries;
    }


    //Divisions

    public static void addDivision(Divisions newDivision){
        allDivisions.add(newDivision);
    }

    public static ObservableList<Divisions> getAllDivisions(){
        return allDivisions;
    }

    //User

    public static void addUser(User newUser){
        allUsers.add(newUser);
    }

    public static ObservableList<User> getAllUsers(){
        return allUsers;
    }
}
