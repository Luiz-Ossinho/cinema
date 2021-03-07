package prova3bi.Cinema.Data.Repositories;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Data.Abstractions.QueryComand;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Interfaces.ILoginRepository;

public class LoginRepository implements ILoginRepository {

	private DBContext context;

	public LoginRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public Login Get(String user) {
		Query<Login> query = new Query<Login>(QueryComand.Select, Query.Modifiers.Limit1, Login.class)
				.like(user, "user");

		return context.get(query);
	}

	@Override
	public int Add(Login entidade) {
		Query<Login> query =  new Query<Login>(QueryComand.Insert, Login.class)
				.value(entidade.user, "user")
				.value(entidade.hash, "hash");

		return context.execute(query);
	}
}
