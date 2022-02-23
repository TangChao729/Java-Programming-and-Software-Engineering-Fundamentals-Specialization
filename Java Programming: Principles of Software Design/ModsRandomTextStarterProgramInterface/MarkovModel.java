
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MarkovModel extends AbstractMarkovModel{
    private int myOrder;
    
    public MarkovModel(int order) {
        myOrder = order;
        keyLength = order;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int length){
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length() - myOrder);
        String current = myText.substring(index, index + myOrder);
        
        sb.append(current);
        
        for(int k=0; k < length -myOrder; k++){
            ArrayList<String> follows = getFollows(current);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            
            String next = follows.get(index);
            sb.append(next);
            current = current.substring(1) + next;

        }
        
        return sb.toString();
    }
}
