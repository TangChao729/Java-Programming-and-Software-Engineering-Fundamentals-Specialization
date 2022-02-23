
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
     
     public int countUniqueIPs () {
         ArrayList<String> uniqAddrs = new ArrayList<String>();
         for (LogEntry le: records) {
             if (!uniqAddrs.contains(le.getIpAddress())) {
                 uniqAddrs.add(le.getIpAddress());
                }
         }
         return uniqAddrs.size();
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public void printAllHigherThanNum (int num) {
         for (LogEntry le: records) {
             if (le.getStatusCode() >= num) {
                 System.out.println(le);
                }
         }
     }
        
     public ArrayList<String> uniqueIPVisitsOnDay(String someday){
         /* This method returns a new ArrayList with a number of items as long as the date included as a para
          *  meter matches the date in the logEntry, and it does not include the logEntry if the ip has already
          *  been included that day (if the ip user already visited the webpage that date). For mor info, please
          *  read the readme.txt file.
          * It may result easier to call somehow the countUniqueIPs method instead of copypasting code
          * but as we are not interested in the int variable returned on that method, we just started 
          * the arrayList again and created a new one for matching dates and unique IPs.
          * I am also sure that we could have used a HashMap, but as we see here, it is not necessary at all.
          */
         ArrayList<String> uniqueIPs = new ArrayList<String>(); 
         ArrayList<String> uniqueIPsDates = new ArrayList<String>(); 
         for (LogEntry le : records){
             Date d = le.getAccessTime();
             String str = d.toString();
             String subStr = str.substring(4,10); 
             String ipAddr = le.getIpAddress();
             if(subStr.equals(someday) && !uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
                 uniqueIPsDates.add(subStr);
                 //it works
             }
         }
         return uniqueIPs;
     }
     
     public ArrayList<String> iPVisitsOnDay(String someday){
         // same as above but allow duplicate
         ArrayList<String> IPs = new ArrayList<String>(); 
         ArrayList<String> IPsDates = new ArrayList<String>(); 
         for (LogEntry le : records){
             Date d = le.getAccessTime();
             String str = d.toString();
             String subStr = str.substring(4,10); 
             String ipAddr = le.getIpAddress();
             if(subStr.equals(someday)){
                 IPs.add(ipAddr);
                 IPsDates.add(subStr);
                 //it works
             }
         }
         return IPs;
     }
     
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>(); 
         ArrayList<Integer> uniqueIPsStatus = new ArrayList<Integer>();
         for (LogEntry le : records){
            int status = le.getStatusCode();
            String ipAddr = le.getIpAddress();
            if (status >= low && status <= high && !uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
                uniqueIPsStatus.add(status);
             }
         }
         return uniqueIPsStatus.size();
     }
     
     public HashMap<String, Integer> countVisitsPerIP () {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             
             if (! counts.containsKey(ip)) {
                 counts.put(ip,1);
             } else {
                 counts.put(ip, counts.get(ip) + 1);
             }
                
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts) {
         int mostNumber = 0;
         for (String s : counts.keySet()) {
             // for each key, if its value is higher than current count, update it.
             if (counts.get(s) > mostNumber) {
                 mostNumber = counts.get(s);
             } 
         }
         return mostNumber;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts) {
         ArrayList<String> iPs = new ArrayList<String>();
         int mostNumber = mostNumberVisitsByIP(counts);
         
         for (String s: counts.keySet()) {
             if (counts.get(s) == mostNumber) {
                 iPs.add(s);
             }
         }
         return iPs;
     }    
     
     public HashMap<String, ArrayList<String>> iPsForDays () {
         HashMap<String, ArrayList<String>> iPsForDay = new HashMap<String, ArrayList<String>>();
         ArrayList<String> uniqueIPsOnDay = new ArrayList<String>();
         
         for (LogEntry le : records){
            Date d = le.getAccessTime();
            String str = d.toString();
            String subStr = str.substring(4,10); 
            
            // nest HashMap keys list
            if (!iPsForDay.containsKey(subStr)) {
                uniqueIPsOnDay = iPVisitsOnDay(subStr);
                iPsForDay.put(subStr,uniqueIPsOnDay);
            }
         }
         return iPsForDay;
     }
     
     public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> iPsForDays) {
         String dayWithMostIP = null;
         int count = 0;
         
         for (String s : iPsForDays.keySet()) {
             // for each key, if its value is higher than current count, update it.
             if (iPsForDays.get(s).size() > count) {
                 count = iPsForDays.get(s).size();
                 dayWithMostIP = s;
             } 
         }
         
         return dayWithMostIP;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> iPsForDays, String someday) {
         //ArrayList<String> iPsWithMostVisitsOnDay = new ArrayList<String>();
         ArrayList<String> totalIPsVisitsOnDay = iPsForDays.get(someday);

         HashMap<String, Integer> map = new HashMap<String, Integer>();
         for (String ip : totalIPsVisitsOnDay) {
             if (! map.containsKey(ip)) {
                 map.put(ip,1);
             } else {
                 map.put(ip, map.get(ip) + 1);
             }                
         }
         
         //iPsWithMostVisitsOnDay = iPsMostVisits(counts);
         ArrayList<String> iPs = new ArrayList<String>();
         
         int mostNumber = mostNumberVisitsByIP(map);
         for (String s: map.keySet()) {
             if (map.get(s) == mostNumber) {
                 iPs.add(s);
             }
         }
         return iPs;
         //return iPsWithMostVisitsOnDay;
     }
}
