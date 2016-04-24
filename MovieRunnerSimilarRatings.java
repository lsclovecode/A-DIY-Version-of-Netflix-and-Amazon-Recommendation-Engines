import java.util.*;
public class MovieRunnerSimilarRatings{
	public void printAverageRatings(){
		FourthRatings FR = new FourthRatings();
		System.out.println(FR.getRaterSize()+"numver of raters");
		//MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = FR.getAverageRatings(35);
		System.out.println(average_list.size()+"size");
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			System.out.println(value+" "+title);
		}
	}
		public void printAverageRatingsByYearAfterAndGenre(){
		Filter f1 = new YearAfterFilter(1990);
		Filter f2 = new GenreFilter("Drama");
		AllFilters f = new AllFilters();
		f.addFilter(f1);
		f.addFilter(f2);
		FourthRatings FR = new FourthRatings();
		System.out.println(FR.getRaterSize()+"numver of raters");
		//MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = FR.getAverageRatingsByFilter(8,f);
		System.out.println("found"+average_list.size()+"movies");
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			int year= MovieDatabase.getYear(id);
			String genre= MovieDatabase.getGenres(id);
			System.out.println(value+" "+year+""+genre+" "+title);
		}
	}
	public void printSimilarRatings(){
		FourthRatings FR = new FourthRatings();
		String id= FR.getSimilarRatings("71",20,5).get(0).getItem();
		String title= MovieDatabase.getTitle(id);
		System.out.println(title);
	}
	public void printSimilarRatingsByGenre(){
		Filter f = new GenreFilter("Mystery");
		FourthRatings FR = new FourthRatings();
		String id= FR.getSimilarRatingsByFilter("964",20,5,f).get(0).getItem();
		String title= MovieDatabase.getTitle(id);
		System.out.println(title);
	}
	public void printSimilarRatingsByDirector(){
		Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		FourthRatings FR = new FourthRatings();
		String id= FR.getSimilarRatingsByFilter("120",10,2,f).get(0).getItem();
		String title= MovieDatabase.getTitle(id);
		System.out.println(title);
	}
	public void printSimilarRatingsByGenreAndMinutes (){
		Filter f1 = new GenreFilter("Drama");
		Filter f2 = new MinutesFilter(80,160);
		AllFilters f = new AllFilters();
	    f.addFilter(f1);
		f.addFilter(f2);
		FourthRatings FR = new FourthRatings();
		String id= FR.getSimilarRatingsByFilter("168",10,3,f).get(0).getItem();
		String title= MovieDatabase.getTitle(id);
		System.out.println(title);
	}
	public void printSimilarRatingsByYearAfterAndMinutes(){
		Filter f1 = new YearAfterFilter(1975);
		Filter f2 = new MinutesFilter(70,200);
		AllFilters f = new AllFilters();
	    f.addFilter(f1);
		f.addFilter(f2);
		FourthRatings FR = new FourthRatings();
		String id= FR.getSimilarRatingsByFilter("314",10,5,f).get(0).getItem();
		String title= MovieDatabase.getTitle(id);
		System.out.println(title);
	}
}