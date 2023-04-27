package sample.model;

public class FirstLevelDivision{
    private int countryId;
    private int divisionId;
    private String divisionName;


    public FirstLevelDivision(int countryId, int divisionId, String divisionName) {
        this.countryId = countryId;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }

    @Override
    public String toString() {
        return (divisionName);
    }

}
