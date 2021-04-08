package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Room;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;

public class RoomRepository implements IRoomRepository {
	private DBContext context;

	public RoomRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public int Add(Room sala) {
		Query<Room> query = new Query<Room>(Query.Comand.Insert, Room.class).value(sala.numeroSala, "numeroSala")
				.value(sala.numPoltronas, "numPoltronas").value(sala.tipo, "tipo");

		return context.execute(query);
	}

// Uso encontrado babaca
	@Override
	public List<Room> GetAll() {
		Query<Room> query = new Query<Room>(Query.Comand.Select, Room.class);

		return context.getAll(query);
	}

	@Override
	public Room Get(int id) {
		Query<Room> query = new Query<Room>(Query.Comand.Select, Query.Modifiers.Limit1, Room.class)
				.PKEquals(id + "");

		return context.get(query);
	}

}
