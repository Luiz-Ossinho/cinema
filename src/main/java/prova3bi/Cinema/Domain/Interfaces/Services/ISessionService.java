package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sessao;

public interface ISessionService {
	public int Add(Sessao session);
	public List<Sessao> GetAll();
}
