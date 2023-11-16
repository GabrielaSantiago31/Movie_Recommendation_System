package step2;

import java.util.ArrayList;

public class SecondRatings {
	
	private ArrayList<Movie> myMovies;
	private ArrayList<Rater> myRaters;
	
	public SecondRatings() {
		this("C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv", 
				"C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv");
	}
	
	public SecondRatings(String moviefile, String ratingsfile) {
		FirstRatings firstRatings = new FirstRatings();
		
		myMovies = firstRatings.loadMovies(moviefile);
		myRaters = firstRatings.loadRaters(ratingsfile);
	}
	
	//write a public method named getMovieSize, 
	//which returns the number of movies that were read in and stored in the ArrayList of type Movie.
	
	public int getMovieSize() {
		return myMovies.size();
		
	}
	
	//write a public method named getRaterSize, 
	//which returns the number of raters that were read in and stored in the ArrayList of type Rater.
	
	public int getRaterSize() {
		
		return myRaters.size();
	}
	
	//representa a classificação média do filme para esse ID se houver pelo menos classificações minimalRaters
	private double getAverageById(String id, int minimalRaters){
		int counter = 0;
		double total = 0;
		for(Rater r : myRaters){
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
		double average;
		ArrayList<Rating> ratings = new ArrayList<>();
		for(Movie m : myMovies){
			average = getAverageById(m.getId(), minimalRaters);
			if(average > 0){
				ratings.add(new Rating(m.getId(),average));
			}
		}
		return ratings;
	}
	
	public String getTitle(String id){
		for(Movie m : myMovies){
			if(m.getId().equals(id)){
				return m.getTitle();	
			}
		}
		return "ID not found.";
	}
	
	public String getId(String title){
		for(Movie m : myMovies){
			if(m.getTitle().equals(title)){
				return m.getId();
			}
		}
		return "NO SUCH FILE";
	}
}
