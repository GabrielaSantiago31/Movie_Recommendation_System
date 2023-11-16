package step2;

import java.util.ArrayList;

public class Rater {
	
	private String myId;
	private ArrayList<Rating> myRatings;
	
	public Rater(String myId, ArrayList<Rating> myRatings) {
		this.setMyId(myId);
		this.setMyRatings(myRatings);
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}

	public ArrayList<Rating> getMyRatings() {
		return myRatings;
	}

	public void setMyRatings(ArrayList<Rating> myRatings) {
		this.myRatings = myRatings;
	}
	
	public void addRating(String item, double rating) {
		Rating newRating = new Rating(item, rating);
		myRatings.add(newRating);
	}
	
	public double getRating(String item) {
		for(Rating r : myRatings) {
			if(r.getItem().equals(item)) {
				return r.getValue();
			}
		}
		return -1;
	}
	
	public ArrayList<String> getItemsRated() {
		ArrayList<String> itemsRated = new ArrayList<>();
		for(Rating rating : myRatings) {
			itemsRated.add(rating.getItem());
		}
		return itemsRated;
		
	}
}
