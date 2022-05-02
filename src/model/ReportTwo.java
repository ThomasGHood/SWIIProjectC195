package model;

import java.time.LocalDateTime;

/**
 * The ReportTwo class.
 * This class models a required report.
 * This report returns a schedule of appointments for a specified Contact.
 */
public class ReportTwo {
    private int appointmentID;
    private String title;
    private String description;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerID;

    /**
     * Instantiates a new Report two.
     *
     * @param appointmentID   the appointment id
     * @param title           the title
     * @param description     the description
     * @param appointmentType the appointment type
     * @param startTime       the start time
     * @param endTime         the end time
     * @param customerID      the customer id
     */
    public ReportTwo(int appointmentID, String title, String description, String appointmentType,
                     LocalDateTime startTime, LocalDateTime endTime, int customerID) {
        this.appointmentID = appointmentID;
        this.title = title;
        this.description = description;
        this.appointmentType = appointmentType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerID = customerID;

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
     * Gets appointment type.
     *
     * @return the appointment type
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public LocalDateTime getStartTime() {
        return startTime;
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
     * Gets customer id.
     *
     * @return the customer id
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Overridden toString method
     * Used to enhance readability of report.
     *
     * @return returns appointment ID, title, type, description, start and end time, and the customer ID.
     */
    @Override
    public String toString(){
        return (getAppointmentID() + " | " + getTitle() + " | " + getAppointmentType() + " | " + getDescription() +
                " | " + getStartTime() + " | " + getEndTime() + " | " + getCustomerID());
    }
}
