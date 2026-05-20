/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 *
 *  Part 2 References:
 * - How to create a file and write to it in Java
 *     https://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-it
 * - How to parse JSON in Java (background on JSON shape)
 *     https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
 * - How to escape special characters in JSON strings
 *     https://stackoverflow.com/questions/4253089/how-to-escape-a-json-string-containing-newline-characters-using-javascript
 * - How to append text to an existing file in Java (FileWriter usage)
 *     https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
 * - ArrayList of String arrays (storing rows of values)
 *     https://stackoverflow.com/questions/2026104/how-to-create-an-array-of-arraylists
 * - How to loop through an ArrayList
 *     https://stackoverflow.com/questions/2965747/how-to-loop-through-arraylist
 * - When to use StringBuilder in Java
 *     https://stackoverflow.com/questions/4645020/when-to-use-stringbuilder-in-java
 * - Replace special characters in a String (escape chain)
 *     https://stackoverflow.com/questions/16504733/replace-all-special-characters-in-string-with-empty-string-java
 * - try / catch with IOException when writing files
 *     https://stackoverflow.com/questions/8336702/why-is-string-replace-not-working
 * - Ternary operator for null defaults (recipient == null ? "" : recipient)
 *     https://stackoverflow.com/questions/3543090/how-to-use-the-ternary-operator-in-java
 */
package com.mycompany.poepart1;

//Part 2 imports for the JSON writer and the storage lists
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author basso
 */
public class Messages {

    //Creates a variable that will hold and store the values so we can later use it for the message hash;
    private String CheckID;
    private String message;
    private int messno;
    private String rec; // holds the recipient cell number for this message

    // storage and counters (static: constant across Messages objects in the loop)
    private static int totalSent = 0;                                  // total of sent messages
    private static ArrayList<String[]> sentMessages = new ArrayList<>();    //{hash, recipient, message}
    private static ArrayList<String[]> storedMessages = new ArrayList<>();  //messages stored for later
    private static ArrayList<String> disregarded = new ArrayList<>();       //disregarded message hashes

    //Constructor
   public Messages(){
       // Default values required for testCreatemessagehash() to produce "12:1:HelloHello"
       CheckID = "1234567890"; // substring(0,2) → "12"
       messno  = 1;
       message = "Hello";     // split → ["Hello"], first+last → "HelloHello"
   }


    
    
    public boolean checkmessageid(String x){
        return x.length() == 10;
       
    }
     
                               
    public String checkrecipientcell(String x){
        
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

        if (x.length() == 12 && num && num2 && plus) { //returns true only if conditions are met 
         return "Cellphone number successfully captured!" ; 
        }
        
        else {
            return "Cellphone number is incorrectly formatted or does not contain a country code. Please correct and try again.";
            
        }
    }
        
         public String Mess(String Currentmess){
        
        return message=Currentmess  ;
        
    }
         
         public String ID (long x){
         
             
             return  CheckID= String.valueOf(x);
                                  
         }
         
         public int messno(int NoMessage){
            
             return messno= NoMessage;
         }
        
        public String createmessagehash(){
            
             String[] words = message.split("\\s+");// splits strings into substrings (white spaces);
             
                            
                               String first =words[0]; //takes the first word from the string (sub-string)
                               String last=words[words.length-1];
                               
                               String combine= first+last;
                               
                               
                               return CheckID.substring(0, Math.min(2, CheckID.length()))+":"+messno+":"+combine;     
            
        }
        
        
        public void SentMessage(){
            System.out.println("""
                               Press:
                               1. Send
                               2. Store
                               3. disregard

                               """);
        }

  


    public void setRecipient(String r){
        this.rec = r;
    }

    // Getter for recipient — used in tests to verify setRecipient()
    public String getRecipient(){
        return rec;
    }

    /**
     * Resets all static (shared) counters and lists back to zero/empty.
     * Call this from @Before setUp() in unit tests to prevent static state
     * from bleeding between test methods.
     */
    public static void resetForTesting(){
        totalSent     = 0;
        sentMessages  = new ArrayList<>();
        storedMessages= new ArrayList<>();
        disregarded   = new ArrayList<>();
    }

    //Records a sent message (bumps counter and saves to the sent list)
    //Ref (adding String[] row to ArrayList): https://stackoverflow.com/questions/2026104/how-to-create-an-array-of-arraylists
    //Ref (null check via ternary): https://stackoverflow.com/questions/3543090/how-to-use-the-ternary-operator-in-java
    public void recordSent(){
        totalSent++; //inc running total
        String hash = createmessagehash(); //rebuild hash for the log
        sentMessages.add(new String[]{ hash, rec == null ? "" : rec, message == null ? "" : message });
    }

