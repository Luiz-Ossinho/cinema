package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.Collection;

import prova3bi.Cinema.Domain.Entities.Login;

public interface ILoginRepository {
	public Login Get(String user);
	public int Add(Login entidade);
	public Collection<Login> GetAll();
}
