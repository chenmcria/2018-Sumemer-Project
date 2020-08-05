package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ItemObserver> observers = new ArrayList<>();

    public void addObserver(ItemObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyObservers(Item i) {
        for (ItemObserver o : observers) {
            o.update(i);
        }
    }
}
