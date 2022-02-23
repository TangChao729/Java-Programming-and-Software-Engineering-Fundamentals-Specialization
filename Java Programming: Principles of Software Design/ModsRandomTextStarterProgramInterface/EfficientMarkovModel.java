
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int myOrder;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int order) {
        myOrder = order;
        keyLength = order; //used for toString method, equal to myOrder
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    public void buildmap() {
        map = new HashMap<String, ArrayList<String>>();
        //we knew myOrder(aka keyLength)
        for(int i=0; i < myText.length() - myOrder; i++) {
            
            String tempKey = myText.substring(i,i + myOrder);
            
            if (!map.containsKey(tempKey)) {
                //if doesnt contain, add key and empty ArrayList
                ArrayList<String> follows = new ArrayList<String>();
                map.put(tempKey, follows);
                //since the ArrayList is emoty, add the following char to ArrayList
                if (i + myOrder + 1 > myText.length()) {
                    continue;
                } else {
                    map.get(tempKey).add(myText.substring(i + myOrder, i + myOrder + 1));
                }
            } else {
                if (i + myOrder + 1 > myText.length()) {
                    continue;
                } else {
                    map.get(tempKey).add(myText.substring(i + myOrder, i + myOrder + 1));
                }
            }
        }
        //add last key 
        String lastKey = myText.substring(myText.length() - myOrder);
        ArrayList<String> empty = new ArrayList<String>();
        map.put(lastKey, empty);
        
        printHashMapInfo();
    }
    
    public void printHashMapInfo() {
        //System.out.println("HashMap: " + map);
        System.out.println("HashMap size: " + map.size());
        
        int currentMax = 0;
        for (String s: map.keySet()) {
            if (map.get(s).size() > currentMax) {
                currentMax = map.get(s).size();
            }
        }
        System.out.println("Size of the largest ArrayList of characters: " + currentMax);
        
        for (String s: map.keySet()) {
            if (map.get(s).size() == currentMax) {
                System.out.println("Keys : " + s);
                System.out.println("Keys contain: " + map.get(s));
            }
        }
        
    }
    
    public ArrayList<String> getFollowsEff(String key) { 
        return map.get(key);
    }    
    
    public String getRandomText(int length) {
        buildmap();
        //initial a empty sb
        StringBuffer sb = new StringBuffer();
        //random pick index number (make sure not out of range)
        int index = myRandom.nextInt(myText.length() - myOrder);
        //form new string from index
        String current = myText.substring(index, index + myOrder);
        //add string to sb
        sb.append(current);
        
        //loop k char times, length = output string length
        for(int k=0; k < length -myOrder; k++){
            //get follow string by using getFollows method
            ArrayList<String> follows = getFollowsEff(current);
            //prevent crash
            if (follows.size() == 0) {
                break;
            }
            //pick new random index
            index = myRandom.nextInt(follows.size());
            //form new string from follows
            String next = follows.get(index);
            //add string to sb
            sb.append(next);
            //move current string to right by 1
            current = current.substring(1) + next;

        }
        
        return sb.toString();
    }
}
