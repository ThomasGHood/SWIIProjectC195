package utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

//TODO add OverViewReportWeek and OverViewReportMonth

/**
 * The ListManager class.
 * This class is a utility class used to get all objects used to populate the GUI objects.
 */
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

    /**
     * Add customer.
     *
     * @param newCustomer the new customer
     */
    public static void addCustomer(Customer newCustomer){
        allCustomers.add(newCustomer);
    }

    /**
     * Get all customers observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Customer> getAllCustomers(){
        return allCustomers;
    }

    /**
     * Update customer.
     *
     * @param index            the index
     * @param selectedCustomer the selected customer
     */
    public static void updateCustomer(int index, Customer selectedCustomer){
        getAllCustomers().set(index, selectedCustomer);
    }

    /**
     * Delete customer boolean.
     *
     * @param selectedCustomer the selected customer
     * @return the boolean
     */
    public static boolean deleteCustomer(Customer selectedCustomer){
        for (Customer customer : getAllCustomers()){
            if(customer == selectedCustomer)
                return getAllCustomers().remove(customer);
        }
        return false;
    }

    /**
     * Add customer appointment.
     *
     * @param newAppointment the new appointment
     */
    public static void addCustomerAppointment(Appointment newAppointment){
        customerAppointments.add(newAppointment);
    }

    /**
     * Gets all customer appointments.
     *
     * @return the all customer appointments
     */
    public static ObservableList<Appointment> getALLCustomerAppointments() {
        return customerAppointments;
    }

    /**
     * Update customer appointment.
     *
     * @param index               the index
     * @param selectedAppointment the selected appointment
     */
    public static void updateCustomerAppointment(int index, Appointment selectedAppointment){
        getALLCustomerAppointments().set(index, selectedAppointment);
    }

    /**
     * Delete customer appointment boolean.
     *
     * @param selectedCustomerAppointment the selected customer appointment
     * @return the boolean
     */
    public static boolean deleteCustomerAppointment(Appointment selectedCustomerAppointment){
        for (Appointment appointment : getALLCustomerAppointments()){
            if (appointment == selectedCustomerAppointment)
                return getALLCustomerAppointments().remove(selectedCustomerAppointment);
        }
        return false;
    }

    //Country

    /**
     * Add country.
     *
     * @param newCountry the new country
     */
    public static void addCountry(Country newCountry){
        allCountries.add(newCountry);
    }

    /**
     * Get all countries observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Country> getAllCountries(){
        return allCountries;
    }


    //Divisions US

    /**
     * Add division us.
     *
     * @param newDivision the new division
     */
    public static void addDivisionUS(Divisions newDivision){
        allDivisionsUS.add(newDivision);
    }

    /**
     * Get all divisions us observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Divisions> getAllDivisionsUS(){
        return allDivisionsUS;
    }

    //Divisions UK

    /**
     * Add division uk.
     *
     * @param newDivision the new division
     */
    public static void addDivisionUK(Divisions newDivision){
        allDivisionsUK.add(newDivision);
    }

    /**
     * Get all divisions uk observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Divisions> getAllDivisionsUK(){
        return allDivisionsUK;
    }

    //Divisions Canada

    /**
     * Add division canada.
     *
     * @param newDivision the new division
     */
    public static void addDivisionCanada(Divisions newDivision){
        allDivisionsCanada.add(newDivision);
    }

    /**
     * Get all divisions canada observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Divisions> getAllDivisionsCanada(){
        return allDivisionsCanada;
    }

    //All Divisions

    /**
     * Add division.
     *
     * @param newDivision the new division
     */
    public static void addDivision(Divisions newDivision){
        allDivisions.add(newDivision);
    }

    /**
     * Get all divisions observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Divisions> getAllDivisions(){
        return allDivisions;
    }

    //User

    /**
     * Add user.
     *
     * @param newUser the new user
     */
    public static void addUser(User newUser){
        allUsers.add(newUser);
    }

    /**
     * Get all users observable list.
     *
     * @return the observable list
     */
    public static ObservableList<User> getAllUsers(){
        return allUsers;
    }

    //Contacts

    /**
     * Add contact.
     *
     * @param newContact the new contact
     */
    public static void addContact(Contact newContact){
        allContacts.add(newContact);
    }

    /**
     * Get all contacts observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Contact> getAllContacts(){
        return allContacts;
    }

    //TIME Lists:

    /**
     * Get all minutes observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Integer> getAllMinutes(){
        return allMinutes;
    }

    /**
     * Set all minutes.
     */
    public static void setAllMinutes(){
        for (int x = 0; x < 60; x++){
            allMinutes.add(x);
        }
    }

    /**
     * Get all start hours observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Integer> getAllStartHours(){
        return allStartHours;
    }

    /**
     * Set all start hours.
     *
     * @param s the start
     * @param e the end
     */
    public static void setAllStartHours(int s, int e){
        for (int x = s; x < e; x++){
            allStartHours.add(x);
        }
    }

    /**
     * Get all end hours observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Integer> getAllEndHours(){
        return allEndHours;
    }

    /**
     * Set all end hours.
     *
     * @param s the start
     * @param e the end
     */
    public static void setAllEndHours(int s, int e){
        for (int x = s; x < e; x++){
            allEndHours.add(x);
        }
    }


    //Reports

    /**
     * Add week report.
     *
     * @param newWeekReport the new week report
     */
    public static void addWeekReport(Appointment newWeekReport){
        weekReport.add(newWeekReport);
    }

    /**
     * Gets all week report.
     *
     * @return the all week report
     */
    public static ObservableList<Appointment> getAllWeekReport() {
        return weekReport;
    }

    /**
     * Delete report week.
     */
    public static void deleteReportWeek(){
        weekReport.removeAll(getAllWeekReport());
    }

    /**
     * Add month report.
     *
     * @param newMonthReport the new month report
     */
    public static void addMonthReport(Appointment newMonthReport){
        monthReport.add(newMonthReport);
    }

    /**
     * Gets all month report.
     *
     * @return the all month report
     */
    public static ObservableList<Appointment> getAllMonthReport() {
        return monthReport;
    }

    /**
     * Delete report month boolean.
     *
     * @return the boolean
     */
    public static boolean deleteReportMonth(){
        return monthReport.removeAll(getAllMonthReport());
    }

    //ReportMenu Lists

    /**
     * Add contact schedule.
     *
     * @param newAppointment the new appointment
     */
    public static void addContactSchedule(Appointment newAppointment){
        contactSchedule.add(newAppointment);
    }

    /**
     * Get contact schedule observable list.
     *
     * @return the observable list
     */
    public static ObservableList<Appointment> getContactSchedule(){
        return contactSchedule;
    }

    /**
     * Get appointment month observable list.
     *
     * @return the observable list
     */
    public static ObservableList<String> getAppointmentMonth(){
        return appointmentMonth;

    }

    /**
     * Add appointment type.
     *
     * @param apptType the appt type
     */
    public static void addAppointmentType(String apptType){
        appointmentType.add(apptType);
    }

    /**
     * Get appointment type observable list.
     *
     * @return the observable list
     */
    public static ObservableList<String> getAppointmentType(){
        return appointmentType;

    }

    /**
     * Add report 1.
     *
     * @param newReport the new report
     */
    public static void addReport1(ReportOne newReport){
        report1.add(newReport);
    }

    /**
     * Get report 1 observable list.
     *
     * @return the observable list
     */
    public static ObservableList<ReportOne> getReport1(){
        return report1;
    }

    /**
     * Add report 2.
     *
     * @param newReport the new report
     */
    public static void addReport2(ReportTwo newReport){
        report2.add(newReport);
    }

    /**
     * Get report 2 observable list.
     *
     * @return the observable list
     */
    public static ObservableList<ReportTwo> getReport2(){
        return report2;
    }

    /**
     * Add report 3.
     *
     * @param newReport the new report
     */
    public static void addReport3(ReportThree newReport){
        report3.add(newReport);
    }

    /**
     * Get report 3 observable list.
     *
     * @return the observable list
     */
    public static ObservableList<ReportThree> getReport3(){
        return report3;
    }
}
