
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester {
    public void testEncryptShort () {
        int key = 4;
        CaesarCipher CC = new CaesarCipher(key);
        String messageToCipher = "I want cakes.";
        String messageFromCipher = CC.encrypt(messageToCipher);
        
        System.out.println("\nBefore cipher: " + messageToCipher + 
                           "\nAfter  cipher: " + messageFromCipher);
    }
    
    public void testEncryptFile () {
        int key = 4;
        CaesarCipher CC = new CaesarCipher(key);
        FileResource fr = new FileResource();
        String messageToCipher = fr.asString();
        String messageFromCipher = CC.encrypt(messageToCipher);
        
        System.out.println("\nBefore cipher: " + messageToCipher + 
                           "\nAfter  cipher: " + messageFromCipher);
    }
    
    public void testDecryptShort () {
        //int key = 4;
        CaesarCracker CC = new CaesarCracker();
        String messageToCracker = "M aerx geoiw iiiii."; //I want cakes eeeee.
        String messageFromCracker = CC.decrypt(messageToCracker);
        
        System.out.println("\nBefore cracker: " + messageToCracker + 
                           "\nAfter  cracker: " + messageFromCracker);        
    }
    
    public void testDecryptFile () {
        //int key = 4;
        CaesarCracker CC = new CaesarCracker();
        FileResource fr = new FileResource();
        String messageToCracker = fr.asString();
        String messageFromCracker = CC.decrypt(messageToCracker);
        int key = CC.getKey(messageToCracker);
        
        System.out.println("\nBefore cracker: " + messageToCracker +
                           "\nThe key is: " + key +
                           "\nAfter  cracker: " + messageFromCracker);        
    } 
    
    public void testDecryptFilePortuguese () {
        //int key = 4;
        CaesarCracker CC = new CaesarCracker('a');
        FileResource fr = new FileResource();
        String messageToCracker = fr.asString();
        String messageFromCracker = CC.decrypt(messageToCracker);
        int key = CC.getKey(messageToCracker);
        
        System.out.println("\nBefore cracker: " + messageToCracker +
                           "\nThe key is: " + key +
                           "\nAfter  cracker: " + messageFromCracker);        
    } 
    
    public void testVigenereCipher () {
        int[] key = {17, 14, 12, 4};
        VigenereCipher VC = new VigenereCipher(key);
        FileResource fr = new FileResource();
        String messageToCipher = fr.asString();
        String messageFromCipher = VC.encrypt(messageToCipher);
        
        System.out.println("\nBefore cipher: " + messageToCipher + 
                           "\nAfter  cipher: " + messageFromCipher);       
    }

    public void testSliceString () {
        VigenereBreaker VB = new VigenereBreaker();
        
        String message = "abcdefghijklm";
        int whichSlice = 4;
        int totalSlices = 5;
        // bfj
        String sliceString = VB.sliceString(message, whichSlice, totalSlices);
        System.out.println("Sliced string is: " + sliceString);
    }
 
    public void testTryKeyLength () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        VigenereBreaker VB = new VigenereBreaker();
        int[] key = VB.tryKeyLength(encrypted, 4, 'e');
        System.out.println(Arrays.toString(key));
    }
    
    public void testBreakVigenere () {
        VigenereBreaker VB = new VigenereBreaker();
        VB.breakVigenere ();
    }
    
    public void testMostCommonCharIn () {
        VigenereBreaker VB = new VigenereBreaker();
        FileResource fr = new FileResource();
        HashSet rD = VB.readDictionary(fr);
        char mostCommonChar = VB.mostCommonCharIn (rD);
        System.out.println(mostCommonChar);
    }
}
