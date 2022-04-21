package model;

public class ReportOne {
    private String appointmentType;
    private String appointmentMonth;
    private int customerCount;

    public ReportOne(String appointmentType, String appointmentMonth, int customerCount){
        this.appointmentType = appointmentType;
        this.appointmentMonth = appointmentMonth;
        this.customerCount = customerCount;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    public void setAppointmentMonth(String appointmentMonth) {
        this.appointmentMonth = appointmentMonth;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    @Override
    public String toString(){
        return (getAppointmentType() + " | " + getAppointmentMonth() + " | " + getCustomerCount());
    }
}
