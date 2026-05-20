/**
 * Author: Zeeshaan Basson AKA ST10526639
 * Date:12 April 2026
 * Project: POE assignment Part 1 BCAD
 *
 * References:
 * GeeksforGeeks: Java while loop
 * -  https://www.geeksforgeeks.org/java/java-while-loop-with-examples/
 * Joyce Farrell, *Java Programming, 10th Edition*, Cengage Learning, 2023.
 * -Chapter 4:Using classes and objects , pp. 131–134;
 *
 * Part 2  references:
 * - Scanner.nextLine() returning empty after nextInt() (why we call sc.nextLine() after sc.nextInt())
 *     https://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-or-nextfoo
 * - Using String.startsWith to check a success message prefix
 *     https://stackoverflow.com/questions/2275004/check-if-a-string-starts-with-another-string
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
                System.out.println(userobj.returnLoginStatus(loginUser, loginPass));   //no break meaning: loop will continue until the user and password are correct 
            } else {
                System.out.println(userobj.returnLoginStatus(loginUser, loginPass));
                logg.dec(3);
                break;
            }

        }
        
        Messages ms = new Messages();
      
        
        
        
        System.out.println("""
                           Press for:
                           1. Send messages 
                           2. See recently Sent messages (Coming soon!!)
                           3. Quit
                           """);
        
        int Op = sc.nextInt();
        switch (Op){
            case 1: 
                     System.out.println("How many messages would you like to send?"); 
                     int messnumber = sc.nextInt();
                     sc.nextLine();
                     int count=0;
                     
                     
                     
                     while (count < messnumber+1){
                     count+=1;
                     long checkID = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;

                        String messageID = String.valueOf(checkID);//converts long to string value

                         if (!ms.checkmessageid(messageID)) {
                             System.out.println("There has been an error creating the message ID. Please contact support.");
                         } else {
                          //ask for recipient cell and validate (loop until valid)
                          //Ref (String.startsWith): https://stackoverflow.com/questions/2275004/check-if-a-string-starts-with-another-string
                          String rec = "";
                          while (true){
                              System.out.print("Enter the recipient cell number (with international code +27): ");
                              rec = sc.nextLine();
                              String result = ms.checkrecipientcell(rec);
                              System.out.println(result);
                              if (result.startsWith("Cellphone number successfully"))// (dont have to do the brackets {} when if has one line)
                              break; 
                          }
                          ms.setRecipient(rec); //save the recipient on the Messages object

                          System.out.println("Send your message below:");
                          String chat= sc.nextLine();

                          ms.ID(checkID);
                          ms.messno(count);// stores the message number to create the hash
                          ms.Mess(chat); //this will be the var that stores the message;

                          if (chat.length()<251) {
                           ms.createmessagehash();
                           ms.SentMessage();
                           int sent= sc.nextInt();
                           sc.nextLine(); 
                           
                         
                           switch (sent){
                              case 1:
                                   if (chat.length()< 251) {
                                   System.out.println("Message Sent");
                                   ms.recordSent(); //log sent + bump total counter
                                  }else{
                                   System.out.println("Please enter a message of less than 250 characters");
                                   }
                                   break; 
                              case 2:

                                   System.out.println("Message Successfully Stored");
                                   ms.recordStored(); // stored for later;
                                   break; 

                              case 3:
                                   System.out.println("Message disregarded.");
                                   ms.recordDisregarded(); //keep hash, leave message;
                               break;

                         }
                         } else {
                            // when the message is too long
                            System.out.println("Please enter a message less than 250 characters.");
                         }

                          }
                     }
                     // after the send loop, show the running total 
                     System.out.println("Total messages sent: " + Messages.getTotalSent());
                     break; //stop the outer switch falling through to case 2 
            case 2:
                System.out.println("Coming soon!!");
                Messages.printSentMessages(); //actually print recently sent messages
                break; 


            case 3:
            // Save everything (sent / stored / disregarded) to a JSON file before quitting
            //Ref (writing a file before System.exit): https://stackoverflow.com/questions/22452/calling-removeall-on-an-arraylist
            Messages.writeMessagesToJSON("messages.json");
            System.out.println("Goodbye.");
            System.exit(0);
        }

        // also save to JSON after the switch (for cases 1 and 2)
        Messages.writeMessagesToJSON("messages.json");
        System.out.println("Total messages sent so far: " + Messages.getTotalSent());

    }

}
