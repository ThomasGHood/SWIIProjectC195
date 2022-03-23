package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.User;

/**
 * The purpose of this class is to manage users and Customer and Appointment class functionalities.
 *
 * TODO add delete and search functionality; add password/user management
 */

public class Managers {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static int customerId;
    private static int appointmentId;

    //Customer

    public static int getCustomerID(){
        return ++customerId;
    }

    public static void setCustomerId(int customerId){
        Managers.customerId = customerId;
    }

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

    //Appointment

    public static void addAppointment(Appointment newAppointment){
        allAppointments.add(newAppointment);
    }

    public static ObservableList<Appointment> getAllAppointments(){
        return allAppointments;
    }

    //User

    public static void addUser(User newUser){
        allUsers.add(newUser);
    }

    public static ObservableList<User> getAllUsers(){
        return allUsers;
    }
}
