package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Item implements Serializable {
    private String category;
    private String description;
    private double amount;
    private String dateString;
    private Date date;
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Item(String description, double amount) {
        category = getCategory();
        this.description = description;
        date = Calendar.getInstance().getTime();
        dateString = this.df.format(this.date);
        this.amount = amount;
    }

    //EFFECTS: returns the description of item
    public String getDescription() {
        return this.description;
    }

    //EFFECTS: returns the date of item added
    public String getDateString() {
        return this.dateString;
    }

    //EFFECTS: returns the amount of the item
    public double getAmount() {
        return this.amount;
    }

    //EFFECTS: returns category as String
    public abstract String getCategory();


    // MODIFIES: this
    // EFFECTS: change the description of the item
    public void setDescription(String description) {
        this.description = description;
    }


    //MODIFIES: this
    //EFFECTS: change the date of the item
    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    //MODIFIES: this
    //EFFECTS: change the amount of the item
    public void setAmount(double amount) {
        this.amount = amount;
    }

}