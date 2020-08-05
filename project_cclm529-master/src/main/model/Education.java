package model;

public class Education extends Item {

    //EFFECTS: constructs food with name and price
    public Education(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String getCategory() {
        return "Education";
    }

    @Override
    public String toString() {
        return getCategory() + " " + getDescription() + " " + getAmount() + " " + getDateString();
    }
}