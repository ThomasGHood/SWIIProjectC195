package model;


/**
 * The Customer class.
 * This class models a Customer.
 *
 * @author Thomas Hood
 */
public class Customer {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private String country;
    private int divisionID;


    /**
     * Instantiates a new Customer.
     *
     * @param customerID   the customer id
     * @param customerName the customer name
     * @param address      the address
     * @param postalCode   the postal code
     * @param phone        the phone
     * @param country      the country
     * @param divisionID   the division id
     */
    public Customer(int customerID, String customerName, String address, String postalCode, String phone, String country, int divisionID){
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.divisionID = divisionID;
    }

    /**
     * Get customer id int.
     *
     * @return the int
     */
    public int getCustomerID(){
        return customerID;
    }

    /**
     * Set customer id.
     *
     * @param customerID the customer id
     */
    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }

    /**
     * Get customer name string.
     *
     * @return the string
     */
    public String getCustomerName(){
        return customerName;
    }

    /**
     * Set customer name.
     *
     * @param customerName the customer name
     */
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address.
     *
     * @param address the address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Get postal code string.
     *
     * @return the string
     */
    public String getPostalCode(){
        return postalCode;
    }

    /**
     * Set postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    /**
     * Get phone string.
     *
     * @return the string
     */
    public String getPhone(){
        return phone;
    }

    /**
     * Set phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone){
        this.phone = phone;
    }

    /**
     * Get country string.
     *
     * @return the string
     */
    public String getCountry(){
        return country;
    }

    /**
     * Set country.
     *
     * @param country the country
     */
    public void setCountry(String country){
        this.country = country;
    }

    /**
     * Get division id int.
     *
     * @return the int
     */
    public int getDivisionID(){
        return divisionID;
    }

    /**
     * Set division id.
     *
     * @param divisionID the division id
     */
    public void setDivisionID(int divisionID){
        this.divisionID = divisionID;
    }

    /**
     * Overridden toString method
     * Used to display customer ID and customer name.
     *
     * @return returns customer ID and customer name.
     */
    @Override
    public String toString(){
        return (getCustomerID() + " | " + getCustomerName());
    }

}
