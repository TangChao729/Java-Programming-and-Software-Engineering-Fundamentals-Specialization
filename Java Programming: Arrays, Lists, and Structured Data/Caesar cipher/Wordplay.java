
/**
 * Write a description of Wordplay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wordplay {
    public boolean isVowel (char ch) {
        String vowel = "aeiouAEIOU";
        if (vowel.indexOf(ch) == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void testIsVowel () {
        String testString = "Check this string";
        for (char ch : testString.toCharArray()) {
            boolean result = isVowel(ch);
            if (result == false) {
                System.out.println("Character " + ch + " is not a vowel");
            } else {
                System.out.println("Character " + ch + " is a vowel");
            }
        }
    }
    public String replaceVowel (String phrase, char ch) {
        String vowel = "aeiouAEIOU";
        StringBuilder newStr = new StringBuilder(phrase);
        
        for (int i=0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            
            if (vowel.indexOf(currChar) == -1) {
                continue;
            } else {
                newStr.setCharAt(i, ch);
            }
        }
        
        return newStr.toString();

    }
    public void testReplaceVowel () {
        String testString = "Check this string";
        String result = replaceVowel(testString,'*');
        System.out.println(result);
    }
    public String emphsize (String phrase, char ch) {

        StringBuilder newStr = new StringBuilder(phrase);
        
        for (int i=0; i < phrase.length(); i++) {
            char currChar = phrase.charAt(i);
            
            if (currChar != ch) {
                continue;
            } else {
                if (i%2 == 0) {
                    newStr.setCharAt(i, '*');
                } else {
                    newStr.setCharAt(i, '+');
                }
            }
        }
        
        return newStr.toString();

    }
    public void testEmphsize () {
        String testString = "dna ctgaaactga";
        String result = emphsize(testString,'a');
        System.out.println(result);
        
        testString = "Mary Bella Abracadabra";
        result = emphsize(testString,'a');
        System.out.println(result);
    }
}
