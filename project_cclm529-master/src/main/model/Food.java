package model;

public class Food extends Item {

    //EFFECTS: constructs food with name and price
    public Food(String description, double amount) {
        super(description, amount);
    }

    @Override
    public String getCategory() {
        return "Food";
    }

    @Override
    public String toString() {
        return getCategory() + " " + getDescription() + " " + getAmount() + " " + getDateString();
    }
}
