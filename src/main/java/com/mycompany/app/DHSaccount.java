package com.mycompany.app;

import java.util.regex.*;

public class DHSaccount {
    private String dhsName, dhsID;

    private String immigrantID;
    private String immigrantStatus;
    protected String newStatus;

    // Basic constructor for DHS to do what they need to do for their job
    public DHSaccount(String dhsName, String dhsID) {
        this.dhsName = dhsName;
        this.dhsID = dhsID;

        this.immigrantID = null;
        this.immigrantStatus = null;

        this.newStatus = null;

        if (dhsName == null || dhsName.isEmpty())
            this.dhsName = "ERROR INVALID NAME";

        if (dhsID == null || dhsID.isEmpty())
            this.dhsID = "-1";
    }

    // validates if the string given is only alpha characters and allows the
    // inclusion of spaces
    public boolean validateImmigrantStatus(String Status) {
        if (Status == null || Status.isEmpty())
            return false;
        newStatus = Pattern.matches("[a-zA-Z\s]*", Status) ? Status : null;
        return (newStatus != null) ? true : false;
    }

    // validates if the string given is only numeric characters
    public boolean validateImmigrantID(String ID) {
        if (ID == null || ID.isEmpty())
            return false;

        immigrantID = Pattern.matches("[0-9]*", ID) ? ID : null;
        return (immigrantID != null) ? true : false;
    }

    // Get DHS account Name
    public String getAccName() {
        return dhsName;
    }

    // Set DHS account Name
    public void setAccName(String name) {
        dhsName = name;
    }

    // Get DHS account UID
    public String getAccID() {
        return dhsID;
    }

    // Set DHS account UID
    public void setAccID(String ID) {
        dhsID = ID;
    }

    // Get immigrant UID
    public String getImmigrantID() {
        return immigrantID;
    }

    // Set immigrant UID
    public void setImmigrantID(String ID) {
        immigrantID = ID;
    }

    // Get immigrant status
    public String getimmigrantStatus() {
        return immigrantStatus;
    }

    // Set immigrant status
    public void setImmigrantStatus(String status) {
        immigrantStatus = status;
    }

    // Get new immigrant status
    public String getRequestedImmigrantStatus() {
        return newStatus;
    }
}