package ui;

import model.Spending;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame implements ActionListener {
    private RoundedCornerBorder cornerBorder;
    private Spending spending;

    public WelcomePage(Spending spending) {
        super("Spending Tracker Application");
        this.spending = spending;
        setPreferredSize(new Dimension(500,400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15,15,15,15));
        setLayout(null);

        JLabel welcomeLable = new JLabel("Welcome to your Spending Tracker!");
        welcomeLable.setBounds(140,40,250,30);
        add(welcomeLable);
        welcomeLable.setForeground(Color.black);

        JLabel wecomeLable1 = new JLabel("Track your Spending Efficiently");
        wecomeLable1.setBounds(150,60,250,30);
        add(wecomeLable1);
        wecomeLable1.setForeground(Color.black);


        JButton setLimit = new JButton(("Set your limit"));
        setLimit.setBounds(137,120,200,50);
        add(setLimit);
        setLimit.setActionCommand("limit");
        setLimit.addActionListener(this);
        setLimit.setOpaque(true);
        setLimit.setBorderPainted(false);
        setLimit.setForeground(Color.black);
        setLimit.setBackground(Color.orange);

        JButton overview = new JButton("Overview Spending");
        overview.setBounds(137, 200,200,50);
        add(overview);
        overview.setActionCommand("overview");
        overview.addActionListener(this);
        overview.setOpaque(true);
        overview.setBorderPainted(false);
        overview.setForeground(Color.black);
        overview.setBackground(Color.orange);

        JButton newMember = new JButton("Add a New Item");
        newMember.setBounds(120,137,300,25);
        add(newMember);
        newMember.setActionCommand("addItem");
        newMember.addActionListener(this);
        newMember.setBorder(new RoundedCornerBorder(12));
        newMember.setForeground(Color.black);
        newMember.setVisible(false);


        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("overview")) {
            new ViewAllTransactions(spending);
        }

        if (e.getActionCommand().equals("limit")) {
            new SetLimit(spending);
            // dispose();
        }
    }

    public static void main(String[] args) {
        Spending spending = new Spending();
        new WelcomePage(spending);
    }
}
