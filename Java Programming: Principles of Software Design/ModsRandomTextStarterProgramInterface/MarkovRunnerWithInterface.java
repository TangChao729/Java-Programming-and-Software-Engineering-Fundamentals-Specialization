
/**
 * Write a description of MarkovRunnerWithInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class MarkovRunnerWithInterface {
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
        
    private void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for (int k=0; k<3; k++) {
            String st = markov.getRandomText(size);
            printOut(st);
        }
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        MarkovModel mm = new MarkovModel(7);
        runModel(mm, st, 300, 953);
        /*
        MarkovOne mo = new MarkovOne();
        runModel(mo, st, 800, 0);
        
        MarkovModel mm = new MarkovModel(3);
        runModel(mm, st, 800, 0);
        
        MarkovFour mf = new MarkovFour();
        runModel(mf, st, 800, 0);
        */
    }
    
    public void testHashMap() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, 50, 615);
    }
    
    public void compareMethods() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        
        double starttime = System.nanoTime();
        
        MarkovModel mm = new MarkovModel(2);
        runModel(mm, st, 1000, 42);
        
        double endtime = System.nanoTime();
        double timeused = (endtime - starttime)/1000000000;
        System.out.println("time used: " + timeused);
        
        starttime = System.nanoTime();
        EfficientMarkovModel emm = new EfficientMarkovModel(2);
        runModel(emm, st, 1000, 42);
        endtime = System.nanoTime();
        timeused = (endtime - starttime)/1000000000;
        System.out.println("time used: " + timeused);
    }
}
