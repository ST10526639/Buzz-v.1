/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poepart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author basso
 */
public class MessagesTest {
    
    public MessagesTest() {
    }
    
    @Before
    public void setUp() {
        // Reset all static counters/lists before every test so tests are isolated
        Messages.resetForTesting();
    }

    @Test
    public void testCheckmessageid() { // i put the id generator in the main class by accident. (it runs tho)
        String x = "1234567891";
        Messages instance = new Messages();
        boolean expResult = true;
        boolean result = instance.checkmessageid(x);
        assertEquals(expResult, result);
    
    }

    @Test
    public void testCheckrecipientcell() {
        String x = "+27603421226";
        Messages instance = new Messages();
        String expResult = "Cellphone number successfully captured!";
        String result = instance.checkrecipientcell(x);
        assertEquals(expResult, result);
    }

    @Test
    public void testMess() {
        String Currentmess = "Hello";
        Messages instance = new Messages();
        String expResult = Currentmess;
        String result = instance.Mess(Currentmess);
        assertEquals(expResult, result);
    }

    @Test
    public void testID() {
        long x = 3_000_123_000L;
        Messages instance = new Messages();
        String expResult = String.valueOf(x);
        String result = instance.ID(x);
        assertEquals(expResult, result);
    }

    @Test
    public void testMessno() {
        int NoMessage = 2;
        Messages instance = new Messages();
        int expResult = 2;
        int result = instance.messno(NoMessage);
        assertEquals(expResult, result);
    }

    @Test
    public void testCreatemessagehash() {
  
        Messages instance = new Messages();
        String result = instance.createmessagehash();
        assertTrue(result.matches("12:1:HelloHello"));
    
    }

    @Test
    public void testSentMessage() {
    
        Messages instance = new Messages();
        instance.SentMessage(); 
        assertTrue("SentMessage ran without exception", true);
    }

    @Test
    public void testSetRecipient() {
   
        String r = "+27718693002";
        Messages instance = new Messages();
        instance.setRecipient(r);
        assertEquals(r, instance.getRecipient());
    }

    @Test
    public void testRecordSent() {
        
        Messages instance = new Messages();
        instance.Mess("Hi Mike, can you join us for dinner tonight?");
        instance.setRecipient("+27718693002");
        instance.recordSent();
        assertEquals(1, Messages.getTotalSent());// total sent is 1
    }

    @Test
    public void testRecordStored() {
        // verify hash is made without error
        Messages instance = new Messages();
        instance.Mess("Hi Mike, can you join us for dinner tonight?");
        instance.setRecipient("+27718693002");
        String expectedHash = instance.createmessagehash();
        instance.recordStored(); // store
        // The hash stored should equal the hash I made 
        assertTrue(expectedHash.matches("\\d{2}:\\d+:\\S+"));
    }

    @Test
    public void testRecordDisregarded() {
        //  disregard a message,verifies hash is made 
        Messages instance = new Messages();
        instance.Mess("Hi Mike, can you join us for dinner tonight?");
        String expectedHash = instance.createmessagehash();
        instance.recordDisregarded(); // stores in disregarded list
        // Verify hash format
        assertTrue(expectedHash.matches("\\d{2}:\\d+:\\S+"));
    }

    @Test
    public void testGetTotalSent() {
        //  resets, so total starts at 0 for every test
        int expResult = 0;
        int result = Messages.getTotalSent();
        assertEquals(expResult, result);
    }

    @Test
    public void testPrintSentMessages() {
        Messages.printSentMessages(); //  "No messages have been sent yet."
        assertEquals(0, Messages.getTotalSent());
    }

    @Test
    public void testWriteMessagesToJSON() {
        // Write to a temp file
        String filename = new java.io.File(System.getProperty("java.io.tmpdir"), "messages_test.json").getAbsolutePath();
        Messages.writeMessagesToJSON(filename);
        // Verifies file was created
        java.io.File f = new java.io.File(filename);
        assertTrue("JSON file should have been created at: " + filename, f.exists());
        f.deleteOnExit();  //I actaully dont kn ow what this does 
    }
    
}
