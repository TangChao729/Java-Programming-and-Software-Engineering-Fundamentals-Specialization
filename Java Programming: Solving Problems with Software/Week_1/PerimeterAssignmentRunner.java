import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int totalPts = 0;
        for (Point dummy : s.getPoints()) {
            totalPts += 1;
        }
        return totalPts;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avgLen = getPerimeter(s)/getNumPoints(s);
        return avgLen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestside = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update largestside by currDist
            if (currDist >= largestside) {
                largestside = currDist;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestside;
    }

    public double getLargestX(Shape s) {
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // Put code here
        double largestX = prevPt.getX();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currX = prevPt.getX();
            // Update largestside by currDist
            if (currX >= largestX) {
                largestX = currX;
            }
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        //largest perimeter varible
        double largestPeriMF = 0.0;
        // Select multiple documents
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length >= largestPeriMF) {
                largestPeriMF = length;
            }
        }
        return largestPeriMF;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    
        //largest perimeter varible
        double largestPeriMF = 0.0;
        // Select multiple documents
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if (length >= largestPeriMF) {
                largestPeriMF = length;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }
    
    public void testNumPoints () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int points = getNumPoints(s);
        System.out.println("points = " + points);
    }
 
    public void testAvgLen () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double avgLen = getAverageLength(s);
        System.out.println("Average Length = " + avgLen);
    }   

    public void testLargestSide () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largestLen = getLargestSide(s);
        System.out.println("Largest Length = " + largestLen);
    }     

    public void testLargestX () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double largestX = getLargestX(s);
        System.out.println("Largest X-Length = " + largestX);
    }      
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPeriMF = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Perimeter from multiple files = " + largestPeriMF);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPeriFile = getFileWithLargestPerimeter();
        System.out.println("File with Largest Perimeter from multiple files = " + largestPeriFile);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testNumPoints();
        //pr.testAvgLen();
        //pr.testLargestSide();
        //pr.testLargestX();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
