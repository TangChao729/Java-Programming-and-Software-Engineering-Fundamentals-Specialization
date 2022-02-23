
/**
 * Write a description of CassarCiphar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.Scanner;

public class CassarCiphar {
    public String encrypt (String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //System.out.println(alphabet);
        
        String shiftUppAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        //System.out.println(shiftedAlphabet);
        
        String shiftLowAlphabet = shiftUppAlphabet.toLowerCase();
        
        StringBuilder encryptedStr = new StringBuilder(input);
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
                    encryptedStr.setCharAt(i, encryptedChar);
                } else {           
                    char encryptedChar = shiftLowAlphabet.charAt(index);
                    //System.out.println(encryptedChar);
                    encryptedStr.setCharAt(i, encryptedChar);
                }
            }    
        }
        
        return encryptedStr.toString();
    }
    public void testEncrypt () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("Please enter the cipher key:");
        Scanner keyInput = new Scanner (System.in);
        int key = keyInput.nextInt();
        
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    public String twoKeyEncrypt(String input, int key1, int key2) {
        StringBuilder encryptedStr = new StringBuilder(input);
        for (int i=0; i < input.length(); i++) {
            if (i%2 == 0) {
                encryptedStr.setCharAt(i, encrypt(Character.toString(input.charAt(i)),key1).charAt(0));
            } else {
                encryptedStr.setCharAt(i, encrypt(Character.toString(input.charAt(i)),key2).charAt(0));
            }
        }
        return encryptedStr.toString();
    }
    public void testTwoKeyEncrypt () {
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("Please enter the cipher key 1:");
        Scanner keyInput1 = new Scanner (System.in);
        int key1 = keyInput1.nextInt();
        
        System.out.println("Please enter the cipher key 2:");
        Scanner keyInput2 = new Scanner (System.in);
        int key2 = keyInput2.nextInt();
        
        String encrypted = twoKeyEncrypt(message, key1, key2);
        System.out.println("key is " + key1 + "/" + key2 + "\n" + encrypted);
    }
    public void shortTestTwoKeyEncrypt () {
        int key1 = 1;
        int key2 = 2;
        String message = "ABCD";
        
        String encrypted = twoKeyEncrypt(message, key1, key2);
        System.out.println("key is " + key1 + "/" + key2 + "\n" + encrypted);
    }
}
