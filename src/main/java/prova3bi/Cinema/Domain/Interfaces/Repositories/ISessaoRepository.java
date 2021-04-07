package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entities.Session;

public interface ISessaoRepository {
	public List<Session> GetAll();
	public int Add(Session entidade);
	public Session Get(int id);
}
