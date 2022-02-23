import edu.duke.*;
import java.util.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap (int start, String dna) {
        codonMap.clear();
        
        for (int i = start; i < dna.length()-3; i+=3) {
            String codon = dna.substring(i,i+3);
            if (codonMap.containsKey(codon)) {
                codonMap.put(codon,codonMap.get(codon)+1);
            } else {
                codonMap.put(codon,1);
            }
        }
    }
    
    public String getMostCommonCodon () {
        int count = 0;
        String codon = null;
        for (String i: codonMap.keySet()) {
            if (codonMap.get(i) > count) {
                count = codonMap.get(i);
                codon = i;
            }
        }
        return codon;
    }
    
    public void printCodonCounts (int start, int end) {
        for (String i: codonMap.keySet()) {
            if (codonMap.get(i) >= start && codonMap.get(i) <= end) {
                System.out.println(i + "\t" + codonMap.get(i));
            }
        }
    }
    
    public void tester () {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i,dna);
            String mostCommonCodon = getMostCommonCodon ();
            System.out.println("Reading frame starting with " + i 
                            + " results in " + codonMap.size() + " unique codons");
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
            this.printCodonCounts(1,5);
        }
    }
}
