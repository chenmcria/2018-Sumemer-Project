package model;

public class SpendingSummary implements ItemObserver {

    @Override
    public void update(Item item) {
        System.out.println("You have added " + item.getDescription() + " to the Transactions");
    }
}
