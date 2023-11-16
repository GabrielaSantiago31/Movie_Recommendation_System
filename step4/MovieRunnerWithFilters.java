package step4;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	
	private String file = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv";
	private String moviesfile = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv";

	public void printAverageRatings() {
		//SecondRatings sr = new SecondRatings(movies, raters);
		ThirdRatings tr = new ThirdRatings(file);
		
		MovieDatabase.initialize(file);
		
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		System.out.println("Quantity of raters: " + tr.getRaterSize());
		
		ArrayList<Rating> ratings = tr.getAverageRatings(1);
		
		System.out.println("Movies ratings: " + ratings.size());
		
		Collections.sort(ratings);
		
		for(Rating r : ratings){
			System.out.println(r.getValue()+ " " + MovieDatabase.getTitle(r.getItem()));
		}
	}
	
	public void getAverageRatingOneMovie(){
		ThirdRatings tr = new ThirdRatings(file);
		ArrayList<Rating> ratings = tr.getAverageRatings(1);
		String movieTitle = "The Godfather";
		boolean exist = false;
		for(Rating r: ratings){
			if(MovieDatabase.getTitle(r.getItem()).equals(movieTitle)){
				System.out.println(r.getValue() + " " + movieTitle);
				exist = true;
			}
		}
		if(!exist){
			System.out.println("There is no such title");
		}
	}
	
	public void printAverageRatingsByYear(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, new YearsAfterFilter(2013));

		System.out.println("Total of ratings: " + ratings.size());
		
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " +
				MovieDatabase.getTitle(value.getItem()) + " " + MovieDatabase.getGenres(value.getItem())));
		
	}
	
	public void printAverageRatingsByGenre(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
		System.out.println("Total of ratings for movies: " + ratings.size());
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " + MovieDatabase.getTitle(value.getItem())
				+ " " + MovieDatabase.getGenres(value.getItem())));
	}
	
	public void printAverageRatingsByMinutes(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
		System.out.println("Total of ratings for movies: " + ratings.size());
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " + MovieDatabase.getMinutes(value.getItem())
				+ " " + MovieDatabase.getGenres(value.getItem())));
	}
	
	public void printAverageRatingsByDirectors(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		Filter d = new DirectorsFilter("Charles Chaplin,Michael Mann, Spike Jonze");
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, d);
		System.out.println("Total of ratings for movies: " + ratings.size());
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " + MovieDatabase.getTitle(value.getItem())
				+ " " + MovieDatabase.getDirector(value.getItem())));
	}
	
	public void printAverageRatingsByYearAfterAndGenre(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new GenreFilter("Romance"));
		allFilters.addFilter(new YearAfterFilter(1980));
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, allFilters);
		System.out.println("Total of ratings for movies: " + ratings.size());
		
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " + MovieDatabase.getYear(value.getItem())
				+ " " + MovieDatabase.getTitle(value.getItem()) + " " + MovieDatabase.getGenres(value.getItem())));
	}
	
	public void printAverageRatingsByDirectorsAndMinutes(){
		ThirdRatings tr = new ThirdRatings(file);
		MovieDatabase.initialize(moviesfile);
		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + tr.getRaterSize());
		
		AllFilters allFilters = new AllFilters();
		allFilters.addFilter(new MinutesFilter(30, 170));
		allFilters.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));
		
		ArrayList<Rating> ratings = tr.getAverageRatingsByFilter(1, allFilters);
		System.out.println("Total of ratings for movies: " + ratings.size());
		
		Collections.sort(ratings);
		
		ratings.stream().forEach(value -> System.out.println(value.getValue() + " " + MovieDatabase.getMinutes(value.getItem())
				+ " " + MovieDatabase.getTitle(value.getItem()) + " " + MovieDatabase.getDirector(value.getItem())));
	}
}
