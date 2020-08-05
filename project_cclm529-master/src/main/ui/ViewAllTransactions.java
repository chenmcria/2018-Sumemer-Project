package ui;

import model.Item;
import model.Spending;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class ViewAllTransactions extends JFrame implements ActionListener {
    DefaultTableModel model;
    JTable table;
    Spending spending;
    ArrayList<Item> purchasedList;

    public ViewAllTransactions(Spending spending) {

        this.spending = spending;
        purchasedList = spending.load("savefile.txt");
        System.out.println(purchasedList);

        String[] top = new String[]{
                "Category", "Description", "Amount", "Date"
        };


        model = new DefaultTableModel(null, top);
        
        for (Item i : purchasedList) {
            Vector row = new Vector();
            row.add(i.getCategory());
            row.add(i.getDescription());
            row.add(i.getAmount());
            row.add(i.getDateString());
            model.addRow(row);
        }

        table = new JTable(model);
        add(new JScrollPane(table));

        JButton removeItem = new JButton("Delete selected item");
        removeItem.setActionCommand("delete");
        removeItem.addActionListener(this);
        add(removeItem);
        removeItem.setBorder(new RoundedCornerBorder(10));
        removeItem.setForeground(Color.black);

        JButton addItem = new JButton("Add a New Transaction");
        addItem.setActionCommand("add");
        addItem.addActionListener(this);
        add(addItem);
        addItem.setBorder(new RoundedCornerBorder(10));
        addItem.setForeground(Color.black);

        JLabel limitLeft = new JLabel("Limit Left\n"
                + "\n" + Spending.getLimitLeft());
        limitLeft.setBounds(50,40,150,30);
        limitLeft.setBorder(new RoundedCornerBorder(10));
        limitLeft.setBackground(Color.BLUE);
        add(limitLeft);
        limitLeft.setForeground(Color.black);


        JLabel originalLimit = new JLabel("Original Limit\n"
                + "\n" + Spending.LIMIT);
        originalLimit.setBounds(90,40,150,30);
        originalLimit.setBorder(new RoundedCornerBorder(10));
        originalLimit.setBackground(Color.BLUE);
        add(originalLimit);
        originalLimit.setForeground(Color.black);

        JLabel spentSoFar = new JLabel("Spent So Far\n"
                + "\n" + Spending.amountSpentSoFar);
        spentSoFar.setBounds(130,40,150,30);
        spentSoFar.setBorder(new RoundedCornerBorder(10));
        spentSoFar.setBackground(Color.BLUE);
        add(spentSoFar);
        spentSoFar.setForeground(Color.black);


        JPanel panel = new JPanel(new BorderLayout(4,4));
        panel.add(new JScrollPane(table));
        setTitle("All Transactions");
        setLayout(new FlowLayout());

        JPanel reportPage = new JPanel();
        reportPage.add(originalLimit,BorderLayout.NORTH);
        reportPage.add(spentSoFar,BorderLayout.NORTH);
        reportPage.add(limitLeft,BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.add(addItem);
        panel1.add(removeItem);
        panel.add(panel1,BorderLayout.SOUTH);
        panel.add(reportPage,BorderLayout.EAST);

        add(panel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);

        ExchangeRate er = new ExchangeRate("CAD");
        Map map = er.getListOfExchangeRates();
        Iterator it = map.entrySet().iterator();
        Runnable r = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        String output = "";
                        while (it.hasNext()) {
                            Map.Entry pair = (Map.Entry)it.next();
                            output = output + pair.getKey() + " : " + pair.getValue() + "\n";
                            it.remove();
                        }
                        JTextArea jtextArea = new JTextArea(output, 500, 500);
                        jtextArea.setBounds(60,80,500,500);
                        add(jtextArea);
                        reportPage.add(jtextArea,BorderLayout.SOUTH);
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        };
        new Thread(r).start();
        add(panel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pack();
        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            int row = table.getSelectedRow();
            this.spending.removeItem(row);
            JOptionPane.showMessageDialog(null, "Are you sure you want to delete the selected item?");
            JOptionPane.showMessageDialog(null, "Item has been deleted successfully.");
            spending.save("savefile.txt");
        }
        if (e.getActionCommand().equals("add")) {
            new AddTransaction(this, spending);

        }

    }
}
