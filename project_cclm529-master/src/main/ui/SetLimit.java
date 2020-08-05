package ui;

import exception.InEligibleInputOfLimit;
import model.Spending;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetLimit extends JFrame implements ActionListener {
    JTextField limitText;
    Spending spending;

    public SetLimit(Spending spending) {
        super("Set Limit");
        this.spending = spending;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(350,200));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel enterLimit = new JLabel("Enter your limit: ");
        enterLimit.setBounds(50,50,200,25);
        add(enterLimit);
        enterLimit.setForeground(Color.black);

        limitText = new JTextField(30);
        limitText.setBounds(160,50,100,25);
        add(limitText);

        JButton done = new JButton("Done");
        done.setBounds(160,120,60,25);
        add(done);
        done.setActionCommand("done");
        done.addActionListener(this);
        done.setBorder(new RoundedCornerBorder(10));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("done")) {
            // Spending spending = new Spending();
            String text = limitText.getText();
            double d = Double.parseDouble(text);
            try {
                spending.setLimit(d);
            } catch (InEligibleInputOfLimit ex) {
                JOptionPane.showMessageDialog(null, "Ineligible input please enter a new one");
            }
            spending.save("savefile.txt");
        }
        dispose();
    }
}
