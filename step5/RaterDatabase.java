package step5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RaterDatabase {
	
	private static HashMap<String, Rater> ourRaters;
	private static String file = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv";
	
	public static void initialize(String filename){
		if(ourRaters == null){
			ourRaters = new HashMap<String, Rater>();
			addRatings(filename);
		}
	}
	
	private static void initialize(){
		if(ourRaters == null){
			ourRaters = new HashMap<String, Rater>();
		}	
	}
	
	private static void addRatings(String filename){
		initialize();
		FirstRatings fr = new FirstRatings();
		ArrayList<EfficientRater> list = fr.loadRaters(filename);
		
		for(EfficientRater r : list){
			for(String items : r.getItemsRated()){
				addRaterRating(r.getMyId(), items, r.getRating(items));
			}
		}
	}
	
	public static void addRaterRating(String raterId, String movieId, double rating){
		initialize();
		Rater rater = null;
		HashMap<String, Rating> ratings = new HashMap<>();
		
		//System.out.println("Acionou o addRaterRating: " + " " + raterId + " " + movieId + " " + rating);
				
		if(ourRaters.containsKey(raterId)){
			rater = ourRaters.get(raterId);
			rater.addRating(movieId, rating);
		}else{
			ratings.put(movieId, new Rating(movieId, rating));
			rater = new EfficientRater(raterId,ratings);
		}	

		ourRaters.put(rater.getMyId(), rater);	
	}
	
	
	public static Rater getRater(String id){
		initialize();
		return ourRaters.get(id);
	}
	
	public static ArrayList<Rater> getRaters(){
		initialize();
		ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values());
		
		return list;
	}
	
	public static int size(){
		return ourRaters.size();
	}
    
}
