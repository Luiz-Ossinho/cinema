package prova3bi.Cinema.Domain.Validations;

import prova3bi.Cinema.Domain.Entities.Entity;

public interface EntityCondition<T extends Entity>{
	public boolean Test(T entidade);
}
