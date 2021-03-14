package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entidades.Filme;

public interface IMovieRepository {
	public Filme Get(int id);
	public int Add(Filme movie);
}
