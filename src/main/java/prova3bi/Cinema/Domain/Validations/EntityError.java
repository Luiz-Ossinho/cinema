package prova3bi.Cinema.Domain.Validations;

import prova3bi.Cinema.Domain.Entidades.Entidade;

public interface EntityError<T extends Entidade>{
	public Error RenderError(T instance);
}
