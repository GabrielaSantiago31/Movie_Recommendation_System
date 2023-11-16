package step3;

import java.util.Objects;

public class Rating implements Comparable<Rating>{
	
	private String item;
	private double value;
	
	public Rating(String item, double value) {
		this.setItem(item);
		this.setValue(value);
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Rating [item=" + item + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Rating o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(item, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rating other = (Rating) obj;
		return Objects.equals(item, other.item)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	
	
	
	
}
