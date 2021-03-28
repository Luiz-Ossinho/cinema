package prova3bi.Cinema.Data.Repositories;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;

public class MovieRepository implements IMovieRepository {

	private DBContext context;

	public MovieRepository(DBContext context) {
		this.context = context;
	}
	
	@Override
	public Filme Get(int id) {
		Query<Filme> query = new Query<Filme>(Query.Comand.Select, Query.Modifiers.Limit1, Filme.class)
				.PKEquals(id+"");

		return context.get(query);
	}

	@Override
	public int Add(Filme movie) {
		Query<Filme> query = new Query<Filme>(Query.Comand.Insert, Filme.class)
				.value(movie.title, "title")
				.value(movie.encodedPoster, "encodedPoster")
				.value(movie.synopsis, "synopsis")
				.value(movie.audioTrack, "audioTrack");

		return context.execute(query);
	}

}
