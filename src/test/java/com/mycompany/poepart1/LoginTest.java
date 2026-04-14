package com.mycompany.poepart1;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    public LoginTest() {
    }

    @Test
    public void testCheckusername() {

        String x = "Kyl_1";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkusername(x);
        assertEquals(expResult, result);

    }

    @Test
    public void testCheckpasswordcomplexity() {

        String x = "Ch&&sec@ke99!";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkpasswordcomplexity(x);
        assertEquals(expResult, result);

    }

    @Test
    public void testRegisterUser() {

        Login instance = new Login();
        instance.registerUser();

    }

    @Test
    public void testCheckcellphonenumber() {

        String x = "+27838968976";
        Login instance = new Login();
        boolean expResult = true;
        boolean result = instance.checkcellphonenumber(x);
        assertEquals(expResult, result);

    }

    @Test
    public void testLoginUser() {

        String entereduser = "Kyl_1";
        String enteredpass = "Ch&&sec@ke99!";
        Login instance = new Login("Kyl_1", "Ch&&sec@ke99!", "Kyle", "Rock");
        boolean expResult = true;
        boolean result = instance.loginUser(entereduser, enteredpass);
        assertEquals(expResult, result);

    }

    @Test
    public void testReturnLoginStatus() {

        String entereduser = "Kyl_1";
        String enteredpass = "Ch&&sec@ke99!";
        Login instance = new Login("Kyl_1", "Ch&&sec@ke99!", "Kyle", "Rock");
        String expResult = "Welcome Kyle, Rock it is great to see you again.";
        String result = instance.returnLoginStatus(entereduser, enteredpass);
        assertEquals(expResult, result);

    }

    @Test
    public void testDec() {

        int no = 1;
        Login instance = new Login();
        instance.dec(no);

    }

}
