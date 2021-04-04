package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Filme;

public interface IMovieService{
	public Filme Add(Filme movie);
	public Filme Get(int id);
	public List<Filme> GetAll();
}
