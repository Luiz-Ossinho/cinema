package prova3bi.Cinema.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Movie;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IMovieService;

public class MovieService implements IMovieService {

	private IMovieRepository movieRepo;
	
	public MovieService(IMovieRepository movieRepo) {
		this.movieRepo = movieRepo;
	}

	@Override
	public Movie Add(Movie movie) {
		var generatedKey = movieRepo.Add(movie);
		movie.setId(generatedKey);
		
		return movie;
	}

	@Override
	public Movie Get(int id) {
		return movieRepo.Get(id);
	}

	@Override
	public List<Movie> GetAll() {
		return movieRepo.GetAll();
	}

}
