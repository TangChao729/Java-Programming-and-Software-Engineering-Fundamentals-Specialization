
/**
 * Write a description of ParsingExport here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExport 
{

    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser, "Nauru");
    }
    public void countryInfo(CSVParser parser, String country) {
        for (CSVRecord record: parser ) {
            String countryList = record.get("Country");
            
            if (countryList.contains(country)) {
                String goods = record.get("Exports");
                String amount = record.get("Value (dollars)");
                System.out.println(country+": "+goods+": "+amount);
            }
            else {
                System.out.println("NOT FOUND");
            }
        }
    }
    public void tester2() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser ) {
            String goods = record.get("Exports");
            
            if (goods.contains(exportItem1) && goods.contains(exportItem2)) {
                String country = record.get("Country");
                //String amount = record.get("Value (dollars)");
                System.out.println(country);
            }
        }       
    }
    public void tester3() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersOneProducts(parser, "cocoa");
    }
    public void listExportersOneProducts(CSVParser parser, String exportItem1) {
        int count = 0;
        for (CSVRecord record: parser ) {
            String goods = record.get("Exports");
            
            if (goods.contains(exportItem1)) {
                String country = record.get("Country");
                //String amount = record.get("Value (dollars)");
                System.out.println(country);
                count = count + 1;
            }
            
        } 
        System.out.println(count);
    }
    public void tester5() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        exportInfo5(parser);
    }
    public void exportInfo5(CSVParser parser) {
        double value = 999999999999.0;
        for (CSVRecord record: parser ) {
            //String amount = record.get("Value (dollars)");
            //int amountNum = Integer.parseInt(amount);
            double amountNum = Double.parseDouble(record.get("Value (dollars)"));
            if (amountNum > value) {
                String country = record.get("Country");
                //String amount = record.get("Value (dollars)");
                System.out.println(country);
            }

            }
        }
    
}
