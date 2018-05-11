package domain.service;

import java.util.ArrayList;
import java.util.List;

import domain.Film;

public class FilmService {
	
	private static ArrayList<Film> db = new ArrayList<Film>();

	private static int currentFilmId = 1;
	
	public List<Film> getAll() {
		return this.db;
	}
	
	public Film getFilm(int id) {
		for(Film f : db) {
			if(f.getId()==id) return f;
		}
		return null;
	}
	
	public void addFilm(Film film) {
		film.setId(currentFilmId);
		db.add(film);
		++currentFilmId;
	}
	
	public void updateFilm(Film film) {
		for(Film f : db) {
			if(f.getId()==film.getId()) {
				if( ! film.getTitle().equals("")) f.setTitle(film.getTitle());
				if(film.getYear() != 0) f.setYear(film.getYear());
				
				if( ! film.getComments().isEmpty()) {
					for(String s : film.getComments()) {
						f.addComment(s);
					}
				}
				
					if( ! film.getRating().isEmpty()) {
					for (int r : film.getRating()) {
						f.addRating(r);
					}
				}
			}
		}
	}
	
	public List<String> getComments(int id) {
		return db.get(id).getComments();
	}
	

	public void addComment(String title, String comment) {
		for (Film film : db) {
			if(film.getTitle().equalsIgnoreCase(title))   film.addComment(comment);
		}
	}
	
	public void setRating(String title, int rating) throws IllegalArgumentException{
		if (rating >= 0 && rating <= 10) {
			for (Film film : db) {
				if(film.getTitle().equalsIgnoreCase(title)) {
					film.addRating(rating);
				}
			}
		} else throw new IllegalArgumentException("Film rating should be from 0 to 10");
	}
	
	
	
	
}
