package step5;

import java.util.ArrayList;

public class ThirdRatings {
	
	private ArrayList<EfficientRater> myRaters;
	
	public ThirdRatings() {
		this("C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv");
	}
	
	public ThirdRatings(String ratingsfile) {
		FirstRatings firstRatings = new FirstRatings();
		
		myRaters = firstRatings.loadRaters(ratingsfile);
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
	
	//representa a classificação média do filme para esse ID se houver pelo menos classificações minimalRaters
	private double getAverageById(String id, int minimalRaters){
		int counter = 0;
		double total = 0;
		for(EfficientRater r : myRaters){
			double rating = r.getRating(id);
			if(rating != -1){
				counter ++;
				total += rating;
			}
		}
		
		if(counter >= minimalRaters) return total/counter;
		return 0.0;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<>();
		double average;
		Filter trueFilter = new TrueFilter();
		for(String m : MovieDatabase.filterBy(trueFilter)){
			average = getAverageById(m, minimalRaters);
			if(average > 0){
				ratings.add(new Rating(m,average));
			}
		}
		return ratings;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
		ArrayList<Rating> moviesIds = new ArrayList<>();
		Filter trueFilter = new TrueFilter();
		ArrayList<String> movieId = MovieDatabase.filterBy(trueFilter);
		
		for(String id : movieId){
			if(filterCriteria.satisfies(id)){
				double average = getAverageById(id, minimalRaters);
				if(average > 0){
					moviesIds.add(new Rating(id, average));
				}
			}
		}
		return moviesIds;
	}
	
}
