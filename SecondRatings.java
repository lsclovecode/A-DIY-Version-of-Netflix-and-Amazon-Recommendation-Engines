
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingfile){
		FirstRatings FR = new  FirstRatings();
		myMovies=FR.loadMovies(moviefile);
		myRaters=FR.loadRaters(ratingfile);		
	}
	public int getMovieSize(){
		return myMovies.size();
	}
	public int getRaterSize(){
		return myRaters.size();
	}
	private double getAverageByID(String id, int minimalRaters){
		double count=0.0;
		HashMap<String,Integer> movie_ratedby_howmany = new HashMap<String,Integer>();
		for(EfficientRater r : myRaters){
			for(Rating rt:r.getMyrating().values()){
				String movieid = rt.getItem();
				double value = rt.getValue();
				if(movieid.equals(id)) count+=value;
				if (movie_ratedby_howmany.keySet().contains(movieid)){
					int howmany= movie_ratedby_howmany.get(movieid)+1;
					movie_ratedby_howmany.put(movieid,howmany);
				}else{
					movie_ratedby_howmany.put(movieid,1);
				}
			}
		}
		if(movie_ratedby_howmany.keySet().contains(id)){
			int totalratings= movie_ratedby_howmany.get(id);
		    if(totalratings<minimalRaters) return 0.0;
		    else{
			   double average = count/totalratings;
			    return average;
		    }
		}
		return 0.0;
		
	}
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> average_rating_list = new ArrayList<Rating>();
		for(Movie m: myMovies){
			String id = m.getID();
			double average = getAverageByID(id, minimalRaters);
			if(average!=0.0){
				Rating average_rating= new Rating(id, average);
				average_rating_list.add(average_rating);
			}
		}
		return average_rating_list;
	}
	public String getTitle (String id){
		for(Movie m: myMovies){
			if (m.getID().equals(id)){
				return m.getTitle();
			}
		}
		return "ID was not found";
	}
	public String getID (String title){
		for(Movie m: myMovies){
			if (m.getTitle().equals(title)){
				return m.getID();
			}
		}
		return "No such Title";
	}
	
	
}
