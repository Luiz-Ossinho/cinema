package prova3bi.Cinema.Domain.Interfaces.Repositories;

import prova3bi.Cinema.Domain.Entidades.Login;

public interface ILoginRepository {
	public Login Get(String user);
	public int Add(Login entidade);
}
