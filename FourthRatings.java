import java.util.*;

public class FourthRatings {
    
    private ArrayList<Rater> myRaters;
    
    public FourthRatings() {
        // default constructor
        MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		myRaters=RaterDatabase.getRaters();
    }
 

	public int getRaterSize(){
		return myRaters.size();
	}
	private double getAverageByID(String id, int minimalRaters){
		double count=0.0;
		HashMap<String,Integer> movie_ratedby_howmany = new HashMap<String,Integer>();
		for(Rater r : myRaters){
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
	private double dotProduct(Rater me, Rater r){
		double sum=0.0;
		HashMap<String,Rating> mylist = me.getMyrating();
		HashMap<String,Rating>  rlist = r.getMyrating();
		for(String s:mylist.keySet()){
			for(String ss: rlist.keySet()){
				if(s.equals(ss)){
					double myscore= mylist.get(s).getValue();
					double rscore=rlist.get(ss).getValue();
					sum+=(myscore-5.0)*(rscore-5.0);
				}
			}
		}
		return sum;
	}
	private ArrayList<Rating> getSimilarities(String id){
		 Rater myself=null;
		ArrayList<Rating> findsimilarities = new ArrayList<Rating>();
		for(Rater r:myRaters){
			if(r.getID().equals(id)){
				myself=r;   // Rater的ID 和跟自己关系相近的分数
			}
		}
		for(Rater r:myRaters){
			if(!r.equals(myself)){
				String yourid= r.getID();
				double similarity=dotProduct(myself,r);
				if(similarity>0.0){
					findsimilarities.add(new Rating(yourid,similarity));
				}
				
			}
		}
		Collections.sort(findsimilarities, Collections.reverseOrder());
		return findsimilarities;
	}
	public boolean hasMinRaters(String movieid, int minimalRaters, ArrayList<Rating> ratingRater,int  numSimilarRaters){

		HashMap<String,Integer> movieratedbytopraters = new HashMap<String,Integer>();
		for(int i=0;i< numSimilarRaters; i++){
			String raterid= ratingRater.get(i).getItem();
			Rater r = RaterDatabase.getRater(raterid);
			for(String s: r.getMyrating().keySet()){
				if(movieratedbytopraters.containsKey(s)){
					int howmany=movieratedbytopraters.get(s);
					movieratedbytopraters.put(s,howmany+1);
				}else{
					movieratedbytopraters.put(s,1);
				}
			}
		}
		if(movieratedbytopraters.containsKey(movieid)&& movieratedbytopraters.get(movieid)>=minimalRaters){
			return true;
		}
		return false;
	}
	
	public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minimalRaters){
	    ArrayList<Rating> ratingRater= getSimilarities(id);
		ArrayList<Rating> ratingMovie= new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
	
		for(int j=0; j<movies.size();j++){
			String movieid=movies.get(j);
			int movieid_int=Integer.parseInt(movieid);
			if(hasMinRaters(movieid,minimalRaters, ratingRater, numSimilarRaters)){
				double sum=0.0;
				double ave=0.0;
				double num=0.0;
				for(int i=0;i<numSimilarRaters;i++){
					Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
					HashMap<String, Rating> movieRated = rater.getMyrating();
					for(String everymovie : movieRated.keySet()){
						int ratermovieid_int=Integer.parseInt(everymovie);
						if(movieid_int==ratermovieid_int){
							sum+=ratingRater.get(i).getValue()*rater.getRating(everymovie);
							num+=1.0;
						}
					}
				}
				if(num!=0.0){
					ave=sum/num;
					ratingMovie.add(new Rating(movieid,ave));
				}
			}
		}
        Collections.sort(ratingMovie,Collections.reverseOrder());
		return ratingMovie;

	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minimalRaters,Filter filterCriteria){
		ArrayList<Rating> ratingRater= getSimilarities(id);
		ArrayList<Rating> ratingMovie= new ArrayList<Rating>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(int j=0; j<movies.size();j++){
			String movieid=movies.get(j);
			if(filterCriteria.satisfies(movieid)){
				int movieid_int=Integer.parseInt(movieid);
			   if(hasMinRaters(movieid,minimalRaters, ratingRater,numSimilarRaters)){
				  double sum=0.0;
				  double ave=0.0;
				   double num=0.0;
				 for(int i=0;i<numSimilarRaters;i++){
					Rater rater = RaterDatabase.getRater(ratingRater.get(i).getItem());
					HashMap<String, Rating> movieRated = rater.getMyrating();
					for(String everymovie : movieRated.keySet()){
						int ratermovieid_int=Integer.parseInt(everymovie);
						if(movieid_int==ratermovieid_int){
							sum+=ratingRater.get(i).getValue()*rater.getRating(everymovie);
							num+=1.0;
						}    
					}
				 }
				 if(num!=0.0){
					  ave=sum/num;
					  ratingMovie.add(new Rating(movieid,ave));
				   }
			    }
			}
		}
        Collections.sort(ratingMovie,Collections.reverseOrder());
		return ratingMovie;
	}
	
	
}
