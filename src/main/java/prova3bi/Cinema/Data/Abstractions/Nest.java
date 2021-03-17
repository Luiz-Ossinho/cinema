package prova3bi.Cinema.Data.Abstractions;

import prova3bi.Cinema.Domain.Entidades.Entidade;

// DESCREVE SE A ENTIDADE <? super T> ESTA SENDO PASSADA
// COM O INTUITO DE CRIAR UMA ENTIDADE NOVA NO BANCO
// OU DE REPRESENTAR UMA ENTIDADE JA EXISTENTE NO BANCO
public enum Nest {
	New, Existing;

	public static <T extends Entidade> Nest Action(T entidade) {
		if (entidade.getId() != -1)
			return Nest.Existing;
		return New;
	}
}
