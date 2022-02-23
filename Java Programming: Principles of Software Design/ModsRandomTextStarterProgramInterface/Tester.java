
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class Tester {
    public void runMarkovTestGetFollows() {
        String stest = "this is a test yes this is a test.";
        MarkovOne markov = new MarkovOne();
        markov.setTraining(stest);
        System.out.println(markov.getFollows("t"));
        System.out.println(markov.getFollows("e"));
        System.out.println(markov.getFollows("es"));
        System.out.println(markov.getFollows("."));
        System.out.println(markov.getFollows("t."));
    }  
    
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setTraining(st);
        
        System.out.println(markov.getFollows("he").size());        
    }
}
