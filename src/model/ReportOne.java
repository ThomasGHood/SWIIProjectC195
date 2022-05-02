package model;

/**
 * The ReportOne Class.
 * This class models a required report.
 * This report returns a count of customers per appointment type.
 */
public class ReportOne {
    private String appointmentType;
    private String appointmentMonth;
    private int customerCount;

    /**
     * Instantiates a new Report one.
     *
     * @param appointmentType  the appointment type
     * @param appointmentMonth the appointment month
     * @param customerCount    the customer count
     */
    public ReportOne(String appointmentType, String appointmentMonth, int customerCount){
        this.appointmentType = appointmentType;
        this.appointmentMonth = appointmentMonth;
        this.customerCount = customerCount;
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
     * Sets appointment type.
     *
     * @param appointmentType the appointment type
     */
    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    /**
     * Gets appointment month.
     *
     * @return the appointment month
     */
    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    /**
     * Sets appointment month.
     *
     * @param appointmentMonth the appointment month
     */
    public void setAppointmentMonth(String appointmentMonth) {
        this.appointmentMonth = appointmentMonth;
    }

    /**
     * Gets customer count.
     *
     * @return the customer count
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /**
     * Sets customer count.
     *
     * @param customerCount the customer count
     */
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    /**
     * Overridden toString method
     * Used to format the report for readability.
     *
     * @return returns appointment type, appointment month, and appointment customer count.
     */
    @Override
    public String toString(){
        return (getAppointmentType() + " | " + getAppointmentMonth() + " | " + getCustomerCount());
    }
}
