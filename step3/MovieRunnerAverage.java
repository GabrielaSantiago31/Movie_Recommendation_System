package step3;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	private String movies = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv";
	private String raters = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv";
	
	public void printAverageRatings() {
		//Create a SecondRatings object and use the CSV filenames of movie information and 
		//ratings information from the first assignment when calling the constructor.
		
		SecondRatings sr = new SecondRatings(movies, raters);
		
		
		//Print the number of movies and number of 
		//raters from the two files by calling the appropriate methods in the SecondRatings class
		
		System.out.println(sr.getMovieSize());
		System.out.println(sr.getRaterSize());
		
		// imprime uma lista de filmes e suas classificações médias, para todos os filmes que 
		//tenham pelo menos um número especificado de classificações, classificadas por médias.
		
		ArrayList<Rating> ratings = sr.getAverageRatings(3);
		Collections.sort(ratings);
		for(Rating r : ratings){
			System.out.println(r.getValue()+ " " + sr.getTitle(r.getItem()));
		}
	}
	
	public void getAverageRatingOneMovie(){
		SecondRatings sr = new SecondRatings(movies, raters);
		
		ArrayList<Rating> ratings = sr.getAverageRatings(3);
		String movieTitle = "The Godfather";
		for(Rating r : ratings){
			if(sr.getTitle(r.getItem()).equals(movieTitle)){ 
				System.out.println(r.getValue() + sr.getTitle(r.getItem()));
			}
		}
	}
}


