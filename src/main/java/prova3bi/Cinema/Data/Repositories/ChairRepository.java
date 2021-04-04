package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entidades.Poltrona;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;

public class ChairRepository implements IChairRepository {

	private DBContext context;

	public ChairRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public int Add(Poltrona chair) {
		Query<Poltrona> query = new Query<Poltrona>(Query.Comand.Insert, Poltrona.class)
				.value(chair.column, "column")
				.value(chair.ocupada, "ocupada")
				.value(chair.row, "row")
				.value(chair.sessao.getId() + "", "sessao");

		return context.execute(query);
	}

	@Override
	public List<Poltrona> GetAllFromSession(int SessionId) {
		Query<Poltrona> query = new Query<Poltrona>(Query.Comand.Select, Poltrona.class)
				.addCondition("Poltronas.session = " + SessionId);

		return context.getAll(query);
	}

	@Override
	public int Put(Poltrona chair) {
		Query<Poltrona> query = new Query<Poltrona>(Query.Comand.Update, Poltrona.class)
				.value(chair.column, "column")
				.value(chair.ocupada, "ocupada")
				.value(chair.row, "row")
				.value(chair.sessao.getId() + "", "sessao")
				.PKEquals(chair.getId() + "");
		return context.execute(query);
	}

	@Override
	public Poltrona Get(int id) {
		Query<Poltrona> query = new Query<Poltrona>(Query.Comand.Select, Query.Modifiers.Limit1, Poltrona.class)
				.PKEquals(id + "");
		return context.get(query);
	}

}
