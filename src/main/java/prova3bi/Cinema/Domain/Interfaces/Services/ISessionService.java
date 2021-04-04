package prova3bi.Cinema.Domain.Interfaces.Services;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.Sessao;

public interface ISessionService {
	public Sessao Add(Sessao session);
	public List<Sessao> GetNext();
}
