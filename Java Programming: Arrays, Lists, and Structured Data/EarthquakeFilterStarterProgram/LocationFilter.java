
/**
 * Write a description of LocationFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LocationFilter implements Filter{
    private Location from;
    private double minDistance;
    
    public LocationFilter(Location loc, int distance) { 
        from = loc;
        minDistance = distance;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(from) <= minDistance; 
    }  
    
    public String getName() {
        return "DistanceFilter";
    }
}
