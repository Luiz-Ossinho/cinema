package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Movie;

public interface IMovieRepository {
	public Movie Get(int id);
	public int Add(Movie movie);
	public List<Movie> GetAll();
}
