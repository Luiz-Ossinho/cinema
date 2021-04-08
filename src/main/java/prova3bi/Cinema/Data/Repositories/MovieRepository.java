package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Movie;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;

public class MovieRepository implements IMovieRepository {

	private DBContext context;

	public MovieRepository(DBContext context) {
		this.context = context;
	}
	
	@Override
	public Movie Get(int id) {
		Query<Movie> query = new Query<Movie>(Query.Comand.Select, Query.Modifiers.Limit1, Movie.class)
				.PKEquals(id+"");

		return context.get(query);
	}

	@Override
	public int Add(Movie movie) {
		Query<Movie> query = new Query<Movie>(Query.Comand.Insert, Movie.class)
				.value(movie.title, "title")
				.value(movie.encodedPoster, "encodedPoster")
				.value(movie.synopsis, "synopsis")
				.value(movie.audioTrack, "audioTrack");

		return context.execute(query);
	}

	@Override
	public List<Movie> GetAll() {
		var query = new Query<Movie>(Query.Comand.Select, Movie.class);
		
		return context.getAll(query);
	}

}
