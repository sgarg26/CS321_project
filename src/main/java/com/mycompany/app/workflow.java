package com.mycompany.app;

import java.util.LinkedList;
import java.util.Queue;

public class workflow {
    private Queue<DHSaccount> list;

    public workflow() {
        list = new LinkedList<>();
    }

    // Updates the Database with new information
    public boolean addAccDatabase(DHSaccount acc) {
        list.add(acc);
        return true;
    }

    // Removes Head from list and returns true if operation succeeds
    public boolean remAccDatabase() {
        list.remove();
        return true;
    }

    // The function returns the first element in a list of DHS accounts.

    public DHSaccount getImmigrant() {

        return list.peek();
    }

    // The function returns the immigrant status of the first element in a list, or
    // an error message if the
    // list is empty or the immigrant status is null.

    public String getImmigrantStatus() {

        if (list.peek() == null || list.peek().getimmigrantStatus() == null) {
            return "ERROR INVALID NAME";
        }

        return list.peek().getimmigrantStatus();

    }

    // The function "getImmigrantID" returns the immigrant ID of the first element
    // in a list, or "-1" if
    // the list is empty or the immigrant ID is null.

    public String getImmigrantID() {

        if (list.peek() == null || list.peek().getImmigrantID() == null) {
            return "-1";
        }

        return list.peek().getImmigrantID();
    }

    // The startDataEntry function creates a new instance of the DataEntry class.
    public void startDataEntry() {
        @SuppressWarnings("unused")
        DataEntry d = new DataEntry(this);
    }

    // Pushes the information of the immigrant to the reviewer to make changes
    public void pushToReviewer(DHSaccount acc) {
        @SuppressWarnings("unused")
        Reviewer r = new Reviewer(acc, this, "ID: " + acc.getAccID() + " Name: " + acc.getAccName());
    }

    // Pushes the information of the immigrant to the approver to approve changes
    // and submit
    public void pushToApprover(DHSaccount acc) {
        @SuppressWarnings("unused")
        Approver a = new Approver(acc, this);
    }

}
