import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        //Filter filterMinMag = new MinMagFilter(4.0); 
        //Filter filterMag = new MagnitudeFilter(4.0, 5.0);
        //ArrayList<QuakeEntry> m7 = filter(list, filterMag);
        
        //Filter filterDepth = new DepthFilter(-35000, -12000);
        //m7 = filter(m7, filterDepth);  
        
        Filter filterPhrase = new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> m7 = filter(list, filterPhrase);
        
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
    }
    
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 2.0);
        maf.addFilter(f1);
        Filter f2 = new DepthFilter(-100000, -10000);
        maf.addFilter(f2);
        Filter f3 = new PhraseFilter("any","a");
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> m8 = filter(list, maf);
        
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }         
    
    }

    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 3.0);
        maf.addFilter(f1);
        
        Location Tulsa= new Location(36.1314, -95.9372);
        Filter f2 = new LocationFilter(Tulsa, 10000000);
        maf.addFilter(f2);
        
        Filter f3 = new PhraseFilter("any","Ca");
        maf.addFilter(f3);
        
        ArrayList<QuakeEntry> m8 = filter(list, maf);
        
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        }         
        
        System.out.println("Filter used are: " + maf.getName());
    }    
    
    public void quiz() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        MatchAllFilter maf = new MatchAllFilter();
        
        Location Tokyo = new Location (35.42, 139.43);
        Location Tulsa = new Location (36.1314, -95.9372);
        Location Denver = new Location (39.7392, -104.9903);
        Location Billund = new Location (55.7308, 9.1153);
        
        Filter f1 = new LocationFilter(Billund, 3000000);
        maf.addFilter(f1);
        
        Filter f2 = new PhraseFilter("any","e");
        maf.addFilter(f2);
        
        Filter f3 = new MagnitudeFilter(0.0, 5.0);
        maf.addFilter(f3);
        
        Filter f4 = new DepthFilter(-12000, -10000);
        //maf.addFilter(f4);
        
        ArrayList<QuakeEntry> q1 = filter(list, maf);
        
        for (QuakeEntry qe: q1) { 
            System.out.println(qe);
        }         
        System.out.println("Amount of earthquake found: " + q1.size());        
        System.out.println("Filter used are: " + maf.getName());
    }  
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
