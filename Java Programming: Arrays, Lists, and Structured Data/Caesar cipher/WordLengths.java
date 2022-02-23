
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
public class WordLengths {
    public String stripSymbols (String inputWord) {
        
        //Strip symbols from start of words
        StringBuilder result = new StringBuilder(inputWord);
        System.out.println(result.length());
        for (int i=0; i < result.length();) {
            if (Character.isLetter(result.charAt(i))) {
                break;
            } else {
                result.setCharAt(i, ' ');
                i++;
            }
        }
        //keep symbols in middle of words
        //Strip symbols from end of words
        for (int i=0; i < result.length();) {
            if (Character.isLetter(result.charAt(result.length() - 1 - i))) {
                break;
            } else {
                result.setCharAt(result.length() - 1 - i, ' ');
                i++;
            }
        }

        return result.toString().trim();
    }
    
    public void testStrip () {
        String input1 = "abcd!";
        String input2 = "!abcd!";
        String input3 = "!ab!cd!";
        String input4 = "~!abcd!~";
        
        System.out.println(stripSymbols(input1));
        System.out.println(stripSymbols(input2));
        System.out.println(stripSymbols(input3));
        System.out.println(stripSymbols(input4));
    }
    
    public int countWordLengths (FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            //char firstLetter = Character.toLowerCase(word.charAt(0));
            //System.out.println(firstLetter);
            word = stripSymbols(word);
            
            int wordLength = word.length();
            if (wordLength > counts.length) {
                counts[counts.length-1] += 1;
            } else {
            counts[wordLength] += 1;
            }
        }
        
        for (int k=0; k < counts.length; k++) {
            if (counts[k] != 0) {
                System.out.println(counts[k] + " words of length " + k);
            }
        }
        
        
        return indexOfMax(counts);
    }
    
    public void testCountWordLengths () {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        int maxIndex = countWordLengths(fr,counts);

        System.out.println("The most frequent number of letters of words is " + maxIndex); 
    }
    
    public int indexOfMax (int[] values) {
        int max = 0;
        
        for (int k=0; k < values.length; k++) {
            if (values[k] > max) {
                max = values[k];
            }
        }
        return max;
    }
}
