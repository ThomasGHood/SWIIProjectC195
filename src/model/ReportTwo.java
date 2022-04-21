package model;

import java.time.LocalDateTime;

public class ReportTwo {
    private int appointmentID;
    private String title;
    private String description;
    private String appointmentType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerID;

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

    public int getAppointmentID() {
        return appointmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    @Override
    public String toString(){
        return (getAppointmentID() + " | " + getTitle() + " | " + getAppointmentType() + " | " + getDescription() +
                " | " + getStartTime() + " | " + getEndTime() + " | " + getCustomerID());
    }
}
