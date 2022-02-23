
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
    public String lastWord(QuakeEntry in) {
        String[] words = in.getInfo().split(" ");
        String lastWord = words[words.length-1];
        return lastWord;
    }
    
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int result = lastWord(q1).compareTo(lastWord(q2));
        if (result != 0) {
            return result;
        } else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}
