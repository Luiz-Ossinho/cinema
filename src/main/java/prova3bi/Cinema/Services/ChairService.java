package prova3bi.Cinema.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Poltrona;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IChairService;

public class ChairService implements IChairService {
	private IChairRepository chairRepo;

	public ChairService(IChairRepository chairRepo) {
		this.chairRepo = chairRepo;
	}

	@Override
	public Poltrona Add(Poltrona chair) {
		var generatedKey = chairRepo.Add(chair);
		chair.setId(generatedKey);
		return chair;
	}

	@Override
	public List<Poltrona> GetAllFromSession(int SessionId) {
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
}
