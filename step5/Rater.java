package step5;

import java.util.ArrayList;

public interface Rater {
	
	public String getMyId();
	
	public void addRating(String item, double rating);
	
	public double getRating(String item);
	
	public ArrayList<String> getItemsRated();
	
}
