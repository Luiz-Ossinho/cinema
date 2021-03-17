package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Poltrona;

public interface IChairRepository {
	public int Add(Poltrona chair);
	public List<Poltrona> GetAllFromSession(int SessionId);
}
