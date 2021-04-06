package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;

public interface IChairRepository {
	public int Add(Chair chair);
	public int Put(Chair chair);
	public List<Chair> GetAllFromSession(int SessionId);
	public Chair Get(int id);
}
