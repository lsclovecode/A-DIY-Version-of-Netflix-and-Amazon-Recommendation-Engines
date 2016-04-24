import java.util.*;

public class ThirdRatings {
    
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this( "ratings.csv");
    }
    public ThirdRatings(String ratingfile){
		FirstRatings FR = new  FirstRatings();
		myRaters=FR.loadRaters(ratingfile);		
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
				if (movie_ratedby_howmany.containsKey(movieid)){
					int howmany= movie_ratedby_howmany.get(movieid)+1;
					movie_ratedby_howmany.put(movieid,howmany);
				}else{
					movie_ratedby_howmany.put(movieid,1);
				}
			}
		}
		if(movie_ratedby_howmany.containsKey(id)){
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
		ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
		for(String m: myMovies){
			String id = m;
			double average = getAverageByID(id, minimalRaters);
			if(average!=0.0){
				Rating average_rating= new Rating(id, average);
				average_rating_list.add(average_rating);
			}
		}
		return average_rating_list;
	}
    

	public ArrayList <Rating> getAverageRatingsByFilter(int minimalRaters, Filter  filterCriteria){
		ArrayList<String> movie_satisfied = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> average_rating_list = new ArrayList<Rating>();
		for(String m: movie_satisfied){
			String id = m;
			double average = getAverageByID(id, minimalRaters);
			if(average!=0.0){
				Rating average_rating= new Rating(id, average);
				average_rating_list.add(average_rating);
			}
		}
		return average_rating_list;
	}
	
	
}
