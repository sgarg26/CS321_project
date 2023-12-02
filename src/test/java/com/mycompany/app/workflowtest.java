package com.mycompany.app;

import static org.junit.Assert.*;

import org.junit.Test;

public class workflowtest {

    //Test to make sure that workflow is adding business objects correctly - Taj
    @Test
    public void workflowImmigrantGetterTest(){
        workflow obj = new workflow();
        obj.addAccDatabase(new DHSaccount("Guacamole", "51"));

        assertFalse("Workflow object isn't added immigrant's into workflow correctly", obj.getImmigrantID().equals("51") && obj.getImmigrantStatus().equals("Guacamole"));
    }

    //Test to make sure the business object is being added in full - Taj
    @Test
    public void workflowDHSObject(){
        workflow obj = new workflow();
        DHSaccount him = new DHSaccount("Him", "213");

        obj.addAccDatabase(him);

        assertEquals("Workflow isn't retrieving object correctly", him, obj.getImmigrant());
    }

    //Test to make sure the queue is working correctly when there's multiple DHS working - Taj
    @Test
    public void workflowAddingDHSEmployee(){

        workflow obj = new workflow();

        DHSaccount tim = new DHSaccount("Tim", "183");
        DHSaccount beatrice = new DHSaccount("Beatrice", "175");

        obj.addAccDatabase(tim);
        obj.addAccDatabase(beatrice);

        assertEquals("Workflow isn't adding multiple objects correctly", tim, obj.getImmigrant());
    }

    //Test to make sure the queue is working correctly when removing a DHS employee from the queue - Taj
    @Test
    public void workflowTakingOutDHSEmployee(){
        workflow obj = new workflow();

        DHSaccount reep = new DHSaccount("Reep", "1052");
        DHSaccount beep = new DHSaccount("Beep", "234");

        obj.addAccDatabase(reep);
        obj.addAccDatabase(beep);

        obj.remAccDatabase();

        assertEquals("Workflow isn't removing from the queue correctly", beep, obj.getImmigrant());
    }

    //Test to make sure that the workflow constructor doesn't allow for an id of 0 - Tajveer Saini
    @Test
    public void workflowZeroTest(){
        workflow obj = new workflow();
        obj.addAccDatabase(new DHSaccount("bob", "0"));

        assertFalse("Workflow object shouldn't have an id of 0", obj.getImmigrantStatus().equals("0"));
    }

    //Test to make sure that the workflow constructor doesn't allow for an immigrantStatus of null - Tajveer Saini
    @Test
    public void workflowNullTest(){
        workflow obj = new workflow();
        obj.addAccDatabase(new DHSaccount("nullBob", null));

        assertFalse("Workflow object shouldn't have a string of null", obj.getImmigrantStatus().equals("null"));
    }

    //Test to make sure there's no empty string - Taj
    @Test
    public void workflowEmptyStringTest(){
        workflow obj = new workflow();
        obj.addAccDatabase(new DHSaccount("", "71"));
        
        assertFalse("Workflow object shouldn't have a string that's empty", obj.getImmigrantStatus().equals(""));
    }
    
}