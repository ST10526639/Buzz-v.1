/**
 * Author: Zeeshaan Basson AKA ST10526639
 * Date:12 April 2026
 * Project: Login.class BCAD Assignment
 *
 * References:
 *StackOverflow: How to make x number a parameters in a constructor java.
 * -(https://stackoverflow.com/questions/64362766/how-to-make-a-x-number-of-parameters-in-a-constructor-java)
 * Joyce Farrell, *Java Programming, 10th Edition*, Cengage Learning, 2023.
 * -Chapter 5: using switch, pp. 181–185;
 */
package com.mycompany.poepart1;

/**
 *
 * @author basso
 */
public class Login {

    @SuppressWarnings("empty-statement")

    /*   // Source - https://stackoverflow.com/q/64362766
      // Posted by mry_02
      // Retrieved 2026-04-12 20:02, License - CC BY-SA 4.0
     */
    //coded to my specific needs inspired by stackoverflow constructor;
    //Creates a variable that will hold and store the values so we can later compare the login details to the registration details ;
    private String user;
    private String pass;
    private String name;
    private String lname;

    // Constructor
    public Login(String user, String pass, String name, String lname) {
        this.user = user;
        this.pass = pass;
        this.name = name;
        this.lname = lname;
    }

    public Login() {

    }

    public boolean checkusername(String x) {

        return x.contains("_") && x.length() <= 5;//validates that the username contains an _ and is 5 characters or smaller
    }

    public boolean checkpasswordcomplexity(String x) {

        int count = 0;                 //Initialise count and the booleans 
        boolean spec = false;
        boolean num = false;
        boolean upp = false;

        for (int i = 0; i < x.length(); i++) {
            count += 1;// counts the number of characters in the password
            char ch = x.charAt(i);//i increases until it = lenth of x 

            if (Character.isDigit(ch)) //checks if each character meets criteria 1 by 1 until each boolean is true 
            {
                num = true;                               //or until i=x.length
            } else if (Character.isUpperCase(ch)) {
                upp = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                spec = true;
            }

        }

        return count >= 8 && spec && num && upp;

    }

    public void registerUser() {

        System.out.println("User has been registered successfully.");

    }

    public boolean checkcellphonenumber(String x) {

        boolean num = false;      //initialise the booleans 
        boolean num2 = false;
        boolean plus = false;

        char pl = x.charAt(0);     // if the char is the correct calue the boolean in then true 
        if (pl == '+') {
            plus = true;
        }

        char ch = x.charAt(1);
        if (ch == '2') {
            num = true;
        }

        char ch2 = x.charAt(2);
        if (ch2 == '7') {
            num2 = true;
        }

        return x.length() == 12 && num && num2 && plus; //returns true only if conditions are met 

    }

    public boolean loginUser(String entereduser, String enteredpass) {      //tests whether the users login details match the registration details/stored details
        return this.user.equals(entereduser) && this.pass.equals(enteredpass);
    }

    public String returnLoginStatus(String entereduser, String enteredpass) { //if details match then welcome message is displayed and if not the error message '
        if (loginUser(entereduser, enteredpass)) {
            return "Welcome " + name + ", " + lname + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    //coded with assisstance of Java Programming 10th Edition, Chapter 5

    public void dec(int no) { // various decorations ( so i dont have to copy and paste as much ).

        switch (no) {
            case 1:
                System.out.println("==========================================================================================================================");
                break;
            case 2:
                System.out.println("   ");
                System.out.println("*******************************************************************");
                break;
            default:
                System.out.println("""
                                   ===================================================
                                   =                                                 =
                                   =              W E L C O M E   T O                =
                                   =                                                 =
                                   =              >>>     BUZZ       <<<             =
                                   =                                                 =
                                   ===================================================
                                          """);
                break;
        }

    }

}
