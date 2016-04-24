import java.util.*;
public class MovieRunnerWithFilters {
	public void printAverageRatings(){
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatings(35);
		System.out.println(average_list.size()+"size");
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			System.out.println(value+" "+title);
		}
	}
	public void printAverageRatingsByYear(){
		Filter f = new YearAfterFilter(2000);
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(20,f);
		System.out.println("found"+average_list.size()+"movies");
		
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			int year= MovieDatabase.getYear(id);
			System.out.println(value+" "+year+" "+title);
		}
		
	}
	public void printAverageRatingsByGenre(){
		Filter f = new GenreFilter("Comedy");
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(20,f);
		System.out.println("found"+average_list.size()+"movies");
		/*
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			String genre= MovieDatabase.getGenres(id);
			System.out.println(value+" "+genre+" "+title);
		}
		*/
	}
	
	public void printAverageRatingsByMinutes(){
		Filter f = new MinutesFilter(105,135);
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(5,f);
		System.out.println("found"+average_list.size()+"movies");
		/*
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			int time= MovieDatabase.getMinutes(id);
			System.out.println(value+" "+time+" "+title);
		}
		*/
	}
	public void printAverageRatingsByDirectors(){
		Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(4,f);
		System.out.println("found"+average_list.size()+"movies");
		/*
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			String directors= MovieDatabase.getDirector(id);
			System.out.println(value+" "+directors+" "+title);
		}
		*/
	}
	public void printAverageRatingsByYearAfterAndGenre(){
		Filter f1 = new YearAfterFilter(1990);
		Filter f2 = new GenreFilter("Drama");
		AllFilters f = new AllFilters();
		f.addFilter(f1);
		f.addFilter(f2);
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(8,f);
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
	public void printAverageRatingsByDirectorsAndMinutes (){
		Filter f1 = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
		Filter f2 = new MinutesFilter(90,180);
		AllFilters f = new AllFilters();
		f.addFilter(f1);
		f.addFilter(f2);
		ThirdRatings TR = new ThirdRatings();
		System.out.println(TR.getRaterSize()+"numver of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> average_list = TR.getAverageRatingsByFilter(3,f);
		System.out.println("found"+average_list.size()+"movies");
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= MovieDatabase.getTitle(id);
			int time= MovieDatabase.getMinutes(id);
			String director= MovieDatabase.getDirector(id);
			System.out.println(value+" "+time+""+director+" "+title);
		}
	}
}