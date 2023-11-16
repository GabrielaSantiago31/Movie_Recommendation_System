package step3;

import java.util.ArrayList;

public class PlainRater implements Rater{
	
	private String myId;
	private ArrayList<Rating> myRatings;
	
	public PlainRater(String myId, ArrayList<Rating> myRatings) {
		this.myId = myId;
		this.myRatings = new ArrayList<Rating>();
	}

	public String getMyId() {
		return myId;
	}

	public ArrayList<Rating> getMyRatings() {
		return myRatings;
	}
	
	public void addRating(String item, double rating) {
		myRatings.add(new Rating(item, rating));
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
	
	public boolean hasRating(String item){
		for(int i = 0; i < myRatings.size(); i++){
			if(myRatings.get(i).getItem().equals(item)){
				return true;
			}
		}
		return false;
	}
	
	public int numRatings(){
		return myRatings.size();
	}
	
}
