package step5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfficientRater implements Rater{
	
	private String myId;
	private HashMap<String, Rating> myRatings; //chave = id do filme
	
	public EfficientRater(String myId, HashMap<String,Rating> myRatings) {
		this.setMyId(myId);
		this.setMyRatings(myRatings);
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public HashMap<String,Rating> getMyRatings() {
		return myRatings;
	}

	public void setMyRatings(HashMap<String,Rating> myRatings) {
		this.myRatings = myRatings;
	}
	
	public void addRating(String item, double rating) {
		Rating newRating = new Rating(item, rating);
		myRatings.put(item, newRating);
	}
	
	public double getRating(String item) {
		if(myRatings.containsKey(item)){
			return myRatings.get(item).getValue();
		}
		return -1;
	}
	
	public ArrayList<String> getItemsRated() {
		ArrayList<String> itemsRated = new ArrayList<>(myRatings.keySet());
		
		return itemsRated;
	}
	
	public boolean hasRating(String item){
		return myRatings.containsKey(item);
	}
}
