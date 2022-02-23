
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String typeFilter;
    private String phraseFilter;
    
    public PhraseFilter(String type, String phrase) { 
        typeFilter = type;
        phraseFilter = phrase;
    } 
    
    public boolean satisfies (QuakeEntry qe) {
        
        if (typeFilter == "start") {
            if (!qe.getInfo().startsWith(phraseFilter)) {
                return false;
            }
        }
        if (typeFilter == "end") {
            if (!qe.getInfo().endsWith(phraseFilter)) {
                return false;
            }
        }
        if (typeFilter == "any") {
            if (qe.getInfo().indexOf(phraseFilter) == -1) {
                return false;
            }
        }
        
        return true;
    }
    
    public String getName(){
        return "PhraseFilter";
    }
}
