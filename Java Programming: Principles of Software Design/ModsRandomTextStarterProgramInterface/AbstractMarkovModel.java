
/**
 * Write a description of AbstractMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel{
    protected String myText;
    protected Random myRandom;
    protected int keyLength;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String text) {
        myText = text.trim();
    }
    
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> charFollows = new ArrayList<String>();
        if (myText == null) {
            return null;
        }
        
        for (int i=0; i < myText.length()-key.length(); i++ ) {
            String matchingString = myText.substring(i, i + key.length());
            //System.out.println(matchingString);
            //System.out.println(cha);
            if (matchingString.equals(key)) {
                char cha = myText.substring(i+key.length(), i+1+key.length()).charAt(0);
                charFollows.add(Character.toString(cha));
                //System.out.println(matchingString + " is matching to " + key);
            } else {
                //System.out.println(matchingString + " is not matching to " + key);
            }
        }
        
        return charFollows;
    }
    
    @Override
    public String toString() {
        return "MarkovModel of order " + keyLength;
    }
}
