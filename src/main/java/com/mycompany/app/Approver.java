package com.mycompany.app;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Approver implements ActionListener {

    /*
     * Various variables that need to be initialized
     * because they'll be used in various scope
     */
    JButton acceptButton = new JButton();
    JButton denyButton = new JButton();
    JFrame frame = new JFrame();
    JTextArea immId = new JTextArea();
    JTextArea immStat = new JTextArea();
    JTextArea newStat = new JTextArea();

    workflow table;
    DHSaccount user;

    public Approver(DHSaccount user, workflow table) {
        // Boots up the screen and everything Approver does with new immigrant info
        this.table = table;
        this.user = user;

        displayScreen(user.getImmigrantID(), user.getimmigrantStatus(), user.getRequestedImmigrantStatus());
    }

    public void displayScreen(String immigrantId, String immigrantStatus, String newStatus) {

        // Sets the title and sizes it properly
        frame.setTitle("Approver Window");
        frame.pack();

        // Allows buttons to do actions when they're clicked on
        acceptButton.addActionListener(this);
        denyButton.addActionListener(this);

        // Puts text on the buttons so the user knows what does what
        acceptButton.setText("ACCEPT");
        denyButton.setText("DENY");

        // Set Sizes of the two buttons
        acceptButton.setPreferredSize(new Dimension(100, 100));
        denyButton.setPreferredSize(new Dimension(100, 100));

        // Change the colors of the button for better clarity
        acceptButton.setBackground(Color.GREEN);
        denyButton.setBackground(Color.RED);

        immId.setText("Immigrant ID: " + immigrantId);
        immStat.setText("Immigrant Status: " + immigrantStatus);
        newStat.setText("Requested Status: " + newStatus);

        immId.setBounds(5, 20, 265, 50);
        immStat.setBounds(5, 70, 265, 50);
        newStat.setBounds(5, 120, 265, 50);

        createTextAreaSpecs(immId, 15, immId.getWidth(), immId.getHeight());
        createTextAreaSpecs(immStat, 15, immStat.getWidth(), immStat.getHeight());
        createTextAreaSpecs(newStat, 15, newStat.getWidth(), newStat.getHeight());

        acceptButton.setBounds(270, 0, 150, 150);
        denyButton.setBounds(430, 0, 150, 150);

        // Adds the buttons onto the frame so the buttons are seen on the screen
        frame.getContentPane().add(immStat);
        frame.getContentPane().add(immId);
        frame.getContentPane().add(newStat);

        frame.getContentPane().add(acceptButton);
        frame.getContentPane().add(denyButton);

        // Method that sets the size, makes it visible, etc
        createFrameSpecs(frame, 600, 250);

    }

    /*
     * This method determines what happens when a button is pressed
     */
    public void actionPerformed(ActionEvent e) {

        // If the accept button was clicked, perform all this
        if (e.getSource() == acceptButton) {

            // Old window isn't needed anymore so we dispose it
            frame.dispose();

            // New Frame for what shows after clicking accept
            JFrame acceptFrame = new JFrame();
            createFrameSpecs(acceptFrame, 400, 150);
            acceptFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

            // Text with an appropriate size
            JTextArea textArea = new JTextArea("IMMIGRANT STATUS APPROVED");
            createTextAreaSpecs(textArea, 20, 350, 200);

            // Adds the new text onto the frame so user can see it
            acceptFrame.getContentPane().add(textArea, BorderLayout.CENTER);

            table.startDataEntry();
        }

        // If the deny button was clicked, perform all this
        else if (e.getSource() == denyButton) {

            // Old window isn't needed anymore so we dispose it
            frame.dispose();

            // New frame for what shows after clicking deny
            JFrame denyFrame = new JFrame();
            createFrameSpecs(denyFrame, 400, 150);
            denyFrame.setLayout(new FlowLayout(FlowLayout.CENTER));

            // Text with an appropriate size
            JTextArea textArea = new JTextArea("SENT BACK TO REVIEWER FOR FURTHER REVIEW");
            createTextAreaSpecs(textArea, 20, 350, 200);

            // Adds the new text onto the frame so user can see it
            denyFrame.getContentPane().add(textArea, BorderLayout.CENTER);

            table.pushToReviewer(user);
        }

    }

    /*
     * Private method for all the little details that all my JTextArea's have.
     * For example, they all are the same font, CAN'T be edited, and allow for
     * the text to be wrapped from one line to the next
     */
    private void createTextAreaSpecs(JTextArea textArea, int size, int width, int height) {

        textArea.setFont(new Font(null, Font.PLAIN, size));
        textArea.setSize(width, height);
        textArea.setEditable(false);

        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);

    }

    /*
     * Private method for all the little details that all my frames come along with
     * For example, they all show up on the center of the screen, can have certain
     * sizes
     * and then the frame is visible
     */
    private void createFrameSpecs(JFrame frame, int width, int height) {
        frame.setLayout(null);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // DHSaccount dhsAcc = new DHSaccount("Tester123", "12379850326");
        // Approver approver = new Approver(dhsAcc);
    }
}
