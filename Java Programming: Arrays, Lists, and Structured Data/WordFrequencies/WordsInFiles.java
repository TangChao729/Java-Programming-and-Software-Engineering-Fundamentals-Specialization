import edu.duke.*;
import java.util.*;
import java.io.File;
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList> hashWords;
    
    public WordsInFiles() {
        hashWords = new HashMap<String, ArrayList>();
    }
    
    public void addWordsFromFile (File f) {
        FileResource fr = new FileResource(f);
        
        for (String word : fr.words()) {     
            if (!hashWords.containsKey(word)) {
                ArrayList<String> sourceF = new ArrayList<String>();
                sourceF.add(f.getName());
                hashWords.put(word,sourceF);
            } else {
                ArrayList<String> sourceF = new ArrayList<String>();
                sourceF = hashWords.get(word);
                if (!sourceF.contains(f.getName())) {
                    sourceF.add(f.getName());
                    hashWords.put(word,sourceF);
                }
            }
        }
    }
    
    public void buildWordFileMap () {
        hashWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    
    private int maxNumber() {
        int maxNumber = 0;
        for (String wordIndex : hashWords.keySet()) {
            int arrayListSize = hashWords.get(wordIndex).size();
            if (arrayListSize > maxNumber) {
                maxNumber = arrayListSize;
            }
        }
        return maxNumber;
    }
    
    public ArrayList wordsInNumFiles (int number) {
        ArrayList<String> words = new ArrayList<String>();
        
        for (String wordIndex : hashWords.keySet()) {
            int arrayListSize = hashWords.get(wordIndex).size();
            if (arrayListSize == number) {
                words.add(wordIndex);
            }
        }
        
        return words;
    }
    
    public void printFilesIn (String word) {
        
        for (String wordIndex : hashWords.keySet()) {

            if (wordIndex.equals(word)) {
                ArrayList<String> fileNamesList = hashWords.get(wordIndex);
                
                for (String fileNames : fileNamesList) {
                    System.out.println(fileNames);
                }  
            }
        }
    }
    
    public void tester() {
        buildWordFileMap();
        System.out.println("\nThe number of words appear in five files: " + hashWords.size());
        ArrayList wordsInNumFiles = wordsInNumFiles(4);
        System.out.println("\nThe number of words appear in 4 of five files: " + wordsInNumFiles.size());
        //for (int i=0; i < wordsInNumFiles.size(); i++){
        //   System.out.println(wordsInNumFiles.get(i));
        //}
        System.out.println("\nMaximum number of words in all the files given = " +maxNumber());
        printFilesIn("tree");
        System.out.println("\n");
        //for (String s :hashWords.keySet() ){
        //    System.out.println(s + hashWords.get(s) );
        //}
    }
    
}
