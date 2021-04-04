package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;

public interface IChairService {
	public void OccupyChairs(List<Integer> chairIds);
	public Chair Add(Chair chair);
	public List<Chair> GetAllFromSession(int SessionId);
}
