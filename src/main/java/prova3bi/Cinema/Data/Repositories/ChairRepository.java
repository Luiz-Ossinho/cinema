package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Chair;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;

public class ChairRepository implements IChairRepository {

	private DBContext context;

	public ChairRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public int Add(Chair chair) {
		Query<Chair> query = new Query<Chair>(Query.Comand.Insert, Chair.class)
				.value(chair.column, "column")
				.value(chair.state, "state")
				.value(chair.row, "row")
				.value(chair.sessao.getId() + "", "sessao");

		return context.execute(query);
	}

	@Override
	public List<Chair> GetAllFromSession(int SessionId) {
		Query<Chair> query = new Query<Chair>(Query.Comand.Select, Chair.class)
				.addCondition("Poltronas.session = " + SessionId);

		return context.getAll(query);
	}

	@Override
	public int Put(Chair chair) {
		Query<Chair> query = new Query<Chair>(Query.Comand.Update, Chair.class)
				.value(chair.column, "column")
				.value(chair.state, "state")
				.value(chair.row, "row")
				.value(chair.sessao.getId() + "", "sessao")
				.PKEquals(chair.getId() + "");
		return context.execute(query);
	}

	@Override
	public Chair Get(int id) {
		Query<Chair> query = new Query<Chair>(Query.Comand.Select, Query.Modifiers.Limit1, Chair.class)
				.PKEquals(id + "");
		return context.get(query);
	}

}
