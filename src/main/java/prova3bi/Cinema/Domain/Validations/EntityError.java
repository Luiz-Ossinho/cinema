package prova3bi.Cinema.Domain.Validations;

import prova3bi.Cinema.Domain.Entities.Entity;

public interface EntityError<T extends Entity>{
	public Error RenderError(T instance);
}
