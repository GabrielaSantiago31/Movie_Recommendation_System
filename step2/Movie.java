package step2;

public class Movie {
	
	private String id;
	private String title;
	private Integer year;
	private String genres; //one String of one or more genres separated by commas
	private String director;
	private String country;
	private Integer minutes;
	private String poster;
	
	public Movie(String id, String title, Integer year, String genres, 
			String director, String country, Integer minutes, String poster) {
		
		this.setId(id);
		this.setTitle(title);
		this.setYear(year);
		this.setGenres(genres);
		this.setDirector(director);
		this.setCountry(country);
		this.setMinutes(minutes);
		this.setPoster(poster);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		if(poster == null) {
			System.out.println("N/A");
		}
		this.poster = poster;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", year=" + year + ", genres=" + genres + ", director="
				+ director + ", country=" + country + ", minutes=" + minutes + ", poster=" + poster + "]";
	}
	
	
}
