/**
 * Author: Zeeshaan Basson AKA ST10526639
 * Date:12 April 2026
 * Project: POE Part 1 BCAD Assignment
 *
 * References:
 * GeeksforGeeks: Java while loop
 * -  https://www.geeksforgeeks.org/java/java-while-loop-with-examples/
 * Joyce Farrell, *Java Programming, 10th Edition*, Cengage Learning, 2023.
 * -Chapter 4:Using classes and objects , pp. 131–134;
 */
package com.mycompany.poepart1;

import java.util.Scanner;

/**
 *
 * @author basso
 */
public class POEpart1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println(" ");
        System.out.println("Register as a User");
        System.out.println(" ");

        System.out.print("Enter Firstname: ");
        String name = sc.next();

        System.out.print("Enter Lastname: ");
        String lname = sc.next();

        String user = "";
        String pass = "";

        Login logg = new Login(); //Create logg object 

        while (true) {
            System.out.print("Please create a username: ");
            user = sc.next();

            if (!logg.checkusername(user)) {   //the ! means the inverted value therefore meaning ig checkusername is false 
                logg.dec(1);//logg calls methods from the login class
                System.out.println("Your username is not formatted correctly; ensure that your username contains an underscore and is no longer than five character in length.");
                logg.dec(1);
            } else {
                System.out.println(" ");
                System.out.println("Username successfully captured.");
                logg.dec(2);
                break;  //breaks the loop when successful  

            }
        }

        while (true) {// same as before 
            System.out.print("Please create a password:");
            pass = sc.next();

            if (!logg.checkpasswordcomplexity(pass)) {

                logg.dec(1);
                System.out.println("""
                   Password is not correctly formatted; please ensure that the password contains at least eight characters,
                   a capital letter, a number, and a special character.
                    """);
                logg.dec(1);
            } else {
                System.out.println(" ");
                System.out.println("Password sucessfully captured.");
                logg.dec(2);
                break;
            }
        }

        while (true) {//same as before
            System.out.print("Please enter your phone number (with international code): ");
            String no = sc.next();

            if (!logg.checkcellphonenumber(no)) {
                System.out.println("""
                                   Cell phone number incorrectly formatted or does not contain international code."
                                   """);

            } else {
                System.out.println("""
                                   Cell phone number successfully added.
                                   
                                   """);
                break;
            }
        }

        logg.dec(2);
        System.out.println("  ");
        logg.registerUser();// calls the register user to create registration message 
        System.out.println("  ");
        logg.dec(2);

        Login userobj = new Login(user, pass, name, lname); //makes the userobj object 

        while (true) { // ensures the user will be asked to re-enter user and password when input wrong 

            System.out.print("Enter username to login: ");
            String loginUser = sc.next();

            System.out.print("Enter password to login: ");
            String loginPass = sc.next();

            if (!userobj.loginUser(loginUser, loginPass)) {
                System.out.println(userobj.returnLoginStatus(loginUser, loginPass));   //there is no break meaning the loop will continue until there is/ the user and password are correct 
            } else {
                System.out.println(userobj.returnLoginStatus(loginUser, loginPass));
                logg.dec(3);
                break; // this ends the loop because the input is correct
            }

        }

    }

}
