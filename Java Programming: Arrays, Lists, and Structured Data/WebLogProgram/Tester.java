
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
        System.out.println("\n\n");
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        System.out.println("\n\n");
    }
    
    public void testLogUniqueAddress() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        la.printAll();
        System.out.println("This log file contains " + la.countUniqueIPs() + " unique IP addresses.");
        System.out.println("\n\n");
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int num = 400;
        System.out.println("These log files contains status code higher than " + num);
        la.printAllHigherThanNum(num);
        System.out.println("\n\n");
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String someday = "Sep 27";
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay(someday);
        System.out.println("On day: " + someday + ", these IPs visited: \n" + uniqueIPs);
        System.out.println("Size: " + uniqueIPs.size());
        System.out.println("\n\n");    
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int count = la.countUniqueIPsInRange(low, high);
        System.out.println(count + " log files contains status code from " + low + " to " + high);
        System.out.println("\n");
        
        low = 400;
        high = 499;
        count = la.countUniqueIPsInRange(low, high);
        System.out.println(count + " log files contains status code from " + low + " to " + high);
        System.out.println("\n");
    }
    
    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitsByIP () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int mostNumber = la.mostNumberVisitsByIP(counts);
        System.out.println("The most number is: " + mostNumber);
    }
    
    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        ArrayList<String> IPs = la.iPsMostVisits(counts);
        System.out.println("These IPs have most visits: \n" + IPs);
    }
    
    public void testIPsForDays () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2-short_log");
        HashMap<String, ArrayList<String>> IPsForDays = la.iPsForDays();
        System.out.println("HashMap IPs to Days: \n" + IPsForDays);
    }
    
    public void testDayWithMostIPVisits () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> IPsForDays = la.iPsForDays();
        String dayWithMostIPs = la.dayWithMostIPVisits(IPsForDays);
        System.out.println("Day with most IP visits: \n" + dayWithMostIPs);
    }
    
    public void testIPsWithMostVisitsOnDay () {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, ArrayList<String>> IPsForDays = la.iPsForDays();
        String someday = "Sep 29";
        
        ArrayList<String> result = new ArrayList<String>();
        result = la.iPsWithMostVisitsOnDay(IPsForDays,someday);
        
        System.out.println("The day: " + someday + " the IP visits the most: \n" + result);
    }

}
