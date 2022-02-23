
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter {
    private ArrayList<String> myDirectors;

    public DirectorsFilter(String directors) {
        String[] directorsArrayHelper = directors.split(",");
        List<String> directorsListHelper = Arrays.asList(directorsArrayHelper);
        myDirectors = new ArrayList<String>(directorsListHelper);
        
    }

    @Override
    public boolean satisfies(String id) {
        for (int i=0; i<myDirectors.size(); i++) {
            String currDirector = myDirectors.get(i);
            if (MovieDatabase.getDirector(id).indexOf(currDirector) != -1) {
                return true;
            } 
        }
        return false;
    }

}
