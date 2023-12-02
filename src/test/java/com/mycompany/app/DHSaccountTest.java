package com.mycompany.app;

import static org.junit.Assert.*;
// import org.junit.jupiter.api.Test;

import org.junit.Test;

// import DHSaccount;

public class DHSaccountTest {
    @Test
    // Antonios - Test for uninitialized  dhsID = NULL should be -1
    public void DHSconstructorNULLIDTest() { 
        DHSaccount test = new DHSaccount("Bob Franz", null);
        assertEquals("-1", test.getAccID());
    }

    @Test
    // Antonios - Test for trying to set NULL immigrant Status
    public void DHSsetNullStatusTest() { 
        DHSaccount test = new DHSaccount("Bob Franz", "bfranz");
        assertEquals(false, test.validateImmigrantStatus(null));
    }

    @Test
    // Antonios - Test for trying to set EMPTY immigrant Status
    public void DHSsetEmptyStatusTest() { 
        DHSaccount test = new DHSaccount("Bob Franz", "bfranz");
        assertEquals(false, test.validateImmigrantStatus(""));
    }

    @Test
    // Antonios - Test for trying to set null for DHSName
    public void DHSconstructorEmptyStatusTest() { 
        DHSaccount test = new DHSaccount(null, "bfranz");
        assertEquals("ERROR INVALID NAME", test.getAccName());
    }

    @Test
    // Antonios - Test for a valid immigrant Status
    public void SendImmigrantStatusValidStatusTest() { 
        DHSaccount test = new DHSaccount("Bob Franz", "bfranz");
        assertEquals(true, test.validateImmigrantStatus("I am a valid Status"));
    }

    @Test
    // Antonios - Test for a invalid immigrant status in Method (has Numbers)
    public void SendImmigrantStatusEmptyStatusTest() { 
        DHSaccount test = new DHSaccount("Fred Zach", "bfranz");
        assertEquals(false, test.validateImmigrantStatus("I am a Status 123."));
    }

    @Test
    // Antonios - Test for invalid immigrant status in method Combination of letters and spaces (valid) but has other characters and numbers (invalid)
    public void invalidImmigrantStatus2() { 
        DHSaccount test = new DHSaccount("Pop Man", "bfranz");
        assertEquals(false, test.validateImmigrantStatus(".ERROR. : Code 0"));
    }

    @Test
    // Siddharth - Test for correct account name being set.
    public void getAccNameTest() {
        DHSaccount test = new DHSaccount("Jerry Seinfeld", "inactive");
        assertNotEquals("active", test.getAccName());
    }

    @Test
    // Siddharth - Test for correct account ID being set
    public void getAccIDTest() {
        DHSaccount test = new DHSaccount("Jason", "status_1");
        assertEquals("status_1", test.getAccID());
    }

    @Test
    // Siddharth - Test for immigrant ID. Checks if the right status is being set for immigrant id.
    public void getImmigrantIDTest() {
        DHSaccount test = new DHSaccount("Bourne", "status_2");
        test.setImmigrantID("status_2");
        assertEquals("status_2", test.getImmigrantID());
    }

    @Test
    public void validateImmigrantIDTest() { // Siddharth - Test for invalid immigrant id sent to DHS account
        DHSaccount test = new DHSaccount("spidey", "status_active");
        assertFalse(test.validateImmigrantID("invalid_id"));
    }

    @Test
    public void validateImmigrantIDTest2() { // Siddharth - test whether an all numeric immigrant id passes. 
        DHSaccount test = new DHSaccount("spidey", "status_active"); 
        assertTrue(test.validateImmigrantID("1234"));
    }

    @Test
    public void validateImmigrantIDTest3() { // Siddharth - testing whether an immigrant ID will fail with an alphanumeric id
        DHSaccount test = new DHSaccount("spidey", "status_active");
        assertFalse(test.validateImmigrantID("status1234"));
    }

    @Test
    public void validateImmigrantIDTest4() { // Siddharth - testing whether an immigrant ID will fail with a negative id
        DHSaccount test = new DHSaccount("spidey", "status_active");
        assertFalse(test.validateImmigrantID("-1234"));
    }

    @Test
    public void validateImmigrantIDTest5() { // Siddharth - Testing null status sent to validateImmigrantID
        DHSaccount test = new DHSaccount("spidey", "status_active");
        assertFalse(test.validateImmigrantID(null));
    }
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
