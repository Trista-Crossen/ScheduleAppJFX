package sample.model;

import javafx.scene.control.SingleSelectionModel;

public class FirstLevelDivision extends SingleSelectionModel<FirstLevelDivision> {
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

    @Override
    protected FirstLevelDivision getModelItem(int i) {
        return null;
    }

    @Override
    protected int getItemCount() {
        return 0;
    }
}
