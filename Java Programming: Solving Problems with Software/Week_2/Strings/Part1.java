
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findGeneSimple(String dna) {
        String result = "";
        
        int headIndex = dna.indexOf("ATG");
        if (headIndex == -1) {
            return "No head string";
        }
        int tailIndex = dna.indexOf("TAA",headIndex+3);
        if (tailIndex == -1) {
            return "No tail string";
        }
        result = dna.substring(headIndex, tailIndex+3);
        if (result.length()%3!=0) {
            return "String digits are not multiple of 3";
        }
        return "DNA string is " + result;
    }
    
    public void testGeneSimple() {
        String dna = "";
        dna = "AATGCGTAATATGGT"; //8 digits, wrong case
        String result = findGeneSimple(dna);
        System.out.println("Original string = " + dna);
        System.out.println("Gene string = " + result);
        
        dna = "AATTGCGCGGTAATATTGGT"; //no ATG, wrong case
        result = findGeneSimple(dna);
        System.out.println("Original string = " + dna);
        System.out.println("Gene string = " + result);
        
        dna = "AATGCGTCGAATATGGT";  //no TAA, wrong case
        result = findGeneSimple(dna);
        System.out.println("Original string = " + dna);
        System.out.println("Gene string = " + result);
        
        dna = "AATGCGTGTATAATATGGT"; //correct case
        result = findGeneSimple(dna);
        System.out.println("Original string = " + dna);
        System.out.println("Gene string = " + result);
    }
}
