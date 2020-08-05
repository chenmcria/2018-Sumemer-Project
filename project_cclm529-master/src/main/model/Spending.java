package model;

import exception.InEligibleInputOfLimit;
import exception.NotEnoughLimitLeftException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Spending extends Subject implements Loadable, Saveable {
    public static double amountSpentSoFar;
    public static double LIMIT;
    protected ArrayList<Item> items;
    public static ArrayList<Item> allItemsBought;
    public static Map<String, Spending> spendingMap;
    public static Item item;

    public Spending() {
        items = new ArrayList<>();
        addObserver(new SpendingSummary());
        allItemsBought = new ArrayList<>();
    }

    public static void populateSpendingMap() {
        spendingMap = new HashMap<>();
        spendingMap.put("education", new EducationSpending());
        spendingMap.put("food", new FoodSpending());
        spendingMap.put("healthcare", new HealthCareSpending());
        spendingMap.put("shopping", new ShoppingSpending());
        spendingMap.put("others", new OthersSpending());
    }

    //EFFECTS: return the amount spent so far
    public double getAmountSpentSoFar() {
        return this.amountSpentSoFar;
    }

    //MODIFIES: this
    //EFFECTS: add new amount to amountSpentSoFar, if is larger than the limit
    //         print the statement and return false, otherwise return true
    public boolean addAmountSpent(double newAmount) {
        amountSpentSoFar += newAmount;
        if (amountSpentSoFar >= this.LIMIT) {
            return false;
        }
        return true;
    }




    //EFFECTS: return the limit
    public double getLimit() {
        return LIMIT;
    }


    //MODIFIES: this
    //EFFECTS: reset the limit
    public void setLimit(double limitEntered) throws InEligibleInputOfLimit {
        if (limitEntered < 0) {
            throw new InEligibleInputOfLimit("Your input can't be negative. Please re-enter a new one.");
        }

        this.LIMIT = limitEntered;
    }

    //MODIFIES: this
    //EFFECTS: add the item to items, and update the amount spent so far

    public void addItem(Item item) throws NotEnoughLimitLeftException {
        double newAmount = getAmountSpentSoFar() + item.getAmount();
        items.add(item);
        notifyObservers(item);
        allItemsBought.add(item);
        addAmountSpent(item.getAmount());
        if (newAmount >= LIMIT) {
            throw new NotEnoughLimitLeftException("You'are over your limit. STOP BUYING!");
        }
    }



    //EFFECTS: removes the item from items at a specific position
    public boolean removeItem(int itemPos) {
        if (itemPos >= 0 && itemPos < items.size()) {
            item = items.get(itemPos);
            items.remove(itemPos);
            amountSpentSoFar -= item.getAmount();
            return  true;
        }
        return false;
    }


    //EFFECTS: return items in the list
    public ArrayList<Item> getItems() {
        return items;
    }


    //EFFECTS: print out all the items in the list
    public void printItemsBought() {
        for (Item item: items) {
            System.out.println(item.getDescription());
            System.out.println(item.getAmount());

        }
    }

    //EFFECTS: print out all the items in the given format
    public static void printAllItemsBought() {
        for (Item item: allItemsBought) {
            item.toString();
            System.out.println(item.toString());
        }
    }

    //EFFECTS: returns the limit left
    public static double getLimitLeft() {
        return LIMIT - amountSpentSoFar;
    }


    @Override
    public ArrayList<Item> load(String filename) {
        ArrayList<Item> list = new ArrayList<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            boolean cont = true;
            while (cont) {
                Item obj = (Item)ois.readObject();
                if (obj != null) {
                    list.add(obj);
                } else {
                    cont = false;
                }
            }
            ois.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Item i: allItemsBought) {
                oos.writeObject(i);
            }
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
