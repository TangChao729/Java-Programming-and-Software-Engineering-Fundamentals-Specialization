
/**
 * Write a description of decryption here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.Scanner;
public class decryption {
    public String decrypt (String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //System.out.println(alphabet);
        key = 26-key;
        
        String shiftUppAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //System.out.println(shiftedAlphabet);
        
        String shiftLowAlphabet = shiftUppAlphabet.toLowerCase();
        
        StringBuilder decryptedStr = new StringBuilder(input);
        for (int i=0; i < input.length(); i++) {
            //read each letter from input
            char currCharOri = input.charAt(i);
            //System.out.println(currChar);
            //swap to cap case
            char currCharUpp = Character.toUpperCase(currCharOri);
            
            int index = alphabet.indexOf(currCharUpp);
            //System.out.println(index);
            if (index == -1) {
                continue;
            } else {
                if (Character.isUpperCase(currCharOri)) {            
                    char encryptedChar = shiftUppAlphabet.charAt(index);
                    //System.out.println(encryptedChar);
                    decryptedStr.setCharAt(i, encryptedChar);
                } else {           
                    char encryptedChar = shiftLowAlphabet.charAt(index);
                    //System.out.println(encryptedChar);
                    decryptedStr.setCharAt(i, encryptedChar);
                }
            }    
        }
        
        return decryptedStr.toString();
    }
    public void testDecrypt () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("Please enter the cipher key:");
        Scanner keyInput = new Scanner (System.in);
        int key = keyInput.nextInt();
        
        String decrypted = decrypt(message, key);
        System.out.println("key is " + key + "\n" + decrypted);
    }
    public String twoKeyDecrypt(String input, int key1, int key2) {
        //key1 = 26-key1;
        //key2 = 26-key2;
        
        StringBuilder decryptedStr = new StringBuilder(input);
        for (int i=0; i < input.length(); i++) {
            if (i%2 == 0) {
                decryptedStr.setCharAt(i, decrypt(Character.toString(input.charAt(i)),key1).charAt(0));
            } else {
                decryptedStr.setCharAt(i, decrypt(Character.toString(input.charAt(i)),key2).charAt(0));
            }
        }
        return decryptedStr.toString();
    }
    public void testTwoKeyDecrypt () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("Please enter the cipher key 1:");
        Scanner keyInput1 = new Scanner (System.in);
        int key1 = keyInput1.nextInt();
        
        System.out.println("Please enter the cipher key 2:");
        Scanner keyInput2 = new Scanner (System.in);
        int key2 = keyInput2.nextInt();
        
        String decrypted = twoKeyDecrypt(message, key1, key2);
        System.out.println("key is " + key1 + "/" + key2 + "\n" + decrypted);
    }
    
    public void shortTestTwoKeyDecrypt () {
        int key1 = 14;
        int key2 = 24;
        String message = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        
        String decrypted = twoKeyDecrypt(message, key1, key2);
        System.out.println("key is " + key1 + "/" + key2 + "\n" + decrypted);
    }
    
    public int countLetterE (String input) {
        int counter = 0;
        for (int i=0; i < input.length(); i++) {
            if (Character.toLowerCase(input.charAt(i)) == 'e') {
                counter += 1;
            } 
        }
        return counter;
    }
    public void testCountLetterE() {
        String input = "This is the senetence to count how many E's.";
        int counter = countLetterE(input);
        System.out.println("There are " + counter + " E's.");
        
    }
    
    public String countLetterMost (String input) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int[] totalCounts = new int[31];

        for (int i=0; i < input.length(); i++) {
            int indexOfAlph = alphabet.toLowerCase().indexOf(input.charAt(i));
            //System.out.println("current i is: " + i);
            //System.out.println("current index is: " + indexOfAlph);
            if (indexOfAlph != -1) {
                totalCounts[indexOfAlph] += 1;
            }
        }
        //System.out.println("length of array: " + totalCounts.length);
        //System.out.println("no.1 in array: " + totalCounts[1]);
        //System.out.println("no.2 in array: " + totalCounts[2]);
        
        int maxPosition = 0;  
        int counter = 0;
        for (int k=0; k < totalCounts.length; k++) {
            if (totalCounts[k] > counter) {
                counter = totalCounts[k];
                //System.out.println("counter: " + counter);
                maxPosition = k;
                //System.out.println("LetterPosition: " + maxPosition);
                //System.out.println("index K is: " + k);
                //System.out.println("============================");
            }
        }   
        
        return Character.toString(alphabet.charAt(maxPosition));
    }    
    

    
    public void testCountMost() {
        String input = "Gp  bqpok feiq cbbbbbbbbb";
        String mostLetter = countLetterMost(input);
        System.out.println("The most frequent letter is " + mostLetter);
        
    }
    
    public int keyFinder (String input) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder decryptedStr = new StringBuilder(input);
        
        String mostLetter = countLetterMost(input);
        //System.out.println("MostLetter is: " + mostLetter);
        //find the key
        int indexTemp1 = alphabet.indexOf(mostLetter);
        //System.out.println("indexTemo1 " + indexTemp1);
        int key = 0;
        if (indexTemp1 < 4) {
            key = 26 - 4 + indexTemp1;
            System.out.println("key is " + key);
        } else {
            key = indexTemp1 - 4;
            System.out.println("key is " + key);
        }
        
        return key;
    }
    
    public void testKeyFinder () {
        String encryptedMessage = "BCDEFFF";
        //original message should be ABCDEEE

        int key = keyFinder(encryptedMessage);
        
        String decrypted = decrypt(encryptedMessage, key);
        System.out.println("key is " + key + "\n" + decrypted);
    }
    
    public void blindTwoKeyDecrypt(String input) {
        //separate one code to two string
        String string1 = "";
        String string2 = "";
        for (int i=0; i < input.length(); i++) {
            if (i%2 == 0) {
                string1 += Character.toString(input.charAt(i));
            } else {
                string2 += Character.toString(input.charAt(i));
            }
        }
        
        
        int key1 = keyFinder(string1);
        String decrypted1 = decrypt(string1, key1);
        //System.out.println("String 1 is: " + string1);
        //System.out.println("Decrypted 1 is: " + decrypted1);  
        
        int key2 = keyFinder(string2);
        String decrypted2 = decrypt(string2, key2);
        //System.out.println("String 2 is: " + string2);
        //System.out.println("Decrypted 2 is: " + decrypted2);
        
        String decryptedString = "";
        for (int i=0; i < decrypted1.length()-1; i++) {
            decryptedString += Character.toString(decrypted1.charAt(i));
            decryptedString += Character.toString(decrypted2.charAt(i));
            }
        
        
        System.out.println("Decrypted string is: " + decryptedString);
    }
    
    public void shortTestBlindTwoKeyDecrypt () {
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        
        blindTwoKeyDecrypt(message);
    }
    
    public void properTestBlindKey () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        blindTwoKeyDecrypt(message);
    }
}
