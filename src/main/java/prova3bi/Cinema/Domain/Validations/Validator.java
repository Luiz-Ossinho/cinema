package prova3bi.Cinema.Domain.Validations;

import java.util.ArrayList;

import prova3bi.Cinema.Domain.Entities.Entity;

public class Validator<T extends Entity>{
	private ArrayList<Validation<T>> validations = new ArrayList<Validation<T>>();
	
	public Validator<T> add(EntityCondition<T> condition, EntityError<T> error) {
		validations.add(new Validation<T>(condition, error));
		return this;
	}
	
	public ErrorList TestAll(T instance){
		var errors = new ErrorList();
		for(var validation : validations) {
			if(!validation.Test(instance))
				errors.add(validation.GetError().RenderError(instance));
		}
		return errors;
	}
}
