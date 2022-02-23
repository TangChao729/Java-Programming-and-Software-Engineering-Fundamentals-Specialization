
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String keyOne = myText[index];
        String keyTwo = myText[index + 1];
        sb.append(keyOne);
        sb.append(" ");
        sb.append(keyTwo);
        sb.append(" ");
        
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(keyOne, keyTwo);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            keyOne = keyTwo;
            keyTwo = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String keyOne, String keyTwo) {
        ArrayList<String> follows = new ArrayList<String>();
        
        int pos = 0;
        while (pos < myText.length) {
            int start = indexOf(myText, keyOne, keyTwo, pos);
            if (start == -1) {
                break;
            }
            if (start + keyOne.length() + keyTwo.length() >= myText.length - 2) {
                break;   
            }
            String next = myText[start + 2];
            follows.add(next);
            pos = start + 1;
        }

        return follows;
    }
    
    private int indexOf(String[] words, String targetOne, String targetTwo, int start) {
        for (int i=start; i<words.length; i++) {
            if (words[i].equals(targetOne) && words[i+1].equals(targetTwo)) {
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf() {
        String sentence = "this is just a test yes this is a simple test";
        String[] words = sentence.split("\\s+");

        
        String targetOne = "this";
        String targetTwo = "is";
        int start = 0;
        System.out.println(indexOf(words, targetOne, targetTwo, start) 
                           );
                           
        targetOne = "this";
        targetTwo = "is";
        start = 3;
        System.out.println(indexOf(words, targetOne, targetTwo, start) 
                           );
        
        targetOne = "just";
        targetTwo = "a";
        start = 0;
        System.out.println(indexOf(words, targetOne, targetTwo, start));
        
    }
}
