import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage{
	public void printAverageRatings(){
		SecondRatings SR = new SecondRatings();
		ArrayList<Rating> average_list = SR.getAverageRatings(12);
		System.out.println(average_list.size()+"size");
		Collections.sort(average_list);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= SR.getTitle(id);
			System.out.println(value+" "+title);
		}
	}
	public void getAverageRatingOneMovie(String mvtitle){
		SecondRatings SR = new SecondRatings();
		ArrayList<Rating> average_list = SR.getAverageRatings(1);
		for(Rating r: average_list){
			double value= r.getValue();
			String id = r.getItem();
			String title= SR.getTitle(id);
			if(title.equals(mvtitle)){
				System.out.println(value+" "+title);
			}
		}
	}
}