package model;

/**
 * The User class.
 * This class models a User.
 *
 * @author Thomas Hood
 */
public class User {

    private int userID;
    private String userName;
    private String userPassword;

    /**
     * Instantiates a new User.
     *
     * @param userID       the user id
     * @param userName     the user name
     * @param userPassword the user password
     */
    public User(int userID, String userName, String userPassword){
        this.userID = userID;
        this.userName = userName;
        this.userPassword = userPassword;
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user password.
     *
     * @param userPassword the user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Overridden toString method
     * Used to display user ID and user name.
     *
     * @return returns the user ID and user name.
     */
    @Override
    public String toString(){
        return (getUserID() + " | " + getUserName());
    }
}
