package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sala;

public interface IRoomRepository {
	public int Add(Sala sala);
	public List<Sala> GetAll();
}
