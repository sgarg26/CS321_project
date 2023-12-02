package com.mycompany.app;

// Main Class to Tie Everything in with Each Other
public class App {
    private static workflow table;

    public static void main(String[] args) {
        table = new workflow(); // Creates Workflow table
        table.startDataEntry(); // starts program
    }
}
