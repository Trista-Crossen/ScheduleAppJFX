package sample.model;

/**This class creates Country objects to be used within the application*/
public class Country {
    private int countryId;
    private String countryName;

    /**Constructor for country objects*/
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**This method returns countryId
     * @return countryId*/
    public int getCountryId() {
        return countryId;
    }

    /**This method returns countryName
     * @return countryName*/
    public String getCountryName() {
        return countryName;
    }

    /**This method overrides Country objects into a string and returns them
     * @return countryId and countryName as one string*/
    @Override
    public String toString() {
        return (countryId  + " - " + countryName);
    }
}
