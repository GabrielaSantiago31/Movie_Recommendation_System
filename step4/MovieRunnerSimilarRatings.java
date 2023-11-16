package step4;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

	private String file = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv";
	private String moviesfile = "C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv";

	public void printAverageRatings() {
		//SecondRatings sr = new SecondRatings(movies, raters);
		FourthRatings tr = new FourthRatings();

		MovieDatabase.initialize(moviesfile);
		RaterDatabase.initialize(file);

		System.out.println("Total of movies: " + MovieDatabase.getMoviesQuantityInDB());
		System.out.println("Total of raters: " + RaterDatabase.getRaters().size());
	
		ArrayList<Rating> ratings = tr.getAverageRatings(1);

		System.out.println("Movies ratings: " + ratings.size());

		Collections.sort(ratings);

		for(Rating r : ratings){
			System.out.println(r.getValue()+ " " + MovieDatabase.getTitle(r.getItem()));
		}
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

	public void printSimilarRatings() {
		FourthRatings tr = new FourthRatings();
		ArrayList<Rating> ratings = tr.getSimilarRatings("65", 20, 5);
		System.out.println("Total ratings for movies : " + ratings.size());

		for (int i = 0; i < 3; i++) {
			System.out.print(ratings.get(i).getValue()+" "+MovieDatabase.getTitle(ratings.get(i).getItem()));
		}
	}

	public void printSimilarRatingsByGenre() {
		FourthRatings tr = new FourthRatings();
		ArrayList<Rating> ratingList = tr.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));
		System.out.println("Total ratings for movies : " + ratingList.size());
		for (int i = 0; i < 3; i++) {
			System.out.print(ratingList.get(i).getValue()+" "+MovieDatabase.getTitle(ratingList.get(i).getItem())+" "+MovieDatabase.getGenres(ratingList.get(i).getItem()));
		}

	}

	public void printSimilarRatingsByDirector() {
		FourthRatings tr = new FourthRatings();
		ArrayList<Rating> ratings = tr.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
		System.out.println("Total ratings for movies : " + ratings.size());
		int qtd = ratings.size();
		if (qtd >= 3){
			qtd = 3;
		}
		for (int i = 0; i < qtd; i++) {
			System.out.print(ratings.get(i).getValue()+" "+MovieDatabase.getTitle(ratings.get(i).getItem())+" "+MovieDatabase.getDirector(ratings.get(i).getItem()));
		}

	}

	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings tr = new FourthRatings();
		AllFilters all = new AllFilters();
		all.addFilter(new GenreFilter("Adventure"));
		all.addFilter(new MinutesFilter(100, 200));
		ArrayList<Rating> ratings = tr.getSimilarRatingsByFilter("65", 10, 5, all);
		System.out.println("Total ratings for movies : " + ratings.size());
		int qtd = ratings.size();
		if (qtd >= 3){
			qtd  = 3;
		}
		for (int i = 0; i < qtd; i++) {
			System.out.print(ratings.get(i).getValue()+" "+MovieDatabase.getMinutes(ratings.get(i).getItem())+" "+MovieDatabase.getTitle(ratings.get(i).getItem())+" "+MovieDatabase.getGenres(ratings.get(i).getItem()));
		}

	}

	public void printSimilarRatingsByYearAfterAndMinutes() {
		FourthRatings tr = new FourthRatings();
		AllFilters all = new AllFilters();
		all.addFilter(new YearAfterFilter(2000));
		all.addFilter(new MinutesFilter(80, 100));
		ArrayList<Rating> ratings = tr.getSimilarRatingsByFilter("65", 10, 5, all);
		System.out.println("Total ratings for movies : " + ratings.size());
		int qtd = ratings.size();
		if (qtd >= 3){
			qtd = 3;
		}
		for (int i = 0; i < qtd; i++) {
			System.out.print(ratings.get(i).getValue()+" "+MovieDatabase.getMovie(ratings.get(i).getItem()).toString());

		}
	}
}