package sample.model;

import javafx.scene.control.SingleSelectionModel;

/**This class creates First Level Division objects for use within the application*/
public class FirstLevelDivision {
    private int countryId;
    private int divisionId;
    private String divisionName;

    /**Constructor for First Level Division objects
     * @param countryId
     * @param divisionId
     * @param divisionName */
    public FirstLevelDivision(int countryId, int divisionId, String divisionName) {
        this.countryId = countryId;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /**This method returns divisionId
     * @return divisionId*/
    public int getDivisionId() {
        return divisionId;
    }

    /**This method returns divisionName
     * @return divisionName*/
    public String getDivisionName() {
        return divisionName;
    }

    /**This method overrides First Level Division objects into strings for use in combo boxes
     * @return FirstLevelDivision as divisionName in form of a string*/
    @Override
    public String toString() {
        return (divisionName);
    }
}
