package prova3bi.Cinema.Domain.Interfaces;

import prova3bi.Cinema.Domain.Entidades.Login;

public interface ILoginRepository {
	public Login Get(String user);
	public int Add(Login entidade);
}
