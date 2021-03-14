package prova3bi.Cinema.Data.Abstractions;

import prova3bi.Cinema.Domain.Entidades.Entidade;

//import domain.Entidades.Entidade;

public enum Nest {
	New, Existing;

	public static <T extends Entidade> Nest Action(T entidade) {
		if (entidade.getId() != 0)
			return Nest.Existing;
		return New;
	}
}