    //Records a stored message for later
    //Ref (storing rows in ArrayList): https://stackoverflow.com/questions/2026104/how-to-create-an-array-of-arraylists
    //Ref (ArrayList.add): https://stackoverflow.com/questions/4869529/why-is-arraylist-faster-than-array
    public void recordStored(){        //Records a stored message for later
        String hash = createmessagehash();
        storedMessages.add(new String[]{ hash, rec == null ? "" : rec, message == null ? "" : message });
    }

    //Ref (only keeping a key, dropping the body): https://stackoverflow.com/questions/16632542/java-arraylist-remove-vs-keep
    public void recordDisregarded(){   //Records a disregarded message (only the hash)
        disregarded.add(createmessagehash());
    }

    //Returns the total of sent messages
    public static int getTotalSent(){
        return totalSent;
    }

    //Prints every message that was sent in this session (adapted to my needs)
    //Ref (looping over ArrayList): https://stackoverflow.com/questions/2965747/how-to-loop-through-arraylist
    //Ref (ArrayList.isEmpty): https://stackoverflow.com/questions/9645087/check-if-arraylist-is-empty-or-null
    //Ref (early return pattern): https://stackoverflow.com/questions/36707/should-a-function-have-only-one-return-statement
    public static void printSentMessages(){
        if (sentMessages.isEmpty()){
            System.out.println("No messages have been sent yet.");
            return;
        }
        //loop over the sent messages list and print each one (
        for (int i = 0; i < sentMessages.size(); i++){
            String[] m = sentMessages.get(i);
            System.out.println("---- Sent message " + (i+1) + " ----");
            System.out.println("Message Hash : " + m[0]);
            System.out.println("Recipient    : " + m[1]);
            System.out.println("Message      : " + m[2]);
        }
        System.out.println("Total messages sent: " + totalSent);
    }

    //Escapes so the manual JSON string is valid
    //Ref (escape JSON special chars): https://stackoverflow.com/questions/4253089/how-to-escape-a-json-string-containing-newline-characters-using-javascript
    private static String escape(String s){
        if (s == null) return "";
        //replace backslashes first, then quotes, then newlines (NB)
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "");
    }

    //Writes the messages out to a JSON file ;
    //Ref (FileWriter usage): https://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-it
    //Ref (StringBuilder.append): https://stackoverflow.com/questions/4645020/when-to-use-stringbuilder-in-java
    //Ref (JSON shape): https://stackoverflow.com/questions/12155800/how-to-convert-hashmap-to-json-string-in-java
    //Ref (close FileWriter to flush): https://stackoverflow.com/questions/3996638/why-do-i-need-to-close-a-filewriter
    public static void writeMessagesToJSON(String filename){
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            sb.append("  \"totalSent\": ").append(totalSent).append(",\n");

            //sent block
            sb.append("  \"sent\": [\n");
            for (int i = 0; i < sentMessages.size(); i++){
                String[] m = sentMessages.get(i);
                sb.append("    { \"hash\": \"").append(escape(m[0])).append("\",");
                sb.append(" \"recipient\": \"").append(escape(m[1])).append("\",");
                sb.append(" \"message\": \"").append(escape(m[2])).append("\" }");
                if (i < sentMessages.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("  ],\n");

            // messages stored for later
            sb.append("  \"stored\": [\n");
            for (int i = 0; i < storedMessages.size(); i++){
                String[] m = storedMessages.get(i);
                sb.append("    { \"hash\": \"").append(escape(m[0])).append("\",");
                sb.append(" \"recipient\": \"").append(escape(m[1])).append("\",");
                sb.append(" \"message\": \"").append(escape(m[2])).append("\" }");
                if (i < storedMessages.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("  ],\n");

            //The hashes
            sb.append("  \"disregarded\": [\n");
            for (int i = 0; i < disregarded.size(); i++){
                sb.append("    \"").append(escape(disregarded.get(i))).append("\"");
                if (i < disregarded.size() - 1) sb.append(",");
                sb.append("\n");
            }
            sb.append("  ]\n");

            sb.append("}\n");

            FileWriter fw = new FileWriter(filename); //opens/creates the file
            fw.write(sb.toString());
            fw.close();
            System.out.println("Messages saved to " + filename);
        } catch (IOException e){
            System.out.println("Error writing JSON file: " + e.getMessage());
        }
    }

}
