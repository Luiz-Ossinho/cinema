package prova3bi.Cinema.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Domain.Interfaces.Services.IRoomService;;

public class RoomService implements IRoomService{
	private IRoomRepository repo;

	public RoomService(IRoomRepository repo) {
		this.repo = repo;
	}
	
	@Override
	public Sala Add(Sala room) {
		var generatedKey = repo.Add(room);
		room.setId(generatedKey);

		return room;
	}

	@Override
	public Sala Get(int id) {
		return repo.Get(id);
	}

	@Override
	public List<Sala> GetAll() {
		return repo.GetAll();
	}
}
