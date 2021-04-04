package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sala;

public interface IRoomService {
	public Sala Add(Sala room);
	public Sala Get(int id);
	public List<Sala> GetAll();
}
