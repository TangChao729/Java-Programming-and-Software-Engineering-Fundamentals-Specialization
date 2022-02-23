import java.util.*;

public class QuakeSort {
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        for(int i = 0; i < in.size(); i++) {
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        int i = 0;
        boolean checker = false;
        while(i < in.size() && checker == false) {
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);
            
            
            System.out.println("Printing Quakes after pass " + i);
            i++;
            checker = checkInSortedOrder(in);
        }
    }    
    
    public int getLargestDepth(ArrayList<QuakeEntry> quakes, int from) {
        int maxIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getDepth() > quakes.get(maxIdx).getDepth()) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
    
    public void sortByDepth(ArrayList<QuakeEntry> in) {
        //
        for (int i = 0; i < 50; i++) {
            //
            int maxIdx = getLargestDepth(in, i);
            //swap
            QuakeEntry qi = in.get(i);
            QuakeEntry qmax = in.get(maxIdx);
            in.set(i,qmax);
            in.set(maxIdx, qi);
        }
        //int quakeNumber = 50;
        //System.out.println("Print quake entry in position " + quakeNumber);
        //System.out.println(in.get(quakeNumber));
    }
    
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        // run n-1 times
        
        for (int i=0; i < quakeData.size() -1 -numSorted; i++) {
            //compare first two
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {
                QuakeEntry q1 = quakeData.get(i);
                QuakeEntry q2 = quakeData.get(i+1);
                quakeData.set(i, q2);
                quakeData.set(i+1, q1);
            }   
        }
        
    }
    
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        // run n-1 times
        System.out.println("read data for " + in.size() + " quakes");
        print(in);
        
        for (int i = 0; i < in.size()-1; i++) {
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass " + i);
            print(in);
        }
        
    }
    
    public void  sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        System.out.println("read data for " + in.size() + " quakes");
        print(in);
        int i = 0;
        boolean checker = false;
        while (i < in.size()-1 && checker == false) {
            onePassBubbleSort(in, i);
            System.out.println("Printing Quakes after pass " + i);
            //print(in);
            i++;
            checker = checkInSortedOrder(in);
        }        
    }
    
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        boolean checker = true;
        for (int i=0; i < quakes.size() -1; i++) {
            if (quakes.get(i).getMagnitude() > quakes.get(i+1).getMagnitude()) {
                return false;
            }              
        }
        return checker;
    }
    
    /* tester method to use 1 3 2 8 7 4
     * in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataWeekDec6sample2.atom";
        
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //sortByMagnitude(list);
        //sortByMagnitudeWithCheck(list);
        //sortByDepth(list);
        //onePassBubbleSort(list, 0);
        //sortByMagnitudeWithBubbleSort(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        System.out.println("EarthQuakes in sorted order:");
        print(list);
        System.out.println("=========================");
    }
    
    public void print(ArrayList<QuakeEntry> in) {
        for(QuakeEntry qe: in) {
            System.out.println(qe);
        }
    }
    
}
