package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Room;

public interface IRoomRepository {
	public int Add(Room sala);
	public Room Get(int id);
	public List<Room> GetAll();
}
