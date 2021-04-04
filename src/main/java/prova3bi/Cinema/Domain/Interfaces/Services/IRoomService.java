package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Room;

public interface IRoomService {
	public Room Add(Room room);
	public Room Get(int id);
	public List<Room> GetAll();
}
