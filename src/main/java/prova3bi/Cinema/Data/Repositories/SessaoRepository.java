package prova3bi.Cinema.Data.Repositories;

import java.util.List;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.QueryComand;
import prova3bi.Cinema.Domain.Entidades.Sessao;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ISessaoRepository;

public class SessaoRepository implements ISessaoRepository{
	private DBContext context;

	public SessaoRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public List<Sessao> GetAll() {
		Query<Sessao> query = new Query<Sessao>(QueryComand.Select, Sessao.class);

		return context.getAll(query);
	}

	@Override
	public int Add(Sessao entidade) {
		Query<Sessao> query =  new Query<Sessao>(QueryComand.Insert, Sessao.class)
				.value(entidade.DHInicio, "DHInicio")
				.value(entidade.DHTermino, "DHTermino")
				.value(entidade.filme.getId(), "filme")
				.value(entidade.preco, "preco")
				.value(entidade.sala.getId(), "sala");
		
		return context.execute(query);
	}
}
