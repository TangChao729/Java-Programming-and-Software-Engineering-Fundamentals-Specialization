
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myOrder = order;
        myRandom = new Random();
        
        //keyLength = order; //used for toString method, equal to myOrder
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.split("\\s+");
        //buildMap();
    }
    
    public void buildMap() {
        map = new HashMap<WordGram, ArrayList<String>>();
        for(int i=0; i < myText.length - myOrder; i++) {
            WordGram keyGram = new WordGram(myText, i, myOrder);
            
            if (!map.containsKey(keyGram)) {
                ArrayList<String> follows = new ArrayList<String>();
                map.put(keyGram, follows);
                map.get(keyGram).add(myText[i + myOrder]);
            } else {
                map.get(keyGram).add(myText[i + myOrder]);
            }
        }
        
        WordGram lastKeyGram = new WordGram(myText, myText.length - myOrder, myOrder);
        if (!map.containsKey(lastKeyGram)) {
            ArrayList<String> empty = new ArrayList<String>();
            map.put(lastKeyGram, empty);
        }
        
        printHashMapInfo();
    } 
    
    public void printHashMapInfo() {
        //System.out.println("HashMap: " + map);
        System.out.println("HashMap size: " + map.size());
        
        int currentMax = 0;
        for (WordGram wg: map.keySet()) {
            if (map.get(wg).size() > currentMax) {
                currentMax = map.get(wg).size();
            }
        }
        System.out.println("Size of the largest ArrayList of characters: " + currentMax);
        
        for (WordGram wg: map.keySet()) {
            if (map.get(wg).size() == currentMax) {
                System.out.println("Keys : " + wg.toString());
                System.out.println("Keys contain: " + map.get(wg));
            }
        }
        
    }
    
    public ArrayList<String> getFollowsEff(WordGram key) { 
        return map.get(key);
    }  
    
    public String getRandomText(int numWords) {
        buildMap();
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
        sb.append(" ");
        
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollowsEff(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = key.shiftAdd(next);
        }
        return sb.toString().trim();
    }
}
