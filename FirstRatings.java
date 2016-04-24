import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{
	public ArrayList<Movie> loadMovies (String filename){
		ArrayList<Movie> mv = new ArrayList<Movie>();
		FileResource fr = new FileResource("D://计算机学习//公开课//duke capstone project//"+filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord csv: parser){
			 String id= csv.get(0);
			 String title=csv.get(1);
			 String year = csv.get(2);
			 String country = csv.get(3);
			 String genre =csv.get(4);
			 String dr=csv.get(5);
			 int minutes = Integer.parseInt(csv.get(6));
			 String poster= csv.get(7);
			Movie m = new Movie(id,title,year,country,genre,dr,minutes,poster);
			mv.add(m);
		}
		return mv;
	}
	public void testLoadMovies(){
		ArrayList<Movie> mv= loadMovies("ratedmoviesfull.csv");
		System.out.println("Total movies"+mv.size());
		HashMap<String,Integer> eachdrnum = new HashMap<String,Integer>();
		int count1=0;
		int count2=0;
		for(Movie m : mv){
			
			if (m.getGenres().indexOf("Comedy")!=-1){
				count1+=1;
			}
			if(m.getMinutes()>150){
				count2+=1;
			}
			String director= m.getDirector();
			String[] str = director.split(",");
			for(String s: str){
				if(eachdrnum.keySet().contains(s)){
					eachdrnum.put(s,eachdrnum.get(s)+1);
				}else{
					eachdrnum.put(s,1);
				}
			}
			
	    }
		System.out.println(count1+"comedy");
		System.out.println(count2+"longer than 150 min");
		int max=0;
		for(String s : eachdrnum.keySet()){
			if(eachdrnum.get(s)>max){
				max=eachdrnum.get(s);
			}
		}
		for(String s : eachdrnum.keySet()){
			if(eachdrnum.get(s)==max){
				System.out.println(s);
			}
		}
		System.out.println(max+"directed by director");
		
	}
	public ArrayList<EfficientRater> loadRaters (String filename){
		ArrayList<EfficientRater> rater = new ArrayList<EfficientRater>();
		FileResource fr = new FileResource("D://计算机学习//公开课//duke capstone project//"+filename);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord csv: parser){
			boolean flag= true;
			String raterid=csv.get(0);
			String item = csv.get(1);
			Double score = Double.parseDouble (csv.get(2));
			for(EfficientRater r: rater){
				if(r.getID().equals(raterid)){
					r.addRating(item,score);
					flag=false;
				}
			}
			if(flag){
				EfficientRater rr = new EfficientRater(raterid);
			    rr.addRating(item,score);
			    rater.add(rr);
			}			
		}
		return rater;
	}
	public void testLoadRaters(){
		ArrayList<EfficientRater> allrater= loadRaters("ratings.csv");
		System.out.println("Total rater"+allrater.size());
		int max=0;
		int ratercount=0;
		int maxrating=0;
		String item = "1798709";
		ArrayList<String> diff = new ArrayList<String>(); 
		for (EfficientRater r: allrater){
			if(r.getID().equals("193")){
				System.out.println("193 has"+r.getMyrating().size()+"ratings");
			}
			if (r.numRatings()>max){
				max=r.getMyrating().size();
			}
			if (r.hasRating(item)){
				ratercount+=1;
			}
			for(String s : r.getItemsRated()){
				if(!diff.contains(s)){
					diff.add(s);
				}
			}
		}
		for (EfficientRater r: allrater){
			if(r.numRatings()==max){
				maxrating+=1;
				System.out.println(r.getID());
			}
		}
		System.out.println("movie"+item+"  "+ratercount);
		System.out.println("total number of "+diff.size()+"were rated");
		System.out.println("there are "+maxrating+"raters have"+max+"ratings");
	}
}