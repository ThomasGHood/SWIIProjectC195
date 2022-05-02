package model;

/**
 * The Divisions class.
 * This class models a Division.
 */
public class Divisions {
    private int divisionId;
    private String divisionName;

    /**
     * Instantiates a new Divisions.
     *
     * @param divisionId   the division id
     * @param divisionName the division name
     */
    public Divisions(int divisionId, String divisionName){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /**
     * Gets division id.
     *
     * @return the division id
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * Sets division id.
     *
     * @param divisionId the division id
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * Gets division name.
     *
     * @return the division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * Sets division name.
     *
     * @param divisionName the division name
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }


    @Override
    public String toString(){
        return getDivisionName();
    }
}
