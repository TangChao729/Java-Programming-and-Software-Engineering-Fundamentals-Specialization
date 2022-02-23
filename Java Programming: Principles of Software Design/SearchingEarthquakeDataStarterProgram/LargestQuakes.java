
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        // TO DO
        for (int j=0; j < howMany; j++) {
            int maxIndex = indexOfLargest(copy);
            ret.add(copy.get(maxIndex));
            copy.remove(maxIndex);
        }
        return ret;
    }
    
    public int indexOfLargest (ArrayList<QuakeEntry> data) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        int indexOfLargest = 0;
        double currLargestMag = 0;
        for (int k=0; k < data.size(); k++) {
            if (data.get(k).getMagnitude() > currLargestMag) {
                currLargestMag = data.get(k).getMagnitude();
                indexOfLargest = k;
            }
        }
        return indexOfLargest;
    }
    

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        ArrayList<QuakeEntry> listLargest = getLargest(list, 50);
        for (QuakeEntry qe: listLargest) {
            System.out.println(qe);
        }       
        System.out.println("Found " + listLargest.size() + " quakes that match that criteria");
    }
    
}
