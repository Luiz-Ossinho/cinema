package prova3bi.Cinema.Domain.Validations;

import prova3bi.Cinema.Domain.Entidades.Entidade;

public interface EntityCondition<T extends Entidade>{
	public boolean Test(T entidade);
}
