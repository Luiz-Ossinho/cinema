package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.Query.Comand;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;

public class RoomRepository implements IRoomRepository {
	private DBContext context;

	public RoomRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public int Add(Sala sala) {
		Query<Sala> query = new Query<Sala>(Query.Comand.Insert, Sala.class).value(sala.numeroSala, "numeroSala")
				.value(sala.numPoltronas, "numPoltronas").value(sala.tipo, "tipo");

		return context.execute(query);
	}

// Uso encontrado babaca
	@Override
	public List<Sala> GetAll() {
		Query<Sala> query = new Query<Sala>(Comand.Select, Sala.class);

		return context.getAll(query);
	}

	@Override
	public Sala Get(int id) {
		Query<Sala> query = new Query<Sala>(Query.Comand.Select, Query.Modifiers.Limit1, Sala.class)
				.PKEquals(id + "");

		return context.get(query);
	}

}
