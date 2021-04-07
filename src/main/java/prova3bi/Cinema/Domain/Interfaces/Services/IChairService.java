package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;

public interface IChairService {
	public void OccupyChairs(List<Integer> chairIds);
	public Chair OccupyChair(int chairId);
	public Chair Add(Chair chair);
	public void SetAsPending(List<Chair> chairs);
	public List<Chair> GetAllFromSession(int SessionId);
	public Chair Get(int ChairId);
}
