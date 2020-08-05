package model;

public class HealthCare extends Item {

    //EFFECTS: constructs food with name and price
    public HealthCare(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String getCategory() {
        return "HealthCare";
    }

    @Override
    public String toString() {
        return getCategory() + " " + getDescription() + " " + getAmount() + " " + getDateString();
    }
}
