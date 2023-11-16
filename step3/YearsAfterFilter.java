package step3;

public class YearsAfterFilter implements Filter{
	
	private int year = 0;
	
	public YearsAfterFilter(int year){
		this.year = year;
	}
	
	@Override
	public boolean satisfies(String id){ 
		return MovieDatabase.getYear(id) >= year;
	}
}
