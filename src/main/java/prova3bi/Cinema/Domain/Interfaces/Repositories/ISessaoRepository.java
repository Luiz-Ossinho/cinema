package prova3bi.Cinema.Domain.Interfaces.Repositories;

import java.util.List;

import prova3bi.Cinema.Domain.Entidades.Sessao;

public interface ISessaoRepository {
	public List<Sessao> GetAll();
	public int Add(Sessao entidade);
}
