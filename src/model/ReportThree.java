package model;

public class ReportThree {

    private int contactId;
    private String contactName;
    private int customerCount;

    public ReportThree(int contactId,String contactName, int customerCount){
        this.contactId = contactId;
        this.contactName = contactName;
        this.customerCount = customerCount;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName(){
        return contactName;
    }

    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    @Override
    public String toString(){
        return (getContactId() + " | " + getContactName() + " | " + getCustomerCount());
    }

//    @Override
//    public String toString(){
//        return (getContactName() + " | " + getCustomerCount());
//    }
}
