package step4;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
	
	private double dotProduct(Rater me, Rater r){
		double dProduct = 0;
		ArrayList<String> meMovieId = me.getItemsRated();
		for(String id : meMovieId){
			if(r.getItemsRated().contains(id)){
				dProduct += (me.getRating(id)-5)*(r.getRating(id)-5);
			}
		}
		return dProduct;
	}
	
	private ArrayList<Rating> getSimilarities(String id){
		
		ArrayList<Rating> ratings = new ArrayList<>();
		ArrayList<Rater> raters = new ArrayList<>();
		
		for(Rater r : RaterDatabase.getRaters()){
			if(!r.getMyId().equals(id)){
				double dProduct = dotProduct(RaterDatabase.getRater(id), r);
				if(dProduct > 0){
					ratings.add(new Rating(r.getMyId(), dProduct));
				}
			}
		}
		Collections.sort(ratings);
		Collections.reverse(ratings);
		
		return ratings;
	}
	
	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
		ArrayList<Rating> ratings = new ArrayList<>();
		Filter trueFilter = new TrueFilter();
		for(String m : MovieDatabase.filterBy(trueFilter)){
			double average = 0;
			ArrayList<Rating> similarities = getSimilarities(id);
			int counter = 0;
			double total = 0;
			int similaritiesWeightTotal = 0;
			
			for(int i = 0; i < numSimilarRaters; i++){
				double rating = RaterDatabase.getRater(similarities.get(i).getItem()).getRating(m);
				
				if(rating != -1){
					counter ++;
					total += rating * similarities.get(i).getValue();
					similaritiesWeightTotal += similarities.get(i).getValue();
				}
			}
			if(counter >= minimalRaters){
				average = total / counter;
			}
			if(average > 0){
				ratings.add(new Rating(m, average));
			}
		}
		Collections.sort(ratings);
		Collections.reverse(ratings);
		
		return ratings;
	}
	
	public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters,int minimalRaters, Filter f){
		ArrayList<Rating> ratings = new ArrayList<>();
		
		Filter trueFilter = new TrueFilter();
		
		ArrayList<Rating> similarities = getSimilarities(raterId);
		
		for(String m : MovieDatabase.filterBy(trueFilter)){
			if(f.satisfies(m)){
				double average = 0;
				ArrayList<Rating> list = getSimilarities(raterId);
				int counter = 0;
				double total = 0;
				double similaritiesWeightTotal = 0;
				for(int i = 0; i < numSimilarRaters; i++){
					double rating = RaterDatabase.getRater(list.get(i).getItem()).getRating(m);
					if(rating != -1){
						counter ++;
						total += rating * list.get(i).getValue();
						similaritiesWeightTotal += rating * list.get(i).getValue();
					}
				}
				if(counter >= minimalRaters){
					average = total / counter;
				}
				if(average > 0){
					ratings.add(new Rating(m, average));
				}
			}
		}
		Collections.sort(ratings);
		Collections.reverse(ratings);
		return ratings;
	}
	
	
	private double getAverageById(String id, int minimalRaters){
		int counter = 0;
		double total = 0;
		for(Rater r : RaterDatabase.getRaters()){
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
