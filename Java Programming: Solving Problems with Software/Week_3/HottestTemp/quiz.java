
/**
 * Write a description of quiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class quiz {
    public CSVRecord coldestHourInFile (CSVParser parser) {
        //start with smallestSoFar as nothing
    CSVRecord smallestSoFar = null;
    //For each row (currentRow) in the CSV File
    for (CSVRecord currentRow : parser) {
        // use method to compare two records
        smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
    }
    //The largestSoFar is the answer
    return smallestSoFar;
    }
    
    public void testColdestInDay () {
        FileResource fr = new FileResource();
        //System.out.println(fr.path);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("TimeEDT"));
    }
    
    public CSVRecord coldestInManyDays() {
        CSVRecord smallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // use method to get largest in file.
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            //
            if (Double.parseDouble(currentRow.get("TemperatureF")) < -1000) {
                continue;
            }
            // use method to compare two records
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar);
        }
        //The largestSoFar is the answer
        return smallestSoFar;
    } 
    
    public CSVRecord getSmallestOfTwo (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentTemp < smallestTemp) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public CSVRecord getSmallestHum (CSVRecord currentRow, CSVRecord smallestSoFar) {
        //If largestSoFar is nothing
        if (smallestSoFar == null) {
            smallestSoFar = currentRow;
        }
        //Otherwise
        else {
            double currentHum = Double.parseDouble(currentRow.get("Humidity"));
            double smallestHum = Double.parseDouble(smallestSoFar.get("Humidity"));
            //Check if currentRow’s temperature > largestSoFar’s
            if (currentHum < smallestHum) {
                //If so update largestSoFar to currentRow
                smallestSoFar = currentRow;
            }
        }
        return smallestSoFar;
    }
    
    public void testColdestInManyDays () {
        CSVRecord smallest = coldestInManyDays();
        System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
                   " at " + smallest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature () {
        CSVRecord smallestSoFar = null;
        String fileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f:dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo (currentRow, smallestSoFar);
            fileName = f.getName();
        }
        return fileName;
    }
    
    public void testFileWithColdestTemperature () {
        String fileName = fileWithColdestTemperature();
        String fullPath = "nc_weather/2015/" + fileName;
        FileResource fr = new FileResource(fullPath);
        CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was in file " + fileName);
        System.out.println("Coldest temperature on that day was " + smallest.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        
        for (CSVRecord currentRow : fr.getCSVParser()) {
            // use method to compare two records
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
    } 
    }
    
    public CSVRecord lowestHumidityInManyFiles () {        
        CSVRecord lowestSoFar = null;
        CSVRecord lowestTemp = null;
        DirectoryResource dr = new DirectoryResource();
        //Select all files
        for (File f:dr.selectedFiles()) {
            //for each file
            FileResource fr = new FileResource(f);
            //get file resource
            CSVParser currentParser = fr.getCSVParser();
            //parse data to parser
            try {
                for (CSVRecord currentRow : currentParser) {
                    // use method to compare two records
                    if (Double.parseDouble(currentRow.get("Humidity")) < -1000) {
                        continue;
                    }
                    if (Double.parseDouble(currentRow.get("Humidity")) == -1000) {
                        continue;
                    }
                    lowestTemp = getSmallestHum(currentRow, lowestTemp);
                }
            } catch (NumberFormatException e) {
                continue;
            }
            lowestSoFar = getSmallestHum(lowestTemp, lowestSoFar);
        }
        //The largestSoFar is the answer
        return lowestSoFar;
    }
    
    public void testLowestHumidityInManyFiles() {
        //Lowest Humidity was 24 at 2014-01-20 19:51:00
        //String fileName = fileWithColdestTemperature();
        //String fullPath = "nc_weather/2015/" + fileName;
        //FileResource fr = new FileResource(fullPath);
        CSVRecord lowestSoFar = lowestHumidityInManyFiles();
        
        System.out.println("Lowest Humidity was " + lowestSoFar.get("Humidity")+ " at " + lowestSoFar.get("DateUTC"));
    }
    
    //5. Write the method averageTemperatureInFile that has one parameter, 
    //a CSVParser named parser. This method returns a double that represents 
    //the average temperature in the file. You should also write a void method 
    //named testAverageTemperatureInFile() to test this method. When this method 
    //runs and selects the file for January 20, 2014, the method should print out
    //Average temperature in file is 44.93333333333334
    public double averageTemperatureInFile(CSVParser parser) {
        int counter = 0;
        double total = 0.0;
        double average = 0.0;
        for (CSVRecord currentRow : parser) {
            counter = counter + 1;
            total = total + Double.parseDouble(currentRow.get("TemperatureF"));
        }
        average = total / counter;
        return average;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + averageTemp);
    }
    
    
    //6. Write the method averageTemperatureWithHighHumidityInFile that has two parameters, 
    //a CSVParser named parser and an integer named value. This method returns a double that 
    //represents the average temperature of only those temperatures when the humidity was 
    //greater than or equal to value. You should also write a void method named 
    //testAverageTemperatureWithHighHumidityInFile() to test this method. When this method 
    //runs checking for humidity greater than or equal to 80 and selects the file for 
    //January 20, 2014, the method should print out
    //No temperatures with that humidity
    
    //If you run the method checking for humidity greater than or equal to 80 and select the 
    //file March 20, 2014, a wetter day, the method should print out
    //Average Temp when high Humidity is 41.78666666666667
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int counter = 0;
        double total = 0.0;
        double average = 0.0;
        for (CSVRecord currentRow : parser) {
            if (Double.parseDouble(currentRow.get("Humidity")) >= value) {
                counter = counter + 1;
                total = total + Double.parseDouble(currentRow.get("TemperatureF"));
            }
        }
        
        if (total != 0) {
            average = total / counter;
            return average;
        }
        else {
            return -2000;
        }
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        double averageTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
            
        if (averageTemp != -2000) {
            System.out.println("Average Temp when high Humidity is " + averageTemp);
        }
        else {
            System.out.println("No temperatures with that humidity");
        }
    }
}
