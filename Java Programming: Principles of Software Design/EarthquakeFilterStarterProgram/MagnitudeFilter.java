
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MagnitudeFilter implements Filter{
    private double minMagnitude;
    private double maxMagnitude;
    
    public MagnitudeFilter(double min, double max) { 
        minMagnitude = min;
        maxMagnitude = max;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= minMagnitude && qe.getMagnitude() <= maxMagnitude; 
    }   
    
    public String getName(){
        return "MagnitudeFilter";
    }
}
