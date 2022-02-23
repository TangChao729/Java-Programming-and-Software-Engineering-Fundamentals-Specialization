
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 1; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int seed = 531;
        
        //MarkovWord mw = new MarkovWord(6); 
        //runModel(mw, st, 100, seed); 
        
        EfficientMarkovWord mweff = new EfficientMarkovWord(5);
        runModel(mweff, st, 200, seed);
    } 
    
    public void runMarkovEff() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int seed = 371;
        
        
        EfficientMarkovWord mweff = new EfficientMarkovWord(3);
        runModel(mweff, st, 100, seed); 
        
    } 
    
    public void compareMethods() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int seed = 371;
        
        double starttime = System.nanoTime();
        
        MarkovWord mw = new MarkovWord(2); 
        runModel(mw, st, 100, seed); 
        
        double endtime = System.nanoTime();
        double timeused = (endtime - starttime)/1000000000;
        System.out.println("time used: " + timeused);
        
        
        starttime = System.nanoTime();
        EfficientMarkovWord mweff = new EfficientMarkovWord(2);
        runModel(mweff, st, 100, seed); 
        
        
        endtime = System.nanoTime();
        timeused = (endtime - starttime)/1000000000;
        System.out.println("time used: " + timeused);
    } 
    
    public void testHashMap(){
        //FileResource fr = new FileResource();
	//String st = fr.asString();
	//st = st.replace('\n', ' ');
	String st = "this is a test yes this is really a test";
        int size = 50;
        int seed = 42;
        EfficientMarkovWord mod = new EfficientMarkovWord(2);
        runModel(mod, st, size, seed);
        //System.out.println("printing");
        //mod.printHashMapInfo();
    }

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

}
