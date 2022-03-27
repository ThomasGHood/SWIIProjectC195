package model;

public class Country {
    private static int countryId;
    private static String countryName;

    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        Country.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        Country.countryName = countryName;
    }

//    @Override
//    public String toString(){
//        return (getCountryName());
//    }
}
