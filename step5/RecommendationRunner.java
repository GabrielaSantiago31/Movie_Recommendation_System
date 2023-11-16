package step5;

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {
	
//	private String file = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv";
//	private String moviesfile = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv";

	@Override
	public ArrayList<String> getItemsToRate() {
		ArrayList<String> movieToBeRate = new ArrayList<>();
		ArrayList<String> movieId = MovieDatabase.filterBy(new TrueFilter());
		for(int i = 0; movieToBeRate.size() < 10; i++){
			Random ran = new Random();
			int random = ran.nextInt(movieId.size());
			if(!movieToBeRate.contains(movieId.size())){
				movieToBeRate.add(movieId.get(random));
			}
		}
		return movieToBeRate;
	}

	@Override
	public void printRecommendationsFor(String webRaterId) {
//		MovieDatabase.initialize(moviesfile);
//		RaterDatabase.initialize(file);
		
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterId, 4, 2);
		if(ratings.size() == 0){
			System.out.println("Sorry! There is no recommendations for you!");
		}else{
			ArrayList<String> movieToBeRate = getItemsToRate();
			ArrayList<Rating> ids = new ArrayList<>();
			int counter = 0;
			for(int i = 0; ids.size() + counter != ratings.size() && ids.size() < 10; i++){
				if(!movieToBeRate.contains(ratings.get(i).getItem())){
					ids.add(ratings.get(i));
				}else{
					counter++;
				}
			}
			System.out.println("Total of ids = " + ids.size());
			
			}
		}
		
//	public static void main(String[] args) {
//		RecommendationRunner a = new RecommendationRunner();
//		a.getItemsToRate();
//		a.printRecommendationsFor("4");
//	}
}
	

