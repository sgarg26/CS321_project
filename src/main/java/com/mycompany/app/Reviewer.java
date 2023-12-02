package com.mycompany.app;

import java.util.ArrayList;

import java.util.logging.Logger;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

// clear;javac -d . ./src/Reviewer.java; java src.Reviewer
public class Reviewer {
    private Logger logger;

    private DHSaccount acc;
    private workflow table;

    private ArrayList<JTextField> args;

    private JFrame frame;
    private JPanel panel;
    private JButton button;

    // Constructor to make sure all the data needed is initalized before creating
    // any windows
    public Reviewer(DHSaccount acc, workflow table, String version) {
        logger = Logger.getLogger(getClass().getName());

        this.acc = acc;
        this.table = table;

        args = new ArrayList<>();

        frame = new JFrame(version);
        panel = new JPanel();
        button = new JButton("Submit");

        createWindow();
    }

    // Helper method to reduce clutter and makes accessing items easier. Creates
    // componenets to be added to panel
    private JPanel componentAdd(String preset, int textCol, String labelTag, int flow, JButton button) {
        JPanel ret = new JPanel();
        JLabel label = new JLabel(labelTag + ":");
        JTextField text = new JTextField(preset, textCol);

        args.add(text); // adds to array for later use

        // determines alignment
        ret.setLayout((flow == 0) ? new FlowLayout(FlowLayout.LEFT) : new FlowLayout(FlowLayout.RIGHT));
        ret.add(label);
        ret.add(text);

        if (button != null) {
            // Specific Button Interaction
            button.addActionListener(event -> {
                String ID = args.get(0).getText();
                String upStatus = args.get(1).getText();

                // Make sure fields arent empty
                if (ID.isEmpty() || upStatus.isEmpty())
                    return;

                // Updates Status => if valid continue if not do nothing...
                if (!acc.validateImmigrantStatus(upStatus)) {
                    frame.setTitle("Invalid Status");
                    return;
                }

                // Updates ID => if valid continue if not do nothing...
                if (!acc.validateImmigrantID(ID)) {
                    frame.setTitle("Invalid ID");
                    return;
                }

                logger.info("Submitted " + upStatus + " with ID of " + ID + " to Approver...");

                // Kills current Window
                frame.dispose();

                // send to workflow table to be sent to Approver
                table.pushToApprover(acc);
            });
        }

        return ret;
    }

    private void createWindow() {
        // Structures it so that they are on top of each other going down
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(componentAdd(acc.getImmigrantID(), 20, "Immigrant ID", 1, null));
        panel.add(componentAdd(acc.getimmigrantStatus(), 20, "Immigrant Status", 1, button));
        panel.add(button);

        // Normal Field Settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(350, 200);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER)); // Makes Sure items are centered with no weird gaps
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
    }

    public static void main(String[] args) {
        // Reviewer r = new Reviewer(
        // new DHSaccount("null", "null", 0, "null"),
        // new workflow(),
        // "Reviewer BETA"
        // );
    }
}
