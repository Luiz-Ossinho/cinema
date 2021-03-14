package prova3bi.Cinema.Data.Repositories;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.QueryComand;
import prova3bi.Cinema.Domain.Entidades.Entidade;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Entidades.Poltrona;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IChairRepository;

public class ChairRepository implements IChairRepository {

	private DBContext context;

	public ChairRepository(DBContext context) {
		this.context = context;
	}
	
	@Override
	public int Add(Poltrona chair) {
		Query<Poltrona> query =  new Query<Poltrona>(QueryComand.Insert, Poltrona.class)
				.value(chair.column, "column")
				.value(chair.ocupada, "ocupada")
				.value(chair.row, "row")
				.value(chair.sessao.getId()+"", "sessaoId");
		return 0;
	}

}
