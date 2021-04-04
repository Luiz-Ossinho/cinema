package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Movie;

public interface IMovieService{
	public Movie Add(Movie movie);
	public Movie Get(int id);
	public List<Movie> GetAll();
}
