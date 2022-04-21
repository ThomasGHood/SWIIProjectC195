package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

/**
 * The purpose of this class is to manage users and Customer and Appointment class functionalities.
 *
 *
 */

//TODO add OverViewReportWeek and OverViewReportMonth

public class ListManager {
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    private static ObservableList<Appointment> customerAppointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> weekReport = FXCollections.observableArrayList();
    private static ObservableList<Appointment> monthReport = FXCollections.observableArrayList();
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Divisions> allDivisionsUS = FXCollections.observableArrayList();
    private static ObservableList<Divisions> allDivisionsUK = FXCollections.observableArrayList();
    private static ObservableList<Divisions> allDivisionsCanada = FXCollections.observableArrayList();
    private static ObservableList<Divisions> allDivisions =FXCollections.observableArrayList();
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();
    private static ObservableList<Integer> allMinutes = FXCollections.observableArrayList();
    private static ObservableList<Integer> allStartHours = FXCollections.observableArrayList();
    private static ObservableList<Integer> allEndHours = FXCollections.observableArrayList();
    private static ObservableList<Appointment> contactSchedule = FXCollections.observableArrayList();
    private static ObservableList<String> appointmentType = FXCollections.observableArrayList();
    private static ObservableList<String> appointmentMonth = FXCollections.observableArrayList("Jan", "Feb",
            "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
    private static ObservableList<ReportOne> report1 = FXCollections.observableArrayList();
    private static ObservableList<ReportTwo> report2 = FXCollections.observableArrayList();
    private static ObservableList<ReportThree> report3 = FXCollections.observableArrayList();

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

    public static void updateCustomerAppointment(int index, Appointment selectedAppointment){
        getALLCustomerAppointments().set(index, selectedAppointment);
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


    //Divisions US

    public static void addDivisionUS(Divisions newDivision){
        allDivisionsUS.add(newDivision);
    }

    public static ObservableList<Divisions> getAllDivisionsUS(){
        return allDivisionsUS;
    }

    //Divisions UK

    public static void addDivisionUK(Divisions newDivision){
        allDivisionsUK.add(newDivision);
    }

    public static ObservableList<Divisions> getAllDivisionsUK(){
        return allDivisionsUK;
    }

    //Divisions Canada

    public static void addDivisionCanada(Divisions newDivision){
        allDivisionsCanada.add(newDivision);
    }

    public static ObservableList<Divisions> getAllDivisionsCanada(){
        return allDivisionsCanada;
    }

    //All Divisions

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

    //Contacts

    public static void addContact(Contact newContact){
        allContacts.add(newContact);
    }

    public static ObservableList<Contact> getAllContacts(){
        return allContacts;
    }

    //TIME Lists:

    //Minutes
    public static ObservableList<Integer> getAllMinutes(){
        return allMinutes;
    }

    //set minutes
    public static void setAllMinutes(){
        for (int x = 0; x < 60; x++){
            allMinutes.add(x);
        }
    }

    //Start Hours
    public static ObservableList<Integer> getAllStartHours(){
        return allStartHours;
    }

    //Set Start Hours
    public static void setAllStartHours(){
        for (int x = 8; x < 22; x++){
            allStartHours.add(x);
        }
    }

    //End Hours
    public static ObservableList<Integer> getAllEndHours(){
        return allEndHours;
    }

    //Set End Hours
    public static void setAllEndHours(){
        for (int x = 8; x < 23; x++){
            allEndHours.add(x);
        }
    }


    //Reports
    public static void addWeekReport(Appointment newWeekReport){
        weekReport.add(newWeekReport);
    }

    public static ObservableList<Appointment> getAllWeekReport() {
        return weekReport;
    }

    public static void deleteReportWeek(){
        weekReport.removeAll(getAllWeekReport());
    }


    public static void addMonthReport(Appointment newMonthReport){
        monthReport.add(newMonthReport);
    }

    public static ObservableList<Appointment> getAllMonthReport() {
        return monthReport;
    }

    public static boolean deleteReportMonth(){
        return monthReport.removeAll(getAllMonthReport());
    }

    //ReportMenu Lists

    public static void addContactSchedule(Appointment newAppointment){
        contactSchedule.add(newAppointment);
    }
    public static ObservableList<Appointment> getContactSchedule(){
        return contactSchedule;
    }

    public static ObservableList<String> getAppointmentMonth(){
        return appointmentMonth;

    }

    public static void addAppointmentType(String apptType){
        appointmentType.add(apptType);
    }

    public static ObservableList<String> getAppointmentType(){
        return appointmentType;

    }

    public static void addReport1(ReportOne newReport){
        report1.add(newReport);
    }

    public static ObservableList<ReportOne> getReport1(){
        return report1;
    }

    public static void addReport2(ReportTwo newReport){
        report2.add(newReport);
    }

    public static ObservableList<ReportTwo> getReport2(){
        return report2;
    }

    public static void addReport3(ReportThree newReport){
        report3.add(newReport);
    }

    public static ObservableList<ReportThree> getReport3(){
        return report3;
    }
}
