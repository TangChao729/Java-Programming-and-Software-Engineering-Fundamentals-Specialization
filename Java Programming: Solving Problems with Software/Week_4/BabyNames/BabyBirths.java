/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
import java.util.Scanner;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        
        int totalNames = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
            
            totalNames += 1;
            if (rec.get(1).equals("M")) {
                totalBoysNames += 1;
             }
             else {
                 totalGirlsNames += 1;
             }
            
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total names = " + totalNames);
        
        System.out.println("female girls = " + totalGirls);
        System.out.println("female names = " + totalGirlsNames);
        
        System.out.println("male boys = " + totalBoys);
        System.out.println("male boys names = " + totalBoysNames);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob1905.csv");
        totalBirths(fr);
    }
    
    //Write the method named getRank that has three parameters: an integer named year, 
    //a string named name, and a string named gender (F for female and M for male). 
    //This method returns the rank of the name in the file for the given gender,  
    //where rank 1 is the name with the largest number of births. If the name is not 
    //in the file, then -1 is returned.  For example, in the file "yob2012short.csv", 
    //given the name Mason, the year 2012 and the gender ‘M’, the number returned is 2, 
    //as Mason is the boys name with the second highest number of births. Given the name 
    //Mason, the year 2012 and the gender ‘F’, the number returned is -1 as Mason does not 
    //appear with an F in that file.
    
    public int getRank (int year, String name, String gender) {
        int rank = 0;
        int found = 0;
        //data/2015/weather-2015-01-01.csv
        String filePath = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (found == 0) {
                if (rec.get(1).equals(gender)) {
                    rank += 1;
                    if (rec.get(0).equals(name)) {
                        found += 1;
                        return rank;
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }

        return -1;

    }
    
    public void testGetRank() {
        System.out.println("Enter year:");
        Scanner yearInput = new Scanner (System.in);
        int year = yearInput.nextInt();
        
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        int rank = getRank(year, name, gender);
        if (rank != -1) {
            System.out.println("In the year " + year + 
                                ", the name " + name +
                                " in gender " + gender +
                                " ranked: " + rank);
            System.out.println("===============================");
        } else {
            System.out.println("Name not found");
            System.out.println("===============================");
        }
    }
    
    public int getNum (int year, String name, String gender) {
        int amount = 0;
        int found = 0;
        //data/2015/weather-2015-01-01.csv
        String filePath = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        
        while (found == 0) {
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {                    
                    if (rec.get(0).equals(name)) {
                        found += 1;
                        amount = Integer.parseInt(rec.get(2));
                        return amount;
                    }  
                }
            }
        }
        return -1;
    }
    
    public void testGetNum() {
        System.out.println("Enter year:");
        Scanner yearInput = new Scanner (System.in);
        int year = yearInput.nextInt();
        
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        int amount = getNum(year, name, gender);
        if (amount != -1) {
            System.out.println("In the year " + year + 
                                ", the name " + name +
                                " in gender " + gender +
                                " birth amount is " + amount);
            System.out.println("===============================");
        }
    }    
    //Write the method named getName that has three parameters: an integer named year, 
    //an integer named rank, and a string named gender (F for female and M for male). 
    //This method returns the name of the person in the file at this rank, for the given 
    //gender, where rank 1 is the name with the largest number of births. If the rank does 
    //not exist in the file, then “NO NAME”  is returned.
    
    public String getName (int year, int rank, String gender) {
        String name = null;
        int found = 0;
        int temp = 0;
        //data/2015/weather-2015-01-01.csv
        String filePath = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        while (found == 0) {
            for (CSVRecord rec : fr.getCSVParser(false)) {
                if (rec.get(1).equals(gender)) {
                    temp++;
                    if (temp == rank) {
                        found = 1;
                        return rec.get(0);
                    }
                }
            }
        }
        return "No Name";
    }
    
    public void testGetName() {
        System.out.println("Enter year:");
        Scanner yearInput = new Scanner (System.in);
        int year = yearInput.nextInt();
        
        System.out.println("Enter Rank:");
        Scanner rankInput = new Scanner (System.in);
        int rank = rankInput.nextInt();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        String name = getName(year, rank, gender);
        if (name != "No Name") {
            System.out.println("In the year " + year + 
                                " No." + rank +
                                " in gender " + gender +
                                " name is " + name);
            System.out.println("===============================");
        }
    }
    
    public int whatIsNameInYear(String name, int oldYear, int newYear, String gender) {
        int rank = getRank(oldYear, name, gender);
        String newName = getName(newYear, rank, gender);
        // Isabella born in 2012 would be Sophia if she was born in 2014.
        System.out.println(name+" born in "+oldYear+" would be "+
                            newName+" if he/she was born in " +newYear);
        return 0;
    }
    
    public void testWhatIsNameInYear() {
        System.out.println("Enter old year:");
        Scanner oldYearInput = new Scanner (System.in);
        int oldYear = oldYearInput.nextInt();
        
        System.out.println("Enter new year:");
        Scanner newYearInput = new Scanner (System.in);
        int newYear = newYearInput.nextInt();
        
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        int dummy = whatIsNameInYear(name, oldYear, newYear, gender);
    }
    
    /*Write the method yearOfHighestRank that has two parameters: a string name,  
     and a string named gender (F for female and M for male). This method selects 
     *a range of files to process and returns an integer, the year with the highest 
     *rank for the name and gender. If the name and gender are not in any of the selected 
     *files, it should return -1. For example, calling yearOfHighestRank with name Mason 
     *and gender ‘M’ and selecting the three test files above results in returning the year 
     *2012. That is because Mason was ranked the  2nd most popular name in 2012, ranked 4th 
     *in 2013 and ranked 3rd in 2014. His highest ranking was in 2012.*/
     
    public int yearOfHighestRank(String name, String gender) {
        int tempHigh = 0;
        int currRank = 0;
        int year = 0;
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            if (tempHigh >= 0) {
                FileResource fr = new FileResource(f);
                String fileName = f.getName();
                int tempYear = Integer.parseInt(fileName.substring(3,7));
                // use getRank
                currRank = getRank(tempYear, name, gender);
                if (currRank == -1) {
                    continue;
                }
                
                if (tempHigh == 0) {
                    tempHigh = currRank;
                    year = tempYear;
                }
                
                if (currRank < tempHigh) {
                    tempHigh = currRank;
                    year = tempYear;
                }
            } else {
                return -1;
            }
        }
        return year;
    }
    
    public void testYearOfHighestRank() {
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        int year = yearOfHighestRank(name, gender);
        
        if (year != -1) {
            System.out.println("The highest ranking for " + name + 
                                " as gender " + gender +
                                " is in year " + year + ".");
            System.out.println("===============================");
        } else {
            System.out.println("Name not found.");
            System.out.println("===============================");
        }
    }
    
    /*
     * Write the method getAverageRank that has two parameters: a string name, 
     * and a string named gender (F for female and M for male). This method selects 
     * a range of files to process and returns a double representing the average rank 
     * of the name and gender over the selected files. It should return -1.0 if the name 
     * is not ranked in any of the selected files. For example calling getAverageRank with 
     * name Mason and gender ‘M’ and selecting the three test files above results in 
     * returning 3.0, as he is rank 2 in the year 2012, rank 4 in 2013 and rank 3 in 2014.  
     * As another example, calling   getAverageRank with name Jacob and gender ‘M’ 
     * and selecting the three test files above results in returning 2.66.
     */
    public double getAverageRank(String name, String gender) {
        double averageRank = 0.0;
        double currRank = 0.0;
        double tempTotal = 0.0;
        int tempAmount = 0;
        
        DirectoryResource dr = new DirectoryResource();
        // iterate over files
        for (File f : dr.selectedFiles()) {
            if (currRank != -1) {
                FileResource fr = new FileResource(f);
                String fileName = f.getName();
                int tempYear = Integer.parseInt(fileName.substring(3,7));
                
                // use getRank
                currRank = getRank(tempYear, name, gender);
                tempAmount++;

                tempTotal += currRank;
                averageRank = tempTotal / tempAmount;

            } else {
                return -1.0;
            }
        }
        return averageRank;
    }
    
    public void testGetAverageRank() {
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        double averageRank = getAverageRank(name, gender);
        
        if (averageRank != -1) {
            System.out.println("The average ranking for " + name + 
                                " as gender " + gender +
                                " in selected years is " + averageRank);
            System.out.println("===============================");
        } else {
            System.out.println("Name not found.");
            System.out.println("===============================");
        }
    }
    
    /* Write the method getTotalBirthsRankedHigher that has three parameters: an integer named year, 
     * a string named name, and a string named gender (F for female and M for male). This method 
     * returns an integer, the total number of births of those names with the same gender and same 
     * year who are ranked higher than name. For example, if getTotalBirthsRankedHigher accesses 
     * the "yob2012short.csv" file with name set to “Ethan”, gender set to “M”, and year set to 2012, 
     * then this method should return 15, since Jacob has 8 births and Mason has 7 births, and those 
     * are the only two ranked higher than Ethan. 
     */
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalNum = 0;
        
        String filePath = "data/yob" + year + ".csv";
        FileResource fr = new FileResource(filePath);
        // iterate over files

        int tempRank = getRank(year, name, gender);
        int nextRank = tempRank-1;
        
        while (nextRank != 0) {
            
            String newName = getName(year, nextRank, gender);
            int tempAmount = getNum(year, newName, gender);
            
            if (tempAmount < 0) {
                nextRank--;
            } else {
                totalNum += tempAmount;
                nextRank--;
            }
        }
        return totalNum;
    }
    
    public void testGetTotalBirthsRankedHigher () {
        System.out.println("Enter year:");
        Scanner yearInput = new Scanner (System.in);
        int year = yearInput.nextInt();
        
        System.out.println("Enter name:");
        Scanner nameInput = new Scanner (System.in);
        String name = nameInput.nextLine();
        
        System.out.println("F or M:");
        Scanner genderInput = new Scanner (System.in);
        String gender = genderInput.nextLine();
        
        int totalNum = getTotalBirthsRankedHigher(year, name, gender);

        System.out.println("Total number of name borned in " + year + 
                            " rank higher than " + name +
                            " in gender " + gender +
                            " is " + totalNum);
        System.out.println("===============================");
 
    }
}
