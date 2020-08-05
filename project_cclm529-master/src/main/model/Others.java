package model;

public class Others extends Item {

    //EFFECTS: constructs food with name and price
    public Others(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String getCategory() {
        return "Others";
    }

    @Override
    public String toString() {
        return getCategory() + " " + getDescription() + " " + getAmount() + " " + getDateString();
    }
}