package ui;


import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTransaction extends JFrame implements ActionListener {
    JTextField itemDescription;
    JTextField itemAmount;
    JTextField itemCategory;
    private JComboBox comboBox;
    ViewAllTransactions vsw;
    JFrame jf;
    Spending spending;

    public AddTransaction(ViewAllTransactions viewSpendingWindow, Spending spending) {
        jf = new JFrame("Add an Item");
        this.vsw = viewSpendingWindow;
        this.spending = spending;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel enterCategory = new JLabel("Category");
        enterCategory.setBounds(50, 40, 80, 25);
        add(enterCategory);
        enterCategory.setForeground(Color.black);

        String[] choices = {"Food", "Education", "Shopping", "HealthCare", "Others"};
        comboBox = new JComboBox(choices);
        comboBox.setSelectedIndex(4);
        comboBox.setBackground(Color.white);
        comboBox.setForeground(Color.black);
        comboBox.addActionListener(this);
        comboBox.setBounds(48, 75, 103, 25);
        add(comboBox);

        itemCategory = new JTextField(30);
        add(itemCategory);

        JLabel enterDescription = new JLabel("Description");
        enterDescription.setBounds(200, 40, 180, 25);
        add(enterDescription);
        enterDescription.setForeground(Color.black);

        itemDescription = new JTextField(30);
        itemDescription.setBounds(198, 75, 230, 25);
        add(itemDescription);

        JLabel enterAmount = new JLabel("Amount");
        enterAmount.setBounds(460, 40, 150, 25);
        add(enterAmount);
        enterAmount.setForeground(Color.black);

        itemAmount = new JTextField(30);
        itemAmount.setBounds(457, 75, 150, 25);
        add(itemAmount);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        JButton addButton = new JButton("Add");
        addButton.setBounds(600, 120, 80, 25);
        add(addButton);
        addButton.setForeground(Color.black);
        addButton.setActionCommand("Add");
        addButton.addActionListener(this);

    }

    public void helper(String categoryName, Item item) {
        try {
            this.spending.addItem(item);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You'are over your limit. STOP BUYING!");
        }
    }

    public void getCase() {
        String categoryName = (String) comboBox.getSelectedItem();
        String descriptionEntered = itemDescription.getText();
        double d = Double.parseDouble(itemAmount.getText());
        switch (categoryName) {
            case "Food":
                Item food = new Food(descriptionEntered, d);
                this.helper(categoryName, food);
                break;
            case "Shopping":
                Item shopping = new Shopping(descriptionEntered, d);
                this.helper(categoryName, shopping);
                break;
            default:
                break;
        }
        spending.save("savefile.txt");
    }

    public void getRestCase() {
        String categoryName = (String) comboBox.getSelectedItem();
        String descriptionEntered = itemDescription.getText();
        double d = Double.parseDouble(itemAmount.getText());
        switch (categoryName) {
            case "Education":
                Item education = new Education(descriptionEntered, d);
                this.helper(categoryName, education);
                break;
            case "HealthCare":
                Item healthcare = new HealthCare(descriptionEntered, d);
                this.helper(categoryName, healthcare);
                break;
            default:
                Item others = new Others(descriptionEntered, d);
                this.helper(categoryName, others);
                break;
        }
        spending.save("savefile.txt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            getCase();
            getRestCase();
            JOptionPane.showMessageDialog(null, "Item has been successfully added.");
//            vsw.dispose();
            dispose();
            new ViewAllTransactions(spending);
        }
    }
}



