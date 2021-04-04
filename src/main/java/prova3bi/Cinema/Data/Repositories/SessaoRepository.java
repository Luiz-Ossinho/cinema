package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Session;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;

public class SessaoRepository implements ISessaoRepository{
	private DBContext context;

	public SessaoRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public List<Session> GetAll() {
		Query<Session> query = new Query<Session>(Query.Comand.Select, Session.class);

		return context.getAll(query);
	}

	@Override
	public int Add(Session entidade) {
		Query<Session> query =  new Query<Session>(Query.Comand.Insert, Session.class)
				.value(entidade.DHInicio, "DHInicio")
				.value(entidade.DHTermino, "DHTermino")
				.value(entidade.filme.getId(), "filme")
				.value(entidade.preco, "preco")
				.value(entidade.sala.getId(), "sala");
		
		return context.execute(query);
	}
}
