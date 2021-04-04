package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Poltrona;

public interface IChairService {
	public void OccupyChairs(List<Integer> chairIds);
	public Poltrona Add(Poltrona chair);
	public List<Poltrona> GetAllFromSession(int SessionId);
}
