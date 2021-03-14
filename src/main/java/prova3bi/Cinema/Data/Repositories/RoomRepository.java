package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.QueryComand;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;

public class RoomRepository implements IRoomRepository {
	private DBContext context;

	public RoomRepository(DBContext context) {
		this.context = context;
	}
	
	@Override
	public int Add(Sala sala) {
		Query<Sala> query = new Query<Sala>(QueryComand.Insert, Sala.class)
				.value(sala.numeroSala, "numeroSala")
				.value(sala.numPoltronas, "numPoltronas")
				.value(sala.tipo, "tipo");

		return context.execute(query);
	}

	@Override
	public List<Sala> GetAll() {
		Query<Sala> query = new Query<Sala>(QueryComand.Select, Sala.class);

		return context.getAll(query);
	}

}