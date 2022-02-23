
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MarkovFour {
    private String myText;
    private Random myRandom;
    
    public MarkovFour() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        
        StringBuilder sb = new StringBuilder();
        sb.append(key);
        
        for(int k=0; k < numChars -4; k++){ //MarkovFour
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;

        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key) {
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
                charFollows.add(
                Character.toString(cha));
                //System.out.println(matchingString + " is matching to " + key);
            } else {
                //System.out.println(matchingString + " is not matching to " + key);
            }
        }
        
        return charFollows;
    }
}
