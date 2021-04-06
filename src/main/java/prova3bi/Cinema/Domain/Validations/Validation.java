package prova3bi.Cinema.Domain.Validations;

import javafx.util.Pair;
import prova3bi.Cinema.Domain.Entities.Entity;

public class Validation<T extends Entity> {

	private Pair<EntityCondition<T>, EntityError<T>> pair;
	
	public Validation(EntityCondition<T> condition, EntityError<T> error) {
		this.pair = new Pair<EntityCondition<T>, EntityError<T>>(condition, error);
	}
	
	public boolean Test(T instance) {
		boolean test = false;
		try {
			test = this.pair.getKey().Test(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}
	
	public EntityCondition<T> GetCondition(){
		return this.pair.getKey();
	}
	
	public EntityError<T> GetError(){
		return this.pair.getValue();
	}
}
