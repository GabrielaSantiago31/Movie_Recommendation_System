package step5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieDatabase {
	
	private static HashMap<String, Movie> ourMovies;
	
	public static void initialize(String moviefile){
		try{
			if(ourMovies == null){
				ourMovies = new HashMap<String, Movie>();
				loadMovies(moviefile);
			}
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public static void initialize(){
		
		try{
			if(ourMovies == null){
				ourMovies = new HashMap<String, Movie>();
				loadMovies("C:/Users/gsantiag/OneDrive - Capgemini/Desktop/Recommendation_System/ratedmovies_short.csv");
			}
		}catch(Exception e){
			e.getMessage();
		}
		
	}
	
	public static void loadMovies(String filename){
		FirstRatings fr = new FirstRatings();
		ArrayList<Movie> movies = fr.loadMovies(filename);
		for(Movie m : movies){
			ourMovies.put(m.getId(), m);
		}
	}
	
	public static boolean containsId(String id){
		initialize();
		return ourMovies.containsKey(id);
	}

	public static HashMap<String, Movie> getOurMovies() {
		initialize();
		return ourMovies;
	}
	
	public static int getYear(String id){
		initialize();
		return ourMovies.get(id).getYear();
	}
	
	public static String getTitle(String id){
		initialize();
		return ourMovies.get(id).getTitle();
	}
	
	public static String getPoster(String id){
		initialize();
		return ourMovies.get(id).getPoster();
	}
	
	public static Integer getMinutes(String id){
		initialize();
		return ourMovies.get(id).getMinutes();
	}
	
	public static String getCountry(String id){
		initialize();
		return ourMovies.get(id).getCountry();
	}
	
	public static String getGenres(String id){
		initialize();
		return ourMovies.get(id).getGenres();
	}
	
	public static String getDirector(String id){
		initialize();
		return ourMovies.get(id).getDirector();
	}
	
	public static Movie getMovie(String id){
		initialize();
		return ourMovies.get(id);
	}
	
	public static int getMoviesQuantityInDB(){
		initialize();
		return ourMovies.size();
	}
	
	public static ArrayList<String> filterBy(Filter f){
		initialize();
		ArrayList<String> moviesId = new ArrayList<>();
		for(String id : ourMovies.keySet()){
			if(f.satisfies(id)){
				moviesId.add(id);
			}
		}
		return moviesId;
	}
	
	
}
