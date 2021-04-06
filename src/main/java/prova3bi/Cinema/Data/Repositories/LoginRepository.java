package prova3bi.Cinema.Data.Repositories;

import java.util.Collection;

import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Abstractions.Query;
import prova3bi.Cinema.Domain.Entities.Login;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;

public class LoginRepository implements ILoginRepository {

	private DBContext context;

	public LoginRepository(DBContext context) {
		this.context = context;
	}

	@Override
	public Login Get(String user) {
		Query<Login> query = new Query<Login>(Query.Comand.Select, Query.Modifiers.Limit1, Login.class).like(user,
				"user");

		return context.get(query);
	}

	@Override
	public int Add(Login entidade) {
		Query<Login> query = new Query<Login>(Query.Comand.Insert, Login.class).value(entidade.user, "user")
				.value(entidade.hash, "hash").value(entidade.nivelPermissao, "nivelPermissao");

		return context.execute(query);
	}

	@Override
	public Collection<Login> GetAll() {
		Query<Login> query = new Query<Login>(Query.Comand.Select, Login.class);

		return context.getAll(query);
	}
}
