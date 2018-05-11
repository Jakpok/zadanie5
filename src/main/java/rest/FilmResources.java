package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import domain.Film;
import domain.service.FilmService;

@Path("/film")
public class FilmResources {	
	
		private FilmService db = new FilmService();
		
		//wyswietlenie wszystkich filmow
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Film> getAll() {
			return db.getAll();
		}
		
		//wyswietlenie filmu o podanym id
		@GET
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response get(@PathParam("id") int id) {
			Film result = db.getFilm(id);
			if(result == null) {
				return Response.status(404).build();
			}
			return Response.ok(result).build();
		}
		
		//dodanie nowego filmu
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response add(Film film) {
			db.addFilm(film);
			return Response.ok(film.getId()).build();
		}
		
		//aktualizacja danych o istniejacym filmie
		@PUT
		@Path("/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response update(@PathParam("id") int id, Film film) {
			Film result = db.getFilm(id);
			if(result==null) 
				return Response.status(404).build();
			film.setId(id);
			db.updateFilm(film);
			return Response.ok("film modified").build();
		}
		
		//wyswietlenie komentarzy danego filmu
		@GET
		@Path("/{filmId}/comment")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getComment(@PathParam("filmId") int filmId) {
			Film result = db.getFilm(filmId);
			if(result.equals(null))
				return Response.status(404).build();
			String json = new Gson().toJson(result.getComments());
			return Response.ok().entity(json).build();
		}
		
		//dodanie komentarza pod konkretny film
		@POST
		@Path("/{id}/comment")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addComment(@PathParam("id") int id, String comment) {
			Film result = db.getFilm(id);
			if (result.equals(null))
				return Response.status(404).build();
			result.addComment(comment);
			return Response.ok("comment added").build();
		}
		
		//usuwanie komentarza spod filmu
		@DELETE
		@Path("/{id}/comment")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response delete(@PathParam("id") int id, String commentID) {
			Film film = db.getFilm(id);
			if (film.equals(null))
				return Response.status(404).entity("film not found").build();
			
			int commentID_int = Integer.parseInt(commentID);
			if(commentID_int >= film.getComments().size() || commentID_int<0) {
				return Response.status(404).build();
			}			
			film.removeComment(commentID_int);
			return Response.ok("comment removed").build();
		}
		
		//dodanie oceny pod konkretny film
		@POST
		@Path("/{id}/rating")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response setRating(@PathParam("id") int id, String rating) {
			Film film = db.getFilm(id);
			if (film.equals(null))
				return Response.status(404).build();
			
			int rat = Integer.parseInt(rating);
			film.addRating(rat);
			return Response.ok("rating added").build();
		}
}
	
