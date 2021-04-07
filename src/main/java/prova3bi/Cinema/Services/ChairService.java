package prova3bi.Cinema.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IChairService;
import prova3bi.Cinema.Domain.Interfaces.Services.ISessionService;

public class ChairService implements IChairService {
	private IChairRepository chairRepo;
	private ISessionService sessionService;

	public ChairService(IChairRepository chairRepo, ISessionService sessionService) {
		this.chairRepo = chairRepo;
		this.sessionService = sessionService;
	}

	@Override
	public Chair Add(Chair chair) {
		var generatedKey = chairRepo.Add(chair);
		chair.setId(generatedKey);
		return chair;
	}

	@Override
	public List<Chair> GetAllFromSession(int SessionId) {
		return chairRepo.GetAllFromSession(SessionId);
	}

	@Override
	public void OccupyChairs(List<Integer> chairIds) {
		for(var chairId : chairIds){
			var chair = chairRepo.Get(chairId);
			chair.OccupyChair();
			chairRepo.Put(chair);
		}
	}

	@Override
	public void SetAsPending(List<Chair> chairs) {
		for(var chair : chairs){
			chair.state = Chair.State.Pending;
			chairRepo.Put(chair);
		}
	}

	@Override
	public Chair Get(int ChairId) {
		var chair = chairRepo.Get(ChairId);
		chair.sessao = sessionService.Get(chair.sessao.getId());
		return chair;
	}

	@Override
	public Chair OccupyChair(int chairId) {
		var chair = chairRepo.Get(chairId);
		chair.OccupyChair();
		chairRepo.Put(chair);
		return chair;
	}
}
