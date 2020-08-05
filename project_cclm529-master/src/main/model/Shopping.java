package model;

public class Shopping extends Item {

    //EFFECTS: constructs food with name and price
    public Shopping(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String getCategory() {
        return "Shopping";
    }

    @Override
    public String toString() {
        return getCategory() + " " + getDescription() + " " + getAmount() + " " + getDateString();
    }
}