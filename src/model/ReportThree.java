package model;

/**
 * The ReportThree class.
 * This class models a required report.
 * This report returns the number of customers per contact.
 *
 * @author Thomas Hood
 */
public class ReportThree {

    private int contactId;
    private String contactName;
    private int customerCount;

    /**
     * Instantiates a new Report three.
     *
     * @param contactId     the contact id
     * @param contactName   the contact name
     * @param customerCount the customer count
     */
    public ReportThree(int contactId,String contactName, int customerCount){
        this.contactId = contactId;
        this.contactName = contactName;
        this.customerCount = customerCount;
    }

    /**
     * Gets contact id.
     *
     * @return the contact id
     */
    public int getContactId() {
        return contactId;
    }

    /**
     * Sets contact id.
     *
     * @param contactId the contact id
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /**
     * Get contact name string.
     *
     * @return the string
     */
    public String getContactName(){
        return contactName;
    }

    /**
     * Set contact name.
     *
     * @param contactName the contact name
     */
    public void setContactName(String contactName){
        this.contactName = contactName;
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
     * Used to enhance readability of the report.
     *
     * @return returns contact ID, contact name, and customer count.
     */
    @Override
    public String toString(){
        return (getContactId() + " | " + getContactName() + " | " + getCustomerCount());
    }
}
