package domain;

import java.util.ArrayList;
import java.util.List;

public class Film {
	
	private int id = 0;
	private String title = "";
	private int year = 0;
	private float averageRating = 0.0f;
	private List<String> comments = new ArrayList<String>();
	private List<Integer> rating = new ArrayList<Integer>();
	
	private static int count = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAverageRating() {
		return this.averageRating;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public List<String> getComments() {
		return comments;
	}
	public void addComment(String comment) {
		this.comments.add(comment);
	}
	public void removeComment(int id) {
		this.comments.remove(id);
	}
	
	public List<Integer> getRating() {
		return rating;
	}
	public void addRating(int rating) {
		this.rating.add(rating);
		this.updateAverageRating();
	}
	
	private void updateAverageRating() {
		float sum = 0.0f;
		int ratingCount = getRating().size();
		for(Integer rating : getRating()) {
			sum += rating;
		}
		this.averageRating = sum/ratingCount;
	}

		
}
