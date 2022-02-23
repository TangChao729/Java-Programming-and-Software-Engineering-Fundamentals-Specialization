
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FourthRatings {
    
    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    
    public FourthRatings(String ratingfile) {
        RaterDatabase.addRatings("data/" + ratingfile);
    }
    
    public int getRaterSize(){
        return RaterDatabase.size();
    }
    
    
    private double getAverageByID(String id, int minimalRaters) { //should be fine
        double totalRatings = 0.0;
        int numRatings = 0;
        
        for (Rater r : RaterDatabase.getRaters()) {
            for (String item : r.getItemsRated()) {
                if (item.equals(id)) {
                    double movieRatings = r.getRating(item);
                    totalRatings += movieRatings;
                    numRatings++;
                }
            }
        }

        if (numRatings < minimalRaters) {
            return 0.0;
        }
        
        return (totalRatings / numRatings);
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) { //checked
        ArrayList<Rating> output = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for (String m : movies) {
            
            double avgRating = getAverageByID(m, minimalRaters);
            
            if (!(avgRating == 0.0)) {
                Rating r = new Rating(m, avgRating);
                output.add(r);
            }
        }
        return output;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> output = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for (String m : movies) {
            
            double avgRating = getAverageByID(m, minimalRaters);
            
            if (!(avgRating == 0.0)) {
                Rating r = new Rating(m, avgRating);
                output.add(r);
            }
        }
        return output;
    }
    
    private double dotProduct(Rater me, Rater r) {
    	double dotProduct = 0;
    	ArrayList<String> myMovies = me.getItemsRated();
    	for (String id: myMovies)
		{
			if (r.hasRating(id))
			{
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
			}
		}
		return dotProduct;
    }
   
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarRatings = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(id)){
                if(dotProduct(me,r)>0){
                    similarRatings.add(new Rating(r.getID(),dotProduct(me,r)));
                }
            }
        }
        Collections.sort(similarRatings,Collections.reverseOrder());
        return similarRatings;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());	
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;		
    }
    
   
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
    	ArrayList<Rating> res = new ArrayList<Rating>();
    	ArrayList<Rating> list = getSimilarities(id);	
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
	    for (String movieID : movies) {
        	double weightedAverage = 0;
        	double sum = 0;
        	int countRaters = 0;
	    	for (int i = 0; i < numSimilarRaters; i++) {
	    		Rating r = list.get(i);
	    		double weight = r.getValue();
	    		String raterID = r.getItem();
	    		Rater myRater = RaterDatabase.getRater(raterID);
	    		if(myRater.hasRating(movieID)) {
	    			countRaters++;
	    			sum += weight * myRater.getRating(movieID);
	    		}
	    		
	    	}
	    	if (countRaters >= minimalRaters) {
	    		weightedAverage = sum / countRaters;
	    		res.add(new Rating(movieID, weightedAverage));
			}			
	    }
		Collections.sort(res, Collections.reverseOrder());
		return res;	
    }
    
   
   
    public ArrayList<Movie> loadMovies(String filename) {
        //Empty ArrayList of type Movie
        ArrayList<Movie> output = new ArrayList<Movie>();
        
        //Get the file of interest
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);
        
        //Get the CSV parser of the file resource
        CSVParser parser = fr.getCSVParser();
        
        //for each record (row), put info into movie object
        for (CSVRecord record : parser) {
            Movie currMovie = new Movie(record.get("id"), 
                                        record.get("title"),
                                        record.get("year"),
                                        record.get("genre"),
                                        record.get("director"),
                                        record.get("country"),
                                        record.get("poster"),
                                        Integer.parseInt(record.get("minutes")));
            //Add created movie object to 'output' arraylist
            output.add(currMovie);
            
        }
        return output;
    }
    
    public ArrayList<Rater> loadRaters(String filename) {
        //Empty ArrayList of type Rater
        ArrayList<Rater> output = new ArrayList<Rater>();
        
        //Get the file of interest
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);
        
        //Get the CSV parser of the file resource
        CSVParser parser = fr.getCSVParser();
        
        
        for (CSVRecord record : parser) {
            Rater currRater = new EfficientRater(record.get("rater_id"));
            int currID = Integer.parseInt(record.get("rater_id"));
            boolean helper = false;
            for (int i=0; i<output.size(); i++) {
                int currIndexID = Integer.parseInt(output.get(i).getID());
                
                if (currIndexID == currID) {
                    //System.out.println("Now comparing: " + output.get(i).getID() + " and " + record.get("rater_id"));
                    output.get(i).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                    helper = true;
                }
            }
            
            if (helper == false) {
                currRater.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                output.add(currRater);
            } else {
                continue;
            }
            

        }
        
        return output;
        
        
        /* * data structure be like:
         * 
         * ArrayList<Rater> loadRaters = new ArrayList<Rater>;
         * loadRaters = {"[Rater1 info (String myID), Rater1's rating info (ArrayLIst<Rating>)] --- item 0,
         *                [Rater2 info (String myID), Rater2's rating info (ArrayLIst<Rating>)] --- item 1,
         *                [Rater3 info (String myID), Rater3's rating info (ArrayLIst<Rating>)] --- item 2,
         *                ... each item is a Rater type object
         *                
         *                for each Rater's rating info (ArrayList<Rating>):
         *                structure be like:
         *                      Rating = {"[item (movieID), ratingPoint] --- item 0,
         *                                 [item (movieID), ratingPoint] --- item 1,
         *                                 [item (movieID), ratingPoint] --- item 2,
         *                                 ...}
         * */
         
    }
}
