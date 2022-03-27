package model;

public class Divisions {
    private static int divisionId;
    private static String divisionName;

    public Divisions(int divisionId, String divisionName){
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        Divisions.divisionId = divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        Divisions.divisionName = divisionName;
    }


//    @Override
//    public String toString(){
//        return ("ID: " + getDivisionId() + " Division: " + getDivisionName());
//    }
}
