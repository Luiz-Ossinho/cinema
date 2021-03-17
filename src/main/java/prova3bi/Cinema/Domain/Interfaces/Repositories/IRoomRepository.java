package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entidades.Sala;

public interface IRoomRepository {
	public int Add(Sala sala);
	public Sala Get(int id);
}
