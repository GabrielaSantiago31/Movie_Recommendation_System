package step3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FirstRatings {
	
	public ArrayList<Movie> loadMovies(String filename) {
		
		ArrayList<Movie> movies = new ArrayList<>();
		
		BufferedReader conteudoCSV = null;
		
		String linha = "";
		
		try {
			conteudoCSV = new BufferedReader(new FileReader(filename));

			conteudoCSV.readLine();

			while((linha = conteudoCSV.readLine()) != null) {

				String[] info = linha.split(";");

				Movie movie = new Movie(info[0],info[1],Integer.parseInt(info[2]),info[4],info[5],info[3],Integer.parseInt(info[6]),info[7]);
				movies.add(movie);
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return movies;
	}
	
	public void testLoadMovies() {
		ArrayList<Movie> movies = loadMovies("C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv");
		
		//Print the number of movies
		System.out.println(movies.size());
		
		//Print each movie
		movies.forEach(movie -> System.out.println(movie));
		
		//Add code to determine how many movies include the Comedy genre
		
		List<Movie> comedyMovies = movies.stream().filter(m
				-> m.getGenres().contains("Comedy")).collect(Collectors.toList());
		
		System.out.println(comedyMovies.size());
		
		//code to determine how many movies are greater than 150 minutes in length
		List<Movie> moviesGreater150Min = movies.stream().filter(m -> m.getMinutes() > 150).collect(Collectors.toList());
		System.out.println(moviesGreater150Min.size());
		
		//code to determine the maximum number of movies by any director, and who the directors are that directed that many movies
		Set<String> directors = new HashSet<String>();
		int max = 0;
		for(int i = 0; i < movies.size(); i++) {
			directors.add(movies.get(i).getDirector());
			
			int frequencia = Collections.frequency(movies, movies.get(i));
			if(frequencia > max) {
				max = frequencia;
			}
			
		}
		System.out.println("Maximum number of movies by any director is " + max);
		System.out.println("Directors:\n" + directors);
		
	}
	
	public ArrayList<EfficientRater> loadRaters(String filename){
		ArrayList<EfficientRater> raters = new ArrayList<>();

		BufferedReader conteudoCSV = null;

		String linha = "";

		try {
			conteudoCSV = new BufferedReader(new FileReader(filename));

			conteudoCSV.readLine();

			while((linha = conteudoCSV.readLine()) != null) {

				String[] info = linha.split(";");
				HashMap<String,Rating> ratings = new HashMap<>();
				ratings.put(info[1],new Rating(info[1],Double.parseDouble(info[2])));
				EfficientRater rater = new EfficientRater(info[0],ratings);
				//rater.addRating(info[1],Double.parseDouble(info[2]));
				raters.add(rater);
			}
		}catch(Exception e) {
			System.out.println(e);
		}

		return raters;	
	}
	
	public void testLoadRaters() {
		ArrayList<EfficientRater> raters = loadRaters("C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratings_short.csv");
		
		HashMap<String,Integer> frequencymap = new HashMap<String,Integer>();
		
		for(EfficientRater r : raters) {
		  int c = 0;
		  if(frequencymap.containsKey(r.getMyId())) {
		    frequencymap.put(r.getMyId(), frequencymap.get(r.getMyId())+1);
		  }
		  else{ 
			  frequencymap.put(r.getMyId(), 1);
		  }
		}
		
		//Print the total number of raters.
		System.out.println("Total number of raters: " + frequencymap.size());
		
		
		//For each rater, print the rater’s ID and the number of ratings they did on one line
		//followed by each rating (both the movie ID and the rating given) on a separate line.
		for(Map.Entry<String, Integer> entry : frequencymap.entrySet()) {
			System.out.println("Rater's id: " + entry.getKey() + " number of ratings: " + entry.getValue());
			raters.stream().filter(value -> entry.getKey().equals(value.getMyId())).forEach(value -> System.out.println(value.getMyRatings()));
		}
		
		//code to find the number of ratings for a particular rater you specify in your code
		System.out.println("\nThe number of ratings for a particular rater: ");
		raters.stream().filter(value -> "2".equals(value.getMyId())).forEach(value -> System.out.println(value.getMyRatings()));
		
		//the maximum number of ratings by any rater
		Long qtd = raters.stream().filter(value -> "2".equals(value.getMyId())).count();
		System.out.println("maximum number of ratings: " + qtd);
		
		//find the number of ratings a particular movie has
		
		int count = 0;
        String movieId = "1798709";
		
        for (EfficientRater rater : raters) {
            ArrayList<String> rating = rater.getItemsRated();
            if (rating.contains(movieId)) {
                count++;
            }
        }
        System.out.println("the number of ratings: " + count);
		
		//how many different movies have been rated by all these raters
        
        ArrayList<String> differentMovies = new ArrayList<>();
        for (EfficientRater rater : raters) {
            ArrayList<String> ratings = rater.getItemsRated();
            for (String j : ratings) {
                if (!differentMovies.contains(j)) {
                    differentMovies.add(j);
                }
            }
        }
        System.out.println("Total of movie: " + differentMovies.size());
    }
	
	
	public static void main(String[] args) {
		FirstRatings fr = new FirstRatings();
		fr.testLoadMovies();
		
		fr.testLoadRaters();
		
		MovieRunnerAverage m = new MovieRunnerAverage();
		m.printAverageRatings();
		m.getAverageRatingOneMovie();		
		
		System.out.println(MovieDatabase.getYear("6414"));
		System.out.println(MovieDatabase.getCountry("6414"));
		System.out.println(MovieDatabase.getDirector("6414"));
		System.out.println(MovieDatabase.getGenres("6414"));
		System.out.println(MovieDatabase.getMinutes("6414"));
		System.out.println(MovieDatabase.getMovie("6414"));
		
		SecondRatings sr = new SecondRatings();
		
		System.out.println("Ratings average: " + sr.getAverageRatings(2));
		
		MovieRunnerWithFilters mrf = new MovieRunnerWithFilters();
		mrf.printAverageRatingsByYear();
		
	}
	
}
