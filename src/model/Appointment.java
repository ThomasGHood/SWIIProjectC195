package model;

import java.time.LocalDateTime;

/**
 * The Appointment class.
 *
 * @author Thomas Hood
 */
public class Appointment {

    private int appointmentID;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerID;
    private int userID;
    private int contactID;


    /**
     * Instantiates a new Appointment.
     *
     * @param appointmentID the appointment id
     * @param title         the appointment title
     * @param description   the appointment description
     * @param location      the appointment location
     * @param type          the appointment type
     * @param startTime     the appointment start time
     * @param endTime       the appointment end time
     * @param customerID    the appointment customer id
     * @param userID        the appointment user id
     * @param contactID     the appointment contact id
     */
    public Appointment(int appointmentID, String title, String description, String location, String type,
                       LocalDateTime startTime, LocalDateTime endTime, int customerID, int userID, int contactID){
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Gets appointment id.
     *
     * @return the appointment id
     */
    public int getAppointmentID() {
        return appointmentID;
    }

    /**
     * Sets appointment id.
     *
     * @param appointmentID the appointment id
     */
    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get type string.
     *
     * @return the string
     */
    public String getType(){
        return type;
    }

    /**
     * Set type.
     *
     * @param type the type
     */
    public void setType(String type){
        this.type = type;
    }


    /**
     * Get start time local date time.
     *
     * @return the local date time
     */
    public LocalDateTime getStartTime(){
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets customer id.
     *
     * @param customerID the customer id
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets user id.
     *
     * @param userID the user id
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets contact id.
     *
     * @return the contact id
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Sets contact id.
     *
     * @param contactID the contact id
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
