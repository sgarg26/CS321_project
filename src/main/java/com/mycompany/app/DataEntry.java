package com.mycompany.app;

import java.util.logging.Logger;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class DataEntry {
    static DataEntry d;
    static DHSaccount acc;
    @SuppressWarnings("unused")
    private workflow table;

    Logger logger = Logger.getLogger(getClass().getName());

    private JFrame frame;
    private JPanel panel;
    private JButton submitButton;
    private JTextField dhsName_textField;
    private JTextField immigrantId_textField;
    private JTextField immigrantStatus_textField;
    private JTextField dhsId_textField;
    private JLabel dhsName;
    private JLabel immigrantId;
    private JLabel immigrantStatus;
    private JLabel dhsId;

    public DataEntry(workflow table) {
        frame = new JFrame("Data Entry BETA");

        panel = new JPanel();
        submitButton = new JButton("Submit");
        dhsName_textField = new JTextField(20);
        immigrantId_textField = new JTextField(20);
        immigrantStatus_textField = new JTextField(20);
        dhsId_textField = new JTextField(20);
        dhsName = new JLabel("DHS Name:");
        immigrantId = new JLabel("Immigrant ID:");
        immigrantStatus = new JLabel("Immigrant Status:");
        dhsId = new JLabel("DHS ID:");

        this.table = table;

        // Aligns These Elements to the Right side
        JPanel dhsName_panel = new JPanel();
        dhsName_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dhsName_panel.add(dhsName);
        dhsName_panel.add(dhsName_textField);

        // Aligns These Elements to the Right Side
        JPanel dhsId_panel = new JPanel();
        dhsId_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dhsId_panel.add(dhsId);
        dhsId_panel.add(dhsId_textField);

        // Aligns These Elements to the Right Side
        JPanel immigrantId_panel = new JPanel();
        immigrantId_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        immigrantId_panel.add(immigrantId);
        immigrantId_panel.add(immigrantId_textField);

        // Aligns These Elements to the Right Side
        JPanel immigrantStatus_panel = new JPanel();
        immigrantStatus_panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        immigrantStatus_panel.add(immigrantStatus);
        immigrantStatus_panel.add(immigrantStatus_textField);

        // Structures it so that they are on top of each other going down
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(dhsName_panel);
        panel.add(dhsId_panel);
        panel.add(immigrantId_panel);
        panel.add(immigrantStatus_panel);
        panel.add(submitButton);

        // Specific Button Interaction
        submitButton.addActionListener(event -> {
            String dhsNameText = dhsName_textField.getText(); // Grabs whats in Text Box
            String dhsIdText = dhsId_textField.getText(); // Grabs whats in Text Box
            String immigrantIdText = immigrantId_textField.getText(); // Grabs whats in Text Box
            String immigrantStatusText = immigrantStatus_textField.getText(); // Grabs whats in Text Box

            if (areFull(dhsNameText, dhsIdText, immigrantIdText, immigrantStatusText)) {
                acc = new DHSaccount(dhsNameText, dhsIdText);
                acc.setImmigrantID(immigrantIdText);
                acc.setImmigrantStatus(immigrantStatusText);
                if (!table.addAccDatabase(acc)) {
                    logger.info("Unsuccessful add to workflow table. Try again.");
                }

                frame.dispose();
                table.pushToReviewer(acc);
            }

            else {
                frame.setTitle("Please fill out all required information");
            }
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450, 215);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER)); // Makes Sure items are centered with no weird gaps
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(panel);
    }

    private boolean areFull(String dhsName, String dhsId, String immigrantId, String immigrantStatus) {
        return (!(dhsName.isEmpty() || dhsId.isEmpty() || immigrantId.isEmpty() || immigrantStatus.isEmpty()));
    }

    public static void main(String[] args) {
        // DataEntry obj = new DataEntry();
    }
}