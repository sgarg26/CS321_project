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

}
